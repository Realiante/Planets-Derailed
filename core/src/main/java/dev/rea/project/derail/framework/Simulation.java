package dev.rea.project.derail.framework;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import dev.rea.project.derail.ships.ShipLoader;

public class Simulation {

    public static final float X_BOUND = 3200;
    public static final float Y_BOUND = 3200;
    public final DerailWorldWrapper derailWorld;

    //TODO: Pass a real world
    public Simulation() {
        derailWorld = new DerailWorldWrapper(new World(Vector2.Zero, false));
        debugShip();
    }

    private void debugShip() {
        World world = derailWorld.getWorld();
        ShipLoader loader = ShipLoader.getInstance();

        Body ship1 = loader.getModel("deb1").buildShipBody(world);
        ship1.setTransform(750, 650, 1.5f);

        Body ship2 = loader.getModel("deb2").buildShipBody(world);
        ship2.setTransform(130, 250, 2.5f);

        Body ship3 = loader.getModel("deb3").buildShipBody(world);
        ship3.setTransform(135, 700, 0.5f);

        Body ship4 = loader.getModel("deb_big").buildShipBody(world);
        ship4.setTransform(600, 1600, -0.2f);
    }

    public World getWorld() {
        return derailWorld.getWorld();
    }
}
