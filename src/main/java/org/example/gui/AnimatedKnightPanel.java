// src/main/java/org/example/gui/AnimatedKnightPanel.java
package org.example.gui;

import org.example.caballo.KnightSolver;
import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Panel animado para mostrar paso a paso el Tour del Caballo,
 * arrancando desde (startRow, startCol).
 */
public class AnimatedKnightPanel extends JPanel {
    private int dimension;
    private List<int[]> path;
    private int currentStep;
    private Timer timer;
    private static final int CELL_SIZE = 20;

    public AnimatedKnightPanel() {
        setBackground(Color.WHITE);
    }

    /**
     * Ahora recibe también las coordenadas de arranque.
     */
    public void startTour(int d, int startRow, int startCol) {
        this.dimension = d;
        // Creamos un solver que arranca desde centro (o desde donde querramos)
        KnightSolver solver = new KnightSolver(d, startRow, startCol);
        solver.resolver();             // hará el dfs desde esas coords
        this.path = solver.getCamino();
        this.currentStep = 0;

        // ajusta tamaño
        int sizePx = CELL_SIZE * d;
        setPreferredSize(new Dimension(sizePx, sizePx));
        revalidate();

        if (timer != null && timer.isRunning()) timer.stop();
        timer = new Timer(150, e -> {
            currentStep++;
            if (currentStep >= path.size()) {
                ((Timer)e.getSource()).stop();
            }
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (path == null || path.isEmpty()) return;
        Graphics2D g2 = (Graphics2D) g;

        // 1) dibujo tablero
        for (int r = 0; r < dimension; r++) {
            for (int c = 0; c < dimension; c++) {
                boolean light = ((r + c) & 1) == 0;
                g2.setColor(light ? Color.LIGHT_GRAY : Color.DARK_GRAY);
                g2.fillRect(c*CELL_SIZE, r*CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }

        // 2) dibujo líneas
        g2.setColor(Color.BLUE);
        g2.setStroke(new BasicStroke(2));
        for (int i = 1; i <= currentStep && i < path.size(); i++) {
            int[] prev = path.get(i-1);
            int[] cur  = path.get(i);
            int x1 = prev[1]*CELL_SIZE + CELL_SIZE/2;
            int y1 = prev[0]*CELL_SIZE + CELL_SIZE/2;
            int x2 = cur[1]*CELL_SIZE  + CELL_SIZE/2;
            int y2 = cur[0]*CELL_SIZE  + CELL_SIZE/2;
            g2.drawLine(x1, y1, x2, y2);
        }

        // 3) dibujo caballo
        int[] pos = path.get(Math.min(currentStep, path.size()-1));
        int x = pos[1]*CELL_SIZE;
        int y = pos[0]*CELL_SIZE;
        g2.setColor(Color.RED);
        g2.fillOval(x+4, y+4, CELL_SIZE-8, CELL_SIZE-8);
    }
}
