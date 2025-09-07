package hospital.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class Employee_info extends JFrame{
    Employee_info(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBounds(0,0,720,470);
        panel.setBackground(new Color(109,164,170));
        panel.setLayout(null);
        add(panel);

        JTable table = new JTable();
//        table.setBounds(10,34,660,300);
//        table.setBackground(new Color(109,164,170));
//        table.setFont(new Font("Times New Roman",Font.BOLD,14));
//        panel.add(table);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 60, 700, 320);
        scrollPane.setFont(new Font("Times New Roman",Font.BOLD,14));
        panel.add(scrollPane);

//        try{
//            conn c = new conn();
//            String q = "select * from EMP_INFO";
//            ResultSet resultSet = c.statement.executeQuery(q);
//            table.setModel(DbUtils.resultSetToTableModel(resultSet));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        JButton button = new JButton("BACK");
        button.setBounds(300, 400, 120, 30);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        panel.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        try {
            conn c = new conn();
            String q = "SELECT * FROM EMP_INFO";
            ResultSet resultSet = c.statement.executeQuery(q);

            table.setModel(DbUtils.resultSetToTableModel(resultSet));

            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnCount = rsmd.getColumnCount();

            int x = 10;
            int y = 30;
            int width = 110;

            for (int i = 1; i <= columnCount; i++) {
                String columnName = rsmd.getColumnName(i);

                JLabel label = new JLabel(columnName);
                label.setBounds(x, y, width, 20);
                label.setFont(new Font("Times New Roman", Font.BOLD, 14));
                panel.add(label);

                x += width;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        setUndecorated(true);
        setSize(720,470);
        setLocation(250,200);
        setLayout(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Employee_info();
    }
}