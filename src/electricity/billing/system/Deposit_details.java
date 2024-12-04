package electricity.billing.system;

import com.mysql.cj.xdevapi.Table;
import icon.splash.DataBase;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Deposit_details extends JFrame implements ActionListener {
    Choice searchMeterChoice, searchNameChoice;
    JButton search,print, close;
    JTable table;
    Deposit_details(){
        super("Deposit Details");
        getContentPane().setBackground(new Color(192,186,254));
        setSize(700,500);
        setLocation(400,200);
        setLayout(null);

        JLabel searchMeter = new JLabel("Search By Meter Number");
        searchMeter.setBounds(20,20,150,20);
        add(searchMeter);
        searchMeterChoice = new Choice();
        searchMeterChoice.setBounds(180,20,100,20);
        add(searchMeterChoice);

        try{
            DataBase d = new DataBase();
            String query = "Select * from bill";
            ResultSet resultSet = d.stm.executeQuery(query);
            while (resultSet.next()){
                searchMeterChoice.add(resultSet.getString("meter_no"));
            }
        }catch (Exception E){
            E.printStackTrace();
        }

        JLabel searchName = new JLabel("Search By Month");
        searchName.setBounds(420,20,100,20);
        add(searchName);
        searchNameChoice = new Choice();
        searchNameChoice.setBounds(530,20,100,20);
        searchNameChoice.add("January");
        searchNameChoice.add("February");
        searchNameChoice.add("March");
        searchNameChoice.add("April");
        searchNameChoice.add("May");
        searchNameChoice.add("June");
        searchNameChoice.add("July");
        searchNameChoice.add("August");
        searchNameChoice.add("September");
        searchNameChoice.add("October");
        searchNameChoice.add("November");
        searchNameChoice.add("December");
        add(searchNameChoice);

        search = new JButton("Search");
        search.setBackground(Color.white);
        search.setBounds(20,70,80,20);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBackground(Color.white);
        print.setBounds(120,70,80,20);
        print.addActionListener(this);
        add(print);

        close = new JButton("Close");
        close.setBounds(600,70,80,20);
        close.setBackground(Color.WHITE);
        close.addActionListener(this);
        add(close);

        table = new JTable();
        try{
            DataBase d = new DataBase();
            ResultSet resultSet = d.stm.executeQuery("Select * from bill");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch (Exception E){
            E.printStackTrace();
        }
        JScrollPane jScrollPane = new JScrollPane(table);
        jScrollPane.setBounds(0,100,700,500);
        jScrollPane.setBackground(Color.white);
        add(jScrollPane);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==search){
            String meterNo = searchMeterChoice.getSelectedItem();
            String searchName = searchNameChoice.getSelectedItem();
            String query = "Select * from bill where meter_no='"+meterNo+"' and month='"+searchName+"'  ";
            try {
                DataBase d = new DataBase();
                ResultSet resultSet = d.stm.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource()==print) {
            try {
                table.print();
            }catch (Exception E){
                E.printStackTrace();
            }
        } else if (e.getSource()==close) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Deposit_details();
    }
}
