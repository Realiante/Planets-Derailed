package dev.rea.project.derail.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import dev.rea.project.derail.framework.Simulation;
import dev.rea.project.derail.ships.ShipLoader;

import static com.badlogic.gdx.math.MathUtils.clamp;

public class SimulationScreen implements Screen {

    /**
     * Simulation step time constant.
     * Simulation will be shown to clients at 42fps, but for more
     * precision and reduced clipping we advance time step twice for each frame
     */
    private static final float STEP_TIME = 42f;

    private final Box2DDebugRenderer debugRenderer;
    private final ShapeRenderer shapeRenderer;
    private final OrthographicCamera camera;

    private final Stage mainStage;
    private final Stage uiStage;

    private int scrWidth = 800;
    private int scrHeight = 600;

    private float xCamDiff = 0f;
    private float yCamDiff = 0f;
    private float viewportScale = 4f;

    private Simulation currentSimulation;

    public SimulationScreen() {
        //TODO: pass actual sim
        System.out.println(ShipLoader.getInstance().getModel("deb1"));
        currentSimulation = new Simulation();
        debugRenderer = new Box2DDebugRenderer();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setColor(1, 1, 0, 1);
        shapeRenderer.setAutoShapeType(true);

        //todo: change screen sizes and add resize.
        camera = new OrthographicCamera(scale(scrWidth), scale(scrHeight));
        ExtendViewport extViewport = new ExtendViewport(800, 600, camera);
        mainStage = new Stage(extViewport);
        uiStage = new Stage();
        camera.update();
        uiStage.addListener(new CameraManipulationListener());

        //todo: remove debug
        uiStage.addListener(new DebugListener());
    }


    protected float scale(float value) {
        return value * viewportScale;
    }

    protected int scaleInt(float value) {
        return (int) (value * viewportScale);
    }

    @Override
    public void dispose() {
        mainStage.dispose();
        uiStage.dispose();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(uiStage);
    }

    private void act(float dt) {
        mainStage.act(dt);
        uiStage.act(dt);
    }


    private void updateCamera() {
        float curX = camera.position.x + xCamDiff;
        float curY = camera.position.y + yCamDiff;
        camera.position.set(clamp(curX, 0f, scale(Simulation.X_BOUND)),
                clamp(curY, 0f, scale(Simulation.Y_BOUND)), 0);
        camera.update();
    }

    @Override
    public void render(float dt) {
        //Perform actions on stage each delta or once per 1/42th of a second, whichever is least.
        act(Math.min(dt, 1f / STEP_TIME));

        // clear the screen ready for next set of images to be drawn
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT
                | (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));

        //camera updates
        updateCamera();
        mainStage.getBatch().getProjectionMatrix().set(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);

        //draw shapes
        shapeRenderer.begin();
        shapeRenderer.rect(0, 0, Simulation.X_BOUND, Simulation.Y_BOUND);
        shapeRenderer.end();

        //draw debug bodies
        debugRenderer.render(currentSimulation.getWorld(), camera.combined);

        //draw stages
        mainStage.draw();
        uiStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        scrWidth = width;
        scrHeight = height;
        camera.setToOrtho(false, scale(width), scale(height));
    }

    @Override
    public void pause() {
        //stub
    }

    @Override
    public void resume() {
        //stub
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    private class CameraManipulationListener extends InputListener {
        @Override
        public boolean keyDown(InputEvent event, int keycode) {
            if (keycode == Input.Keys.LEFT || keycode == Input.Keys.A) {
                xCamDiff = -12;
            } else if (keycode == Input.Keys.RIGHT || keycode == Input.Keys.D) {
                xCamDiff = 12;
            } else if (keycode == Input.Keys.UP || keycode == Input.Keys.W) {
                yCamDiff = 12;
            } else if (keycode == Input.Keys.DOWN || keycode == Input.Keys.S) {
                yCamDiff = -12;
            }
            return super.keyDown(event, keycode);
        }

        @Override
        public boolean keyUp(InputEvent event, int keycode) {
            if (keycode == Input.Keys.LEFT || keycode == Input.Keys.A) {
                xCamDiff = 0;
            } else if (keycode == Input.Keys.RIGHT || keycode == Input.Keys.D) {
                xCamDiff = 0;
            } else if (keycode == Input.Keys.UP || keycode == Input.Keys.W) {
                yCamDiff = 0;
            } else if (keycode == Input.Keys.DOWN || keycode == Input.Keys.S) {
                yCamDiff = 0;
            }
            return super.keyUp(event, keycode);
        }

        @Override
        public boolean scrolled(InputEvent event, float x, float y, float amountX, float amountY) {
            viewportScale = clamp(viewportScale + (amountY / 2f), 1, 16);
            resize(scrWidth, scrHeight);
            return super.scrolled(event, x, y, amountX, amountY);
        }
    }

    private class DebugListener extends InputListener {
        @Override
        public boolean keyTyped(InputEvent event, char character) {
            System.out.println(character);
            return super.keyTyped(event, character);
        }
    }
}
