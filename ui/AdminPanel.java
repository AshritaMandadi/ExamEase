package ui;

import model.Faculty;
import model.Room;
import model.Student;
import service.AllocationService;
import service.DataStore;
import service.PasswordUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AdminPanel extends JPanel {

    private DefaultTableModel studentModel;
    private DefaultTableModel facultyModel;
    private DefaultTableModel roomModel;
    private DefaultTableModel allocationModel;

    public AdminPanel() {
        setLayout(new BorderLayout(15, 15));
        setBackground(new Color(245, 245, 245));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel heading = new JLabel("Admin Panel - Manage System");
        heading.setFont(new Font("Arial", Font.BOLD, 18));
        add(heading, BorderLayout.NORTH);

        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(new Font("Arial", Font.BOLD, 13));

        tabs.addTab("Students", createStudentTab());
        tabs.addTab("Faculty", createFacultyTab());
        tabs.addTab("Rooms", createRoomTab());
        tabs.addTab("Allocation", createAllocationTab());

        add(tabs, BorderLayout.CENTER);
    }

    // -------------------- Students Tab --------------------
    private JPanel createStudentTab() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setBackground(Color.WHITE);

        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));
        form.setBackground(Color.WHITE);

        JTextField name = new JTextField();
        JTextField roll = new JTextField();
        JTextField branch = new JTextField();
        JTextField year = new JTextField();

        form.add(new JLabel("Name"));
        form.add(name);
        form.add(new JLabel("Roll No (Username)"));
        form.add(roll);
        form.add(new JLabel("Branch"));
        form.add(branch);
        form.add(new JLabel("Year"));
        form.add(year);

        JButton addBtn = new JButton("Add Student");
        stylePrimaryButton(addBtn);

        addBtn.addActionListener(e -> {
            String n = name.getText().trim();
            String r = roll.getText().trim();
            String b = branch.getText().trim();
            String y = year.getText().trim();

            if (n.isEmpty() || r.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name & Roll No required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String pwd = PasswordUtil.generateStudentPassword();
            Student s = new Student(n, r, b, y, pwd);

            DataStore.students.add(s);
            studentModel.addRow(new Object[]{s.getName(), s.getRollNo(), s.getBranch(), s.getYear(), s.getPassword()});

            JOptionPane.showMessageDialog(this,
                    "Student Added ✅\nUsername: " + s.getRollNo() + "\nPassword: " + s.getPassword());

            name.setText("");
            roll.setText("");
            branch.setText("");
            year.setText("");
        });

        studentModel = new DefaultTableModel(new Object[]{"Name", "Roll No", "Branch", "Year", "Password"}, 0);
        JTable table = new JTable(studentModel);
        table.setRowHeight(22);

        JPanel top = new JPanel(new BorderLayout(10, 10));
        top.setBackground(Color.WHITE);
        top.add(form, BorderLayout.CENTER);

        JPanel btnWrap = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnWrap.setBackground(Color.WHITE);
        btnWrap.add(addBtn);

        top.add(btnWrap, BorderLayout.SOUTH);

        panel.add(top, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        return panel;
    }

    // -------------------- Faculty Tab --------------------
    private JPanel createFacultyTab() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setBackground(Color.WHITE);

        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));
        form.setBackground(Color.WHITE);

        JTextField fname = new JTextField();
        JTextField fid = new JTextField();
        JTextField dept = new JTextField();

        form.add(new JLabel("Faculty Name"));
        form.add(fname);
        form.add(new JLabel("Faculty ID (Username)"));
        form.add(fid);
        form.add(new JLabel("Department"));
        form.add(dept);

        JButton addBtn = new JButton("Add Faculty");
        stylePrimaryButton(addBtn);

        addBtn.addActionListener(e -> {
            String n = fname.getText().trim();
            String id = fid.getText().trim();
            String d = dept.getText().trim();

            if (n.isEmpty() || id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Faculty Name & ID required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String pwd = PasswordUtil.generateFacultyPassword();
            Faculty f = new Faculty(n, id, d, pwd);

            DataStore.faculties.add(f);
            facultyModel.addRow(new Object[]{f.getName(), f.getFacultyId(), f.getDepartment(), f.getPassword()});

            JOptionPane.showMessageDialog(this,
                    "Faculty Added ✅\nUsername: " + f.getFacultyId() + "\nPassword: " + f.getPassword());

            fname.setText("");
            fid.setText("");
            dept.setText("");
        });

        facultyModel = new DefaultTableModel(new Object[]{"Name", "Faculty ID", "Department", "Password"}, 0);
        JTable table = new JTable(facultyModel);
        table.setRowHeight(22);

        JPanel top = new JPanel(new BorderLayout(10, 10));
        top.setBackground(Color.WHITE);
        top.add(form, BorderLayout.CENTER);

        JPanel btnWrap = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnWrap.setBackground(Color.WHITE);
        btnWrap.add(addBtn);

        top.add(btnWrap, BorderLayout.SOUTH);

        panel.add(top, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        return panel;
    }

    // -------------------- Rooms Tab --------------------
    private JPanel createRoomTab() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setBackground(Color.WHITE);

        JPanel form = new JPanel(new GridLayout(2, 2, 10, 10));
        form.setBackground(Color.WHITE);

        JTextField roomNo = new JTextField();
        JTextField cap = new JTextField();

        form.add(new JLabel("Room No"));
        form.add(roomNo);
        form.add(new JLabel("Capacity"));
        form.add(cap);

        JButton addBtn = new JButton("Add Room");
        stylePrimaryButton(addBtn);

        addBtn.addActionListener(e -> {
            try {
                String rn = roomNo.getText().trim();
                String c = cap.getText().trim();

                if (rn.isEmpty() || c.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Room No & Capacity required!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int capacity = Integer.parseInt(c);
                Room r = new Room(rn, capacity);

                DataStore.rooms.add(r);
                roomModel.addRow(new Object[]{r.getRoomNo(), r.getCapacity()});

                JOptionPane.showMessageDialog(this, "Room Added ✅");

                roomNo.setText("");
                cap.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Capacity must be a number!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        roomModel = new DefaultTableModel(new Object[]{"Room No", "Capacity"}, 0);
        JTable table = new JTable(roomModel);
        table.setRowHeight(22);

        JPanel top = new JPanel(new BorderLayout(10, 10));
        top.setBackground(Color.WHITE);
        top.add(form, BorderLayout.CENTER);

        JPanel btnWrap = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnWrap.setBackground(Color.WHITE);
        btnWrap.add(addBtn);

        top.add(btnWrap, BorderLayout.SOUTH);

        panel.add(top, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        return panel;
    }

    // -------------------- Allocation Tab --------------------
    private JPanel createAllocationTab() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setBackground(Color.WHITE);

        JButton allocateBtn = new JButton("Generate Allocation");
        styleWarningButton(allocateBtn);

        allocateBtn.addActionListener(e -> {
            if (DataStore.students.isEmpty() || DataStore.rooms.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Add Students & Rooms first!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            AllocationService.generateAllocation();
            refreshAllocationTable();
            JOptionPane.showMessageDialog(this, "Allocation Generated ✅");
        });

        allocationModel = new DefaultTableModel(new Object[]{"Roll No", "Name", "Room", "Seat"}, 0);
        JTable table = new JTable(allocationModel);
        table.setRowHeight(22);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        top.setBackground(Color.WHITE);
        top.add(allocateBtn);

        panel.add(top, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        return panel;
    }

    private void refreshAllocationTable() {
        allocationModel.setRowCount(0);
        DataStore.allocations.forEach(a -> allocationModel.addRow(
                new Object[]{a.getRollNo(), a.getStudentName(), a.getRoomNo(), a.getSeatNo()}
        ));
    }

    // -------------------- Button Styling Helpers --------------------
    private void stylePrimaryButton(JButton btn) {
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBackground(new Color(25, 118, 210));
        btn.setForeground(Color.WHITE);
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(180, 40));
    }

    private void styleWarningButton(JButton btn) {
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBackground(new Color(255, 152, 0));
        btn.setForeground(Color.WHITE);
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(200, 40));
    }
}

