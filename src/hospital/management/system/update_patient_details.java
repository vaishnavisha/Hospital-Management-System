package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class update_patient_details extends JFrame {

    update_patient_details(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBounds(5,5,940,440);
        panel.setBackground(new Color(90,156,163));
        panel.setLayout(null);
        add(panel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/updated.png"));
        Image image = imageIcon.getImage().getScaledInstance(300,300,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(500,60,300,300);
        panel.add(label);

        JLabel label1 = new JLabel("Update Patient Details");
        label1.setBounds(124,11,260,25);
        label1.setFont(new Font("Times New Roman",Font.BOLD,20));
        label1.setForeground(Color.WHITE);
        panel.add(label1);

        JLabel label2 = new JLabel("Name : ");
        label2.setBounds(25,88,150,14);
        label2.setFont(new Font("Times New Roman",Font.BOLD,14));
        label2.setForeground(Color.WHITE);
        panel.add(label2);

        Choice choice = new Choice();
        choice.setBounds(248,85,140,25);
        panel.add(choice);

        try{
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select * from patient_info");
            while (resultSet.next()){
                choice.add(resultSet.getString("Name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel label3 = new JLabel("Room Number : ");
        label3.setBounds(25,129,150,14);
        label3.setFont(new Font("Times New Roman",Font.BOLD,14));
        label3.setForeground(Color.WHITE);
        panel.add(label3);

        JTextField textFieldR = new JTextField();
        textFieldR.setBounds(248,129,140,20);
        panel.add(textFieldR);

        JLabel label4 = new JLabel("In-Time : ");
        label4.setBounds(25,174,150,14);
        label4.setFont(new Font("Times New Roman",Font.BOLD,14));
        label4.setForeground(Color.WHITE);
        panel.add(label4);

        JTextField textFieldINTime = new JTextField();
        textFieldINTime.setBounds(248,174,140,20);
        panel.add(textFieldINTime);

        JLabel label5 = new JLabel("Amount Paid (Rs) : ");
        label5.setBounds(25,216,150,14);
        label5.setFont(new Font("Times New Roman",Font.BOLD,14));
        label5.setForeground(Color.WHITE);
        panel.add(label5);

        JTextField textFieldAmount = new JTextField();
        textFieldAmount.setBounds(248,216,140,20);
        panel.add(textFieldAmount);

        JLabel label6 = new JLabel("Pending Amount (Rs) : ");
        label6.setBounds(25,261,150,14);
        label6.setFont(new Font("Times New Roman",Font.BOLD,14));
        label6.setForeground(Color.WHITE);
        panel.add(label6);

        JTextField textFieldPending = new JTextField();
        textFieldPending.setBounds(248,261,140,20);
        panel.add(textFieldPending);

        JButton check = new JButton("CHECK");
        check.setBounds(281,378,89,23);
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        panel.add(check);
//        check.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String id = choice.getSelectedItem();
//                String q = "select * from patient_info where Name = '"+id+"'";
//                try{
//                    conn c = new conn();
//                    ResultSet resultSet = c.statement.executeQuery(q);
//                    while(resultSet.next()){
//                        textFieldR.setText(resultSet.getString("Room_Number"));
//                        textFieldINTime.setText(resultSet.getString("Time"));
//                        textFieldAmount.setText(resultSet.getString("Deposite"));
//                    }
//                    ResultSet resultSet1 = c.statement.executeQuery("select * from room where room_no = '"+textFieldR.getText()+"'");
//                    while (resultSet1.next()){
//                        String price = resultSet1.getString("Price");
//                        int amountPaid = Integer.parseInt(price) - Integer.parseInt(textFieldAmount.getText());
//                        textFieldPending.setText(""+amountPaid);
//                    }
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//            }
//        });
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedName = choice.getSelectedItem();
                String patientQuery = "SELECT * FROM patient_info WHERE Name = '" + selectedName + "'";

                try {
                    conn c = new conn();
                    ResultSet rsPatient = c.statement.executeQuery(patientQuery);

                    String room = "", deposit = "", intime = "";

                    if (rsPatient.next()) {
                        room = rsPatient.getString("Room_Number");
                        deposit = rsPatient.getString("Deposite");
                        intime = rsPatient.getString("Time");

                        textFieldR.setText(room);
                        textFieldAmount.setText(deposit);
                        textFieldINTime.setText(intime);
                    } else {
                        JOptionPane.showMessageDialog(null, "Patient not found.");
                        return;
                    }

                    if (room != null && !room.isEmpty()) {
                        ResultSet rsRoom = c.statement.executeQuery("SELECT * FROM Room WHERE room_no = '" + room + "'");
                        if (rsRoom.next()) {
                            String priceStr = rsRoom.getString("Price");
                            if (priceStr != null && deposit != null && !priceStr.trim().isEmpty() && !deposit.trim().isEmpty()) {
                                try {
                                    int price = Integer.parseInt(priceStr.trim());
                                    int paid = Integer.parseInt(deposit.trim());
                                    int pending = price - paid;
                                    textFieldPending.setText(String.valueOf(pending));
                                } catch (NumberFormatException nfe) {
                                    JOptionPane.showMessageDialog(null, "Price or Deposit is not a valid number.");
                                    textFieldPending.setText("");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Price or Deposit is missing.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Room not found in Room table.");
                        }
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "An error occurred while checking patient details.");
                }
            }
        });

        JButton update = new JButton("UPDATE");
        update.setBounds(56,378,89,23);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        panel.add(update);
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    conn c = new conn();
                    String q = choice.getSelectedItem();
                    String room = textFieldR.getText();
                    String time = textFieldINTime.getText();
                    String amount = textFieldAmount.getText();
                    c.statement.executeUpdate("update patient_info set Room_Number = '"+room+"', Time = '"+time+"', Deposite = '"+amount+"' where Name = '"+q+"'");
                    JOptionPane.showMessageDialog(null,"Updated Successfully");
                    setVisible(false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton Back = new JButton("BACK");
        Back.setBounds(166,378,89,23);
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
        setSize(950,450);
        setLayout(null);
        setLocation(200,200);
        setVisible(true);
    }
    public static void main(String[] args){
        new update_patient_details();
    }
}