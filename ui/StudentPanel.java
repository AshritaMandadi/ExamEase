package ui;

import service.DataStore;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StudentPanel extends JPanel {

    public StudentPanel(String rollNo) {
        setLayout(new BorderLayout(12, 12));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(new Color(245, 245, 245));

        JLabel heading = new JLabel("Student Panel - My Seat Details");
        heading.setFont(new Font("Arial", Font.BOLD, 18));
        add(heading, BorderLayout.NORTH);

        // Result area
        JTextArea result = new JTextArea();
        result.setEditable(false);
        result.setFont(new Font("Arial", Font.PLAIN, 15));
        result.setLineWrap(true);
        result.setWrapStyleWord(true);
        result.setBorder(new EmptyBorder(12, 12, 12, 12));

        JScrollPane scrollPane = new JScrollPane(result);

        // Button
        JButton checkBtn = new JButton("Check My Seat Allocation");
        checkBtn.setFocusPainted(false);
        checkBtn.setFont(new Font("Arial", Font.BOLD, 14));
        checkBtn.setBackground(new Color(25, 118, 210));
        checkBtn.setForeground(Color.WHITE);
        checkBtn.setOpaque(true);
        checkBtn.setBorderPainted(false);
        checkBtn.setPreferredSize(new Dimension(250, 40));

        checkBtn.addActionListener(e -> {
            var found = DataStore.allocations.stream()
                    .filter(a -> a.getRollNo().equals(rollNo))
                    .findFirst();

            if (found.isPresent()) {
                var a = found.get();
                result.setText("✅ Allocation Found!\n\n"
                        + "Roll No: " + a.getRollNo() + "\n"
                        + "Name: " + a.getStudentName() + "\n"
                        + "Room: " + a.getRoomNo() + "\n"
                        + "Seat: " + a.getSeatNo());
            } else {
                result.setText("❌ No allocation found yet.\n\nAsk Admin to generate allocation.");
            }
        });

        // Bottom panel (button container)
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.setBackground(new Color(245, 245, 245));
        bottom.add(checkBtn);

        add(scrollPane, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }
}

