package org.example.gui;

import org.example.reinas.QueenSolver;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class QueensPanel extends JPanel {
    private JTextArea output;
    private JButton startBtn;
    private JTextField sizeField;

    public QueensPanel() {
        setLayout(new BorderLayout());
        JPanel top = new JPanel();
        top.add(new JLabel("N: ")); sizeField = new JTextField("8",3);
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
        int n = Integer.parseInt(sizeField.getText());
        QueenSolver solver = new QueenSolver(n);
        solver.resolver();
        output.append("Soluciones encontradas: " + solver.getSoluciones().size() + "\n");
        output.append("Operaciones: " + solver.getOperaciones() + "\n");

        // Mostrar la primera solución
        if (!solver.getSoluciones().isEmpty()) {
            int[] sol = solver.getSoluciones().get(0);
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    output.append(sol[row] == col ? "Q ": ". ");
                }
                output.append("\n");
            }
        }
    }
}
