package org.example.reinas;

import org.example.figura.Figura;
import java.util.ArrayList;
import java.util.List;

public class QueenSolver extends Figura {
    private int n;
    private int[] posiciones;
    private List<int[]> soluciones;
    private int operaciones;

    public QueenSolver(int n) {
        super("Reinas");
        this.n = n;
        this.posiciones = new int[n];
        this.soluciones = new ArrayList<>();
        this.operaciones = 0;
    }

    @Override
    public void resolver() {
        backtrack(0);
    }

    private void backtrack(int fila) {
        if (fila == n) {
            soluciones.add(posiciones.clone());
            return;
        }
        for (int col = 0; col < n; col++) {
            operaciones++;
            if (esSeguro(fila, col)) {
                posiciones[fila] = col;
                registrarMovimiento();
                backtrack(fila + 1);
            }
        }
    }

    private boolean esSeguro(int fila, int col) {
        for (int i = 0; i < fila; i++) {
            if (posiciones[i] == col || Math.abs(posiciones[i] - col) == Math.abs(i - fila)) {
                return false;
            }
        }
        return true;
    }

    public List<int[]> getSoluciones() {
        return soluciones;
    }

    public int getOperaciones() {
        return operaciones;
    }
}

