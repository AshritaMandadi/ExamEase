package ui;

import service.DataStore;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FacultyPanel extends JPanel {

    private DefaultTableModel allocationModel;

    public FacultyPanel() {
        setLayout(new BorderLayout(12, 12));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(new Color(245, 245, 245));

        JLabel heading = new JLabel("Faculty Panel - View Allocation");
        heading.setFont(new Font("Arial", Font.BOLD, 18));
        add(heading, BorderLayout.NORTH);

        allocationModel = new DefaultTableModel(new Object[]{"Roll No", "Name", "Room", "Seat"}, 0);
        JTable table = new JTable(allocationModel);
        table.setRowHeight(22);

        JScrollPane scroll = new JScrollPane(table);

        JButton refreshBtn = new JButton("Refresh Allocation");
        refreshBtn.setFocusPainted(false);
        refreshBtn.setFont(new Font("Arial", Font.BOLD, 14));
        refreshBtn.setBackground(new Color(25, 118, 210));
        refreshBtn.setForeground(Color.WHITE);
        refreshBtn.setOpaque(true);
        refreshBtn.setBorderPainted(false);
        refreshBtn.setPreferredSize(new Dimension(200, 40));

        refreshBtn.addActionListener(e -> loadData());

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.setBackground(new Color(245, 245, 245));
        bottom.add(refreshBtn);

        add(scroll, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        loadData();
    }

    private void loadData() {
        allocationModel.setRowCount(0);
        DataStore.allocations.forEach(a -> allocationModel.addRow(
                new Object[]{a.getRollNo(), a.getStudentName(), a.getRoomNo(), a.getSeatNo()}
        ));
    }
}

