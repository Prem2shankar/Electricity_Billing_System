package electricity.billing.system;

import icon.splash.DataBase;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Customer_details extends JFrame implements ActionListener {
    Choice searchByMeterChoice, searchByNameChoice;
    JTable table;
    JButton search, print, close;
    Customer_details() {
        super("Customer Details");
        getContentPane().setBackground(new Color(192,186,254));
        setSize(700,500);
        setLocation(400,200);
        setLayout(null);

        JLabel searchMeter = new JLabel("Search By Meter Number");
        searchMeter.setBounds(20,20,150,20);
        add(searchMeter);

        searchByMeterChoice = new Choice();
        searchByMeterChoice.setBounds(180,20,100,20);
        add(searchByMeterChoice);

        try{
            DataBase c= new DataBase();
            ResultSet resultSet = c.stm.executeQuery("select * from new_customer");
            while (resultSet.next()){
                searchByMeterChoice.add(resultSet.getString("meter_no"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        JLabel searchName = new JLabel("Search By Name");
        searchName.setBounds(400,20,100,20);
        add(searchName);

        searchByNameChoice = new Choice();
        searchByNameChoice.setBounds(520,20,100,20);
        add(searchByNameChoice);

        try{
            DataBase c= new DataBase();
            ResultSet resultSet = c.stm.executeQuery("select * from new_customer");
            while (resultSet.next()){
                searchByNameChoice.add(resultSet.getString("name"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        search = new JButton("Search");
        search.setBounds(20,70,80,20);
        search.setBackground(Color.white);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBounds(120,70,80,20);
        print.setBackground(Color.white);
        print.addActionListener(this);
        add(print);

        close = new JButton("Close");
        close.setBounds(600,70,80,20);
        close.setBackground(Color.white);
        close.addActionListener(this);
        add(close);

        table = new JTable();
        try{
            DataBase d = new DataBase();
            ResultSet resultSet = d.stm.executeQuery("Select * from new_customer");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch (Exception E){
            E.printStackTrace();
        }
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,100,700,500);
        scrollPane.setBackground(Color.white);
        add(scrollPane);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==search){
            try{
                DataBase d = new DataBase();
                ResultSet resultSet = d.stm.executeQuery("select * from new_customer where meter_no = '"+searchByMeterChoice.getSelectedItem()+"' and Name = '"+searchByNameChoice.getSelectedItem()+"'            ");
                table.setModel((DbUtils.resultSetToTableModel(resultSet)));
            }catch (Exception E ){
                E.printStackTrace();
            }
        } else if (e.getSource()==print) {
            try{
                table.print();
            }catch (Exception E){
                E.printStackTrace();
            }
        } else if (e.getSource()==close) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Customer_details();
    }
}
