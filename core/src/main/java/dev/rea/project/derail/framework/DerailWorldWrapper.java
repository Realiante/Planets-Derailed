package dev.rea.project.derail.framework;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class DerailWorldWrapper {

    private final World world;
    private final Array<Body> bodies;

    public DerailWorldWrapper(World world) {
        this.world = world;
        this.bodies = new Array<>();
    }

    public World getWorld() {
        return world;
    }

    public Array<Body> getBodies() {
        return bodies;
    }
}
