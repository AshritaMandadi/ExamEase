package ui;

import service.AuthService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JComboBox<String> roleBox;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("ExamEase - Login");
        setSize(900, 520);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel left = new JPanel(new BorderLayout());
        left.setBackground(new Color(25, 118, 210));
        left.setPreferredSize(new Dimension(330, 520));

        JLabel brand = new JLabel(
                "<html><div style='text-align:center;'>"
                        + "<h1 style='color:white; margin:0;'>ExamEase</h1>"
                        + "<p style='color:white; font-size:14px;'>Seating Allocation System</p>"
                        + "<p style='color:white; font-size:12px;'>Admin • Faculty • Student</p>"
                        + "</div></html>",
                SwingConstants.CENTER
        );

        left.add(brand, BorderLayout.CENTER);

        JPanel right = new JPanel();
        right.setBackground(Color.WHITE);
        right.setBorder(new EmptyBorder(30, 40, 30, 40));
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Login");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitle = new JLabel("Welcome back! Please login to continue.");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitle.setForeground(new Color(90, 90, 90));
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        right.add(title);
        right.add(Box.createVerticalStrut(8));
        right.add(subtitle);
        right.add(Box.createVerticalStrut(25));

        roleBox = new JComboBox<>(new String[]{"Admin", "Faculty", "Student"});
        roleBox.setPreferredSize(new Dimension(400, 32));
        roleBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        right.add(makeField("Select Role", roleBox));

        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(400, 32));
        usernameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        right.add(makeField("Username / ID", usernameField));

        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(400, 32));
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        right.add(makeField("Password", passwordField));

        JButton loginBtn = new JButton("Login");
        loginBtn.setFocusPainted(false);
        loginBtn.setFont(new Font("Arial", Font.BOLD, 15));
        loginBtn.setBackground(new Color(25, 118, 210));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setOpaque(true);
        loginBtn.setBorderPainted(false);
        loginBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        loginBtn.addActionListener(e -> doLogin());

        right.add(Box.createVerticalStrut(15));
        right.add(loginBtn);

        right.add(Box.createVerticalStrut(10));

        JScrollPane scroll = new JScrollPane(right);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        add(left, BorderLayout.WEST);
        add(scroll, BorderLayout.CENTER);
    }

    private JPanel makeField(String label, JComponent field) {
        JPanel panel = new JPanel(new BorderLayout(5, 6));
        panel.setBackground(Color.WHITE);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel l = new JLabel(label);
        l.setFont(new Font("Arial", Font.PLAIN, 13));
        l.setForeground(new Color(60, 60, 60));

        panel.add(l, BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);
        panel.add(Box.createVerticalStrut(14), BorderLayout.SOUTH);

        return panel;
    }

    private void doLogin() {
        String role = roleBox.getSelectedItem().toString();
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter username and password!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean ok = false;

        if (role.equals("Admin")) ok = AuthService.loginAdmin(username, password);
        if (role.equals("Faculty")) ok = AuthService.loginFaculty(username, password);
        if (role.equals("Student")) ok = AuthService.loginStudent(username, password);

        if (ok) {
            JOptionPane.showMessageDialog(this, "Login Successful ");
            dispose();
            new DashboardFrame(role, username).setVisible(true);
        } 
        else {
            JOptionPane.showMessageDialog(this, "Invalid Credentials ", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
}

