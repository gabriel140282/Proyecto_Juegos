package org.example.reinas;

import org.example.figura.Figura;

public class Queen extends Figura {
    private int fila;
    private int columna;

    public Queen(String id, int fila, int columna) {
        super(id);
        this.fila = fila;
        this.columna = columna;
    }

    @Override
    public void resolver() {
        // No aplica
    }

    public int getFila() { return fila; }
    public int getColumna() { return columna; }
}

