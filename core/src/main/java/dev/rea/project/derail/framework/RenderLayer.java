package dev.rea.project.derail.framework;

public enum RenderLayer {
    PROJ_OBJ(6),
    SHIP_UPPER(5),
    SHIP_MID(4),
    SHIP_BASE(3),
    BG_OBJ(2);

    int l;

    RenderLayer(int layer) {
        this.l = layer;
    }

    public int getLayer() {
        return l;
    }
}
