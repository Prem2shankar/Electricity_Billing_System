package electricity.billing.system;

import icon.splash.DataBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class Pay_Bill extends JFrame implements ActionListener {
    String meter;
    Choice monthChoice;
    JButton back, pay;
    JLabel statusTextField, unitTextField,totalBillTextField,nameTextField ,meter_NoTextField ;
    Pay_Bill(String meter){
        this.meter = meter;

        setLayout(null);
        setBounds(300,150,900,600);

        JLabel heading = new JLabel("Pay Bill");
        heading.setBounds(120,5,400,30);
        heading.setFont(new Font("Tahoma",Font.BOLD,20));
        add(heading);

        JLabel meter_no = new JLabel("Meter Number");
        meter_no.setBounds(35,80,200,20);
        add(meter_no);

         meter_NoTextField = new JLabel("");
        meter_NoTextField.setBounds(300,80,200,20);
        add(meter_NoTextField);

        JLabel name = new JLabel("Name");
        name.setBounds(35,140,200,20);
        add(name);

         nameTextField = new JLabel("");
        nameTextField.setBounds(300,140,200,20);
        add(nameTextField);

        JLabel month = new JLabel("Month");
        month.setBounds(35,200,200,20);
        add(month);

        monthChoice = new Choice();
        monthChoice.add("January");
        monthChoice.add("February");
        monthChoice.add("March");
        monthChoice.add("April");
        monthChoice.add("May");
        monthChoice.add("June");
        monthChoice.add("July");
        monthChoice.add("August");
        monthChoice.add("September");
        monthChoice.add("October");
        monthChoice.add("November");
        monthChoice.add("December");
        monthChoice.setBounds(300,200,200,20);
        add(monthChoice);

        JLabel unit = new JLabel("Unit");
        unit.setBounds(35,260,200,20);
        add(unit);

         unitTextField = new JLabel("");
        unitTextField.setBounds(300,260,260,20);
        add(unitTextField);

        JLabel totalBIll = new JLabel("Total BIll");
        totalBIll.setBounds(35,320,200,20);
        add(totalBIll);

         totalBillTextField = new JLabel("");
        totalBillTextField.setBounds(300,320,260,20);
        add(totalBillTextField);

        JLabel status = new JLabel("Status");
        status.setBounds(35,380,200,20);
        add(status);

         statusTextField = new JLabel("");
        statusTextField.setBounds(300,380,260,20);
        add(statusTextField);
        try{
            DataBase d = new DataBase();
            ResultSet resultSet = d.stm.executeQuery("select * from new_customer where meter_no ='"+meter+"'");
            while (resultSet.next()){
                meter_NoTextField.setText(resultSet.getString("meter_no"));
                nameTextField.setText(resultSet.getString("name"));
            }
        } catch (Exception E) {
            E.printStackTrace();
        }


        monthChoice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                DataBase d = new DataBase();
                try {
                    ResultSet resultSet = d.stm.executeQuery("select * from bill where meter_no = '"+meter+"' and month = '"+monthChoice.getSelectedItem()+"' ");
                    while (resultSet.next()) {
                            unitTextField.setText(resultSet.getString("unit"));
                            totalBillTextField.setText(resultSet.getString("total_bill"));
                            statusTextField.setText(resultSet.getString("status"));
                    }
                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

         pay = new JButton("Pay");
        pay.setBounds(100,460,100,25);
        pay.setBackground(Color.BLACK);
        pay.setForeground(Color.white);
        pay.addActionListener(this);
        add(pay);

         back = new JButton("Back");
        back.setBounds(230,460,100,25);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==pay){
            try{
                DataBase d = new DataBase();
                d.stm.executeUpdate("update bill set status ='Paid' where meter_no='"+meter+"' and month='"+monthChoice.getSelectedItem()+"'");
                setVisible(false);
                new Payment_Bill(meter);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource()==back) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Pay_Bill("");
    }
}
