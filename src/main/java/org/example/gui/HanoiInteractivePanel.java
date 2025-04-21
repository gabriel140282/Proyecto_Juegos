package org.example.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;

/**
 * Panel interactivo para jugar a las Torres de Hanoi con el ratón.
 * Haz clic en una torre para seleccionar el disco, luego en otra torre para moverlo.
 */
public class HanoiInteractivePanel extends JPanel implements MouseListener {
    private int numDisks;
    private Stack<Integer>[] pegs;
    private int selectedPeg = -1;
    private static final int PEG_COUNT = 3;
    private static final Color[] DISK_COLORS = {
            new Color(0x607D8B), // gris oscuro
            new Color(0xFF5722), // naranja
            new Color(0x4CAF50), // verde
            new Color(0x2196F3), // azul
            new Color(0x9C27B0), // morado
            new Color(0xF44336), // rojo
            new Color(0x03A9F4), // celeste
            new Color(0x795548), // marrón
            new Color(0xFFC107), // ámbar
            new Color(0x009688)  // teal
    };

    public HanoiInteractivePanel(int numDisks) {
        this.numDisks = numDisks;
        initPegs();
        addMouseListener(this);
    }

    /** Inicializa las pilas con los discos en la primera torre */
    private void initPegs() {
        pegs = new Stack[PEG_COUNT];
        for (int i = 0; i < PEG_COUNT; i++) {
            pegs[i] = new Stack<>();
        }
        for (int d = numDisks; d >= 1; d--) {
            pegs[0].push(d);
        }
        selectedPeg = -1;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();
        int pegWidth = 10;
        int pegHeight = height * 3 / 4;
        int baseY = height - 30;
        int spacing = width / (PEG_COUNT + 1);

        // Dibujar base
        g2.setColor(new Color(0x795548));
        g2.fillRect(0, baseY, width, 10);

        // Dibujar palos y discos
        for (int i = 0; i < PEG_COUNT; i++) {
            int pegX = spacing * (i + 1);
            // palo
            g2.fillRect(pegX - pegWidth/2, baseY - pegHeight, pegWidth, pegHeight);

            // discos
            Stack<Integer> stack = pegs[i];
            for (int j = 0; j < stack.size(); j++) {
                int diskSize = stack.get(j);
                int diskWidth = diskSize * spacing / (numDisks+1);
                int diskHeight = 20;
                int x = pegX - diskWidth/2;
                int y = baseY - diskHeight*(j+1);
                Color color = DISK_COLORS[(diskSize-1) % DISK_COLORS.length];
                g2.setColor(color);
                g2.fillRoundRect(x, y, diskWidth, diskHeight, 10, 10);
                g2.setColor(Color.BLACK);
                g2.drawRoundRect(x, y, diskWidth, diskHeight, 10, 10);
            }

            // resaltar torre seleccionada
            if (i == selectedPeg) {
                g2.setColor(Color.RED);
                g2.setStroke(new BasicStroke(3));
                g2.drawRect(pegX - spacing/2, baseY - pegHeight - 5, spacing, pegHeight + 10);
                g2.setStroke(new BasicStroke(1));
            }
        }
    }

    private int pegAtPosition(int x) {
        int width = getWidth();
        int spacing = width / (PEG_COUNT + 1);
        for (int i = 0; i < PEG_COUNT; i++) {
            int pegX = spacing * (i + 1);
            if (Math.abs(x - pegX) < spacing/2) return i;
        }
        return -1;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int peg = pegAtPosition(e.getX());
        if (peg < 0) return;
        if (selectedPeg == -1) {
            // seleccionar origen
            if (!pegs[peg].isEmpty()) {
                selectedPeg = peg;
            }
        } else {
            // intentar mover a destino
            if (selectedPeg != peg) {
                Stack<Integer> src = pegs[selectedPeg];
                Stack<Integer> dst = pegs[peg];
                if (!src.isEmpty()) {
                    int disk = src.peek();
                    if (dst.isEmpty() || dst.peek() > disk) {
                        dst.push(src.pop());
                    } else {
                        // movimiento ilegal: no se puede apilar
                        Toolkit.getDefaultToolkit().beep();
                    }
                }
            }
            selectedPeg = -1;
        }
        repaint();
    }

    // Métodos sin usar
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}

    /** Reinicia el juego con n discos */
    public void reset(int n) {
        this.numDisks = n;
        initPegs();
    }
}
