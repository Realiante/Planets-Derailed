package dev.rea.project.derail;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.kotcrab.vis.ui.VisUI;
import dev.rea.project.derail.screens.SimulationScreen;
import dev.rea.project.derail.screens.StartMenuScreen;
import dev.rea.project.derail.ships.ShipLoader;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class DerailGame extends Game {

    private SimulationScreen sim;
    private StartMenuScreen menu;


    @Override
    public void create() {
        VisUI.load(VisUI.SkinScale.X1);

        //hack to load box2d natives
        new World(Vector2.Zero, true).dispose();

        menu = new StartMenuScreen(this);
        sim = new SimulationScreen();
        ShipLoader.init();

        setScreen(menu);
    }

    public void startSimulation() {
        menu.pause();
        setScreen(sim);
    }

    public void stopSimulation() {
        sim.pause();
        setScreen(menu);
    }

    @Override
    public void dispose() {
        VisUI.dispose();
    }
}