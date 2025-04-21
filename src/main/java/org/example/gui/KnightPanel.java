package org.example.gui;

import org.example.caballo.KnightSolver;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class KnightPanel extends JPanel {
    private JTextArea output;
    private JButton startBtn;
    private JTextField sizeField;

    public KnightPanel() {
        setLayout(new BorderLayout());
        JPanel top = new JPanel();
        top.add(new JLabel("Dimension: ")); sizeField = new JTextField("27",3);
        top.add(sizeField);
        startBtn = new JButton("Resolver");
        top.add(startBtn);
        add(top, BorderLayout.NORTH);

        output = new JTextArea(); output.setEditable(false);
        add(new JScrollPane(output), BorderLayout.CENTER);

        startBtn.addActionListener((ActionEvent e) -> runSolver());
    }

    private void runSolver() {
        output.setText("");
        int d = Integer.parseInt(sizeField.getText());
        KnightSolver solver = new KnightSolver(d, 0, 0);
        solver.resolver();
        output.append("Movimientos: " + solver.getContadorMovimientos() + "\n");
        output.append("Reintentos: " + solver.getReintentos() + "\n");
    }
}
