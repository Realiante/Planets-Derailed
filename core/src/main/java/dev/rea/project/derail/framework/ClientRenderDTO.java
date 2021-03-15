package dev.rea.project.derail.framework;

/**
 * Data transfer object for
 */
public final class ClientRenderDTO {

    private final String id;
    private float x;
    private float y;
    private float rot;

    public ClientRenderDTO(String id, float x, float y, float rot) {
        this.id = id;
        update(x, y, rot);
    }

    public void update(float x, float y, float rot) {
        this.x = x;
        this.y = y;
        this.rot = rot;
    }

    public String getId() {
        return id;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getRot() {
        return rot;
    }

    public void setRot(float rot) {
        this.rot = rot;
    }
}
