package hospital.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ALL_Patient_Info extends JFrame {

    ALL_Patient_Info(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBounds(0,0,800,500);
        panel.setBackground(new Color(90,156,163));
        panel.setLayout(null);
        add(panel);

        JTable table = new JTable();
//        table.setBounds(10,40,900,450);
//        table.setBackground(new Color(90,156,163));
//        table.setFont(new Font("Times New Roman",Font.BOLD,14));
//        panel.add(table);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setFont(new Font("Times New Roman",Font.BOLD,12));
        scrollPane.setBounds(10, 40, 770, 360);
        panel.add(scrollPane);

        try{
            conn c = new conn();
            String q = "select * from Patient_Info";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JButton button = new JButton("BACK");
        button.setBounds(310,420,120,30);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        panel.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setUndecorated(true);
        setSize(800,500);
        setLayout(null);
        setLocation(250,200);
        setVisible(true);
    }
    public static void main(String[] args){
        new ALL_Patient_Info();
    }
}