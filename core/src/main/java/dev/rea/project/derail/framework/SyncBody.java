package dev.rea.project.derail.framework;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class SyncBody implements ClientRenderObject {

    public final Body body;
    private final ClientRenderDTO dto;

    public SyncBody(Body body, String id) {
        this.body = body;
        Vector2 pos = body.getPosition();
        this.dto = new ClientRenderDTO(id, pos.x, pos.y, body.getAngle());
    }

    @Override
    public ClientRenderDTO getDTO() {
        Vector2 pos = body.getPosition();
        dto.update(pos.x, pos.y, body.getAngle());
        return dto;
    }
}
