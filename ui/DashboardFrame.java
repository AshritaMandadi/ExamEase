package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DashboardFrame extends JFrame {

    public DashboardFrame(String role, String username) {
        setTitle("ExamEase - Dashboard (" + role + ")");
        setSize(1100, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(20, 20, 20));
        header.setBorder(new EmptyBorder(15, 20, 15, 20));

        JLabel title = new JLabel("ExamEase Dashboard");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        // Right side: role + user + logout button
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        rightPanel.setOpaque(false);

        JLabel user = new JLabel("Role: " + role + " | User: " + username);
        user.setForeground(new Color(200, 200, 200));
        user.setFont(new Font("Arial", Font.PLAIN, 13));

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setFocusPainted(false);
        logoutBtn.setFont(new Font("Arial", Font.BOLD, 13));
        logoutBtn.setBackground(new Color(220, 53, 69)); // red
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setOpaque(true);
        logoutBtn.setBorderPainted(false);

        logoutBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to logout?",
                    "Logout",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                dispose(); // close dashboard
                new LoginFrame().setVisible(true); // open login again
            }
        });

        rightPanel.add(user);
        rightPanel.add(logoutBtn);

        header.add(title, BorderLayout.WEST);
        header.add(rightPanel, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        // Main Content
        JPanel content = new JPanel(new BorderLayout());
        content.setBorder(new EmptyBorder(15, 15, 15, 15));
        content.setBackground(new Color(245, 245, 245));

        if (role.equals("Admin")) {
            content.add(new AdminPanel(), BorderLayout.CENTER);
        } else if (role.equals("Faculty")) {
            content.add(new FacultyPanel(), BorderLayout.CENTER);
        } else {
            content.add(new StudentPanel(username), BorderLayout.CENTER);
        }

        add(content, BorderLayout.CENTER);
    }
}

