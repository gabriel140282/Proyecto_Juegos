package org.example.figura;

public abstract class Figura {
    protected String id;
    protected int contadorMovimientos;

    public Figura(String id) {
        this.id = id;
        this.contadorMovimientos = 0;
    }

    public abstract void resolver();

    protected void registrarMovimiento() {
        this.contadorMovimientos++;
    }

    public int getContadorMovimientos() {
        return contadorMovimientos;
    }

    public String getId() {
        return id;
    }
}
