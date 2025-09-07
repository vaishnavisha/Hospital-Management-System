package hospital.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Department extends JFrame {
    Department(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBounds(5,5,690,490);
        panel.setLayout(null);
        panel.setBackground(new Color(90,156,163));
        add(panel);

//        JTable table = new JTable();
//        table.setBounds(0,40,700,400);
//        table.setFont(new Font("Times New Roman",Font.BOLD,14));
//        table.setBackground(new Color(90,156,163));
//        panel.add(table);

        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 40, 660, 300);
        panel.add(scrollPane);

        try{
            conn c = new conn();
            String q = "select * from department";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch (Exception e){
            e.printStackTrace();
        }

//        JLabel label1 = new JLabel("Department");
//        label1.setBounds(125,11,105,20);
//        label1.setFont(new Font("Times New Roman",Font.BOLD,14));
//        panel.add(label1);
//
//        JLabel label2 = new JLabel("Phone Number");
//        label2.setBounds(400,11,105,20);
//        label2.setFont(new Font("Times New Roman",Font.BOLD,14));
//        panel.add(label2);

        JButton b1 = new JButton("BACK");
        b1.setBounds(270, 390, 130, 30);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        panel.add(b1);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setUndecorated(true);
        setSize(700,500);
        setLayout(null);
        setLocation(350,200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Department();
    }
}