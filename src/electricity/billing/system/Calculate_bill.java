package electricity.billing.system;

import icon.splash.DataBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.sql.ResultSet;

public class Calculate_bill extends JFrame implements ActionListener {
JLabel heading, meterNumber, name, nameText, address, addressText, unit,month;
Choice meterNumberText, monthText;
JTextField unitText;
JButton submit, cancle;
    Calculate_bill(){
        super("Bill Calculation");

        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBackground(new Color(214,195,247));
        add(jPanel);

        heading = new JLabel("Calculate Electricity BIll");
        heading.setBounds(70,10,300,20);
        heading.setFont(new Font("Tahoma", Font.BOLD,20));
        jPanel.add(heading);

        meterNumber = new JLabel("Meter Number");
        meterNumber.setBounds(50,80,100,20);
        meterNumber.setFont(new Font("Tahoma",Font.BOLD,12));
        jPanel.add(meterNumber);

        meterNumberText = new Choice();
        try{
            DataBase d = new DataBase();
            ResultSet resultSet = d.stm.executeQuery("select * from new_customer");
            while (resultSet.next()){
                meterNumberText.add(resultSet.getString("meter_no"));
            }
        }catch (Exception E){
            E.printStackTrace();
        }
        meterNumberText.setBounds(180,80,100,20);
        jPanel.add(meterNumberText);

        name = new JLabel("Name");
        name.setBounds(50,120,100,20);
        name.setFont(new Font("Tahoma", Font.BOLD,12));
        jPanel.add(name);

        nameText = new JLabel("");
        try{
            DataBase d = new DataBase();
            ResultSet resultSet = d.stm.executeQuery("select * from new_customer");
            while (resultSet.next()){
                nameText.setText(resultSet.getString("name"));
            }
        }catch (Exception E){
            E.printStackTrace();
        }
        nameText.setBounds(180,120,100,20);
        nameText.setFont(new Font("Tahoma", Font.BOLD,12));
        jPanel.add(nameText);

        address = new JLabel("Address");
        address.setBounds(50,160,100,20);
        address.setFont(new Font("Tahoma", Font.BOLD, 12));
        jPanel.add(address);

        addressText = new JLabel();
        try{
            DataBase d = new DataBase();
            ResultSet resultSet = d.stm.executeQuery("select * from new_customer where meter_no= '"+meterNumberText.getSelectedItem()+"' ");
            while (resultSet.next()){
                nameText.setText(resultSet.getString("name"));
                addressText.setText(resultSet.getString("address"));
            }
        }catch (Exception E){
            E.printStackTrace();
        }
        addressText.setBounds(180,160,100,20);
        addressText.setFont(new Font("Tahoma", Font.BOLD,12));
        jPanel.add(addressText);

        unit = new JLabel("Unit Consumed");
        unit.setBounds(50,200,100,20);
        unit.setFont(new Font("Tahoma", Font.BOLD, 12));
        jPanel.add(unit);

        unitText = new JTextField();
        unitText.setBounds(180,200,150,20);
        unitText.setFont(new Font("Tahoma", Font.BOLD, 12));
        jPanel.add(unitText);

        month = new JLabel("Month");
        month.setBounds(50,240,100,20);
        month.setFont(new Font("Tahoma", Font.BOLD,12));
        jPanel.add(month);

        monthText = new Choice();
        monthText.addItem("January");
        monthText.addItem("February");
        monthText.addItem("March");
        monthText.addItem("April");
        monthText.addItem("May");
        monthText.addItem("June");
        monthText.addItem("July");
        monthText.addItem("August");
        monthText.addItem("September");
        monthText.addItem("October");
        monthText.addItem("November");
        monthText.addItem("December");

        monthText.setBounds(180,240,150,20);
        monthText.setFont(new Font("Tahoma", Font.BOLD,12));
        jPanel.add(monthText);

        submit = new JButton("Submit");
        submit.setBounds(50,280,100,20);
        submit.setBackground(Color.BLACK);
        submit.setFont(new Font("Tahoma", Font.BOLD,12));
        submit.setForeground(Color.white);
        submit.addActionListener(this);
        jPanel.add(submit);

        cancle = new JButton("Cancle");
        cancle.setBounds(180,280,100,20);
        cancle.setBackground(Color.BLACK);
        cancle.setFont(new Font("Tahoma",Font.BOLD,12));
        cancle.setForeground(Color.white);
        cancle.addActionListener(this);
        jPanel.add(cancle);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/budget.png"));
        Image image = imageIcon.getImage().getScaledInstance(250,200,Image.SCALE_DEFAULT);
        ImageIcon i = new ImageIcon(image);
        JLabel jLabel = new JLabel(i);
        add(jLabel,"East");

        meterNumberText.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    DataBase d = new DataBase();
                    ResultSet resultSet = d.stm.executeQuery("select * from new_customer where meter_no = '"+meterNumberText.getSelectedItem()+"' ");
                    while (resultSet.next()){
                        nameText.setText(resultSet.getString("name"));
                        addressText.setText(resultSet.getString("address"));
                    }
                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        //setLayout(new BorderLayout());
        add(jPanel,"Center");
        setSize(650,400);
        setLocation(400,200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==submit){
            String smeter = meterNumberText.getSelectedItem();
            String sunit = unitText.getText();
            String smonth = monthText.getSelectedItem();

            int totalbill = 0;
            int units = Integer.parseInt(sunit);
            String query_tax = "select * from tax";
            try{
                DataBase d = new DataBase();
                ResultSet resultSet = d.stm.executeQuery(query_tax);
                while (resultSet.next()){
                    totalbill = units * Integer.parseInt(resultSet.getString("cost_per_unit"));
                    totalbill += Integer.parseInt(resultSet.getString("meter_rent"));
                    totalbill += Integer.parseInt(resultSet.getString("service_charge"));
                    totalbill += Integer.parseInt(resultSet.getString("service_tax"));
                    totalbill += Integer.parseInt(resultSet.getString("swachh_bharat"));
                    totalbill += Integer.parseInt(resultSet.getString("fixed_tax"));
                }
            }catch (Exception E){
                E.printStackTrace();
            }
            String query_total_Bill = "insert into bill values('"+smeter+"','"+smonth+"','"+sunit+"','"+totalbill+"','Not Paid')";
            try{
                DataBase d = new DataBase();
                d.stm.executeUpdate(query_total_Bill);
                JOptionPane.showMessageDialog(null,"Customer BIll Updated Successfully");
                setVisible(false);
            }catch (Exception E){
                E.printStackTrace();
            }

        }else if(e.getSource()==cancle){
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Calculate_bill();
    }
}
