package hospital.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class SearchRoom extends JFrame {

    Choice choice;
    JTable table;

    SearchRoom(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBounds(5,5,690,480);
        panel.setBackground(new Color(90,156,163));
        panel.setLayout(null);
        add(panel);

        JLabel For = new JLabel("Search For Room");
        For.setBounds(250,11,186,31);
        For.setForeground(Color.WHITE);
        For.setFont(new Font("Times New Roman",Font.BOLD,20));
        panel.add(For);

        JLabel status = new JLabel("Status : ");
        status.setBounds(70,70,80,20);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("Times New Roman",Font.BOLD,14));
        panel.add(status);

        choice = new Choice();
        choice.setBounds(170,70,120,20);
        choice.add("Available");
        choice.add("Occupied");
        panel.add(choice);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setFont(new Font("Times New Roman",Font.BOLD,14));
        scrollPane.setBounds(0, 130, 675, 240);
        panel.add(scrollPane);

        try{
            conn c = new conn();
            String q = "select * from room";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch (Exception e){
            e.printStackTrace();
        }

        JButton Search = new JButton("SEARCH");
        Search.setBounds(180,390,120,25);
        Search.setBackground(Color.BLACK);
        Search.setForeground(Color.WHITE);
        panel.add(Search);
        Search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String q = "select * from Room where Availability = '"+choice.getSelectedItem()+"'";
                try{
                    conn c = new conn();
                    ResultSet resultSet = c.statement.executeQuery(q);
                    table.setModel(DbUtils.resultSetToTableModel(resultSet));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton Back = new JButton("BACK");
        Back.setBounds(380,390,120,25);
        Back.setBackground(Color.BLACK);
        Back.setForeground(Color.WHITE);
        panel.add(Back);
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setUndecorated(true);
        setSize(700,500);
        setLayout(null);
        setLocation(300,190);
        setVisible(true);
    }
    public static void main(String[] args) {
        new SearchRoom();
    }
}