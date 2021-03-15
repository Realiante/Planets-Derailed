package dev.rea.project.derail.ships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.JsonValue;
import dev.rea.project.derail.framework.BodyEditorLoader;

import java.io.Serializable;
import java.util.Objects;

public class ShipModel {

    public final String name;
    public final String shipID;
    public final String description;
    public final String shipType;
    public final float density;
    public final int armour;
    public final int hull;
    public final String iconPath;

    public final BodyEditorLoader bodyLoader;
    public final int width;

    public ShipModel(JsonValue jsonBase) {
        name = jsonBase.getString("name");
        shipID = jsonBase.getString("id");
        description = jsonBase.getString("description");
        shipType = jsonBase.getString("class");
        density = jsonBase.getFloat("density");
        armour = jsonBase.getInt("armour");
        hull = jsonBase.getInt("hull");
        iconPath = jsonBase.getString("icon");
        width = jsonBase.getInt("width");

        String blPath = jsonBase.getString("bodyLoader");
        bodyLoader = new BodyEditorLoader(Gdx.files.internal("ships/" + blPath));
    }

    public Body buildShipBody(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        Body body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = density;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.1f;

        //todo
        bodyLoader.attachFixture(body, "body", fixtureDef, width);
        return body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShipModel shipModel = (ShipModel) o;
        return shipID.equals(shipModel.shipID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shipID);
    }

    @Override
    public String toString() {
        return "ShipModel{" +
                "name='" + name + '\'' +
                ", shipID='" + shipID + '\'' +
                '}';
    }
}
