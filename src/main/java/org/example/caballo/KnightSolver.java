// src/main/java/org/example/caballo/KnightSolver.java
package org.example.caballo;

import org.example.figura.Figura;
import java.util.ArrayList;
import java.util.List;

public class KnightSolver extends Figura {
    private int d;
    private boolean[][] visitado;
    private List<int[]> camino;
    private int reintentos;
    private int startX, startY;

    private final int[] dx = {2,2,1,1,-1,-1,-2,-2};
    private final int[] dy = {1,-1,2,-2,2,-2,1,-1};

    /** Nuevo constructor que recibe la casilla de arranque */
    public KnightSolver(int d, int startX, int startY) {
        super("Caballo");
        this.d = d;
        this.startX = startX;
        this.startY = startY;
        this.visitado = new boolean[d][d];
        this.camino = new ArrayList<>();
        this.reintentos = 0;
    }

    /** Lanza el dfs desde (startX,startY) */
    @Override
    public void resolver() {
        dfs(startX, startY, 0);
    }

    private boolean dfs(int x, int y, int paso) {
        visitado[x][y] = true;
        camino.add(new int[]{x,y});
        registrarMovimiento();
        if (paso == d*d - 1) return true;

        List<int[]> vecinos = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            if (nx>=0 && ny>=0 && nx<d && ny<d && !visitado[nx][ny]) {
                vecinos.add(new int[]{nx,ny});
            }
        }
        // Warnsdorff
        vecinos.sort((a,b) -> degree(a[0],a[1]) - degree(b[0],b[1]));

        for (int[] v : vecinos) {
            if (dfs(v[0], v[1], paso+1)) return true;
            reintentos++;
        }
        visitado[x][y] = false;
        camino.remove(camino.size()-1);
        return false;
    }

    private int degree(int x, int y) {
        int cnt=0;
        for (int i=0;i<8;i++) {
            int nx=x+dx[i], ny=y+dy[i];
            if (nx>=0&&ny>=0&&nx<d&&ny<d&&!visitado[nx][ny]) cnt++;
        }
        return cnt;
    }

    public List<int[]> getCamino() { return camino; }
    public int getReintentos()     { return reintentos; }
}
