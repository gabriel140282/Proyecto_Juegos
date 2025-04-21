package org.example.caballo;

import org.example.figura.Figura;

public class Knight extends Figura {
    private int x;
    private int y;

    public Knight(String id, int startX, int startY) {
        super(id);
        this.x = startX;
        this.y = startY;
    }

    @Override
    public void resolver() {
        // No aplica
    }

    public int getX() { return x; }
    public int getY() { return y; }
}

