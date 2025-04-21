package org.example.gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Juegos Clásicos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Reinas", new QueensPanel());

        // --------------------------------------------------
        //   Pestaña "Caballo" ya NO es KnightPanel
        // --------------------------------------------------
        AnimatedKnightPanel animPanel = new AnimatedKnightPanel();
        JPanel caballoTab = new JPanel(new BorderLayout());

        JPanel controls = new JPanel();
        controls.add(new JLabel("Dimensión:"));
        JTextField dimField = new JTextField("27", 3);
        JButton startBtn = new JButton("Iniciar Tour");
        controls.add(dimField);
        controls.add(startBtn);
        caballoTab.add(controls, BorderLayout.NORTH);

        caballoTab.add(animPanel, BorderLayout.CENTER);

        startBtn.addActionListener(e -> {
            int d = Integer.parseInt(dimField.getText());
            // Arranca el tour desde el centro, no desde (0,0)
            animPanel.startTour(d, d/2, d/2);
        });

        tabs.addTab("Caballo", caballoTab);

        add(tabs, BorderLayout.CENTER);

        HanoiInteractivePanel hanoiPanel = new HanoiInteractivePanel(5); // o n
        tabs.addTab("Hanoi", hanoiPanel);

        JTextField nField = new JTextField("5", 2);
        JButton resetBtn = new JButton("Reiniciar");
        resetBtn.addActionListener(e -> {
            int n = Integer.parseInt(nField.getText());
            hanoiPanel.reset(n);
        });
// agrégalo en un panel encima o debajo del hanoiPanel
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
