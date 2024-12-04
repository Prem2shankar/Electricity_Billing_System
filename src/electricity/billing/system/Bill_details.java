package electricity.billing.system;

import icon.splash.DataBase;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class Bill_details extends JFrame {
    String meter;
    Bill_details(String meter){
        this.meter = meter;

        setSize(700,650);
        setLocation(400,150);
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JTable jTable = new JTable();
        try{
            DataBase d = new DataBase();
            ResultSet resultSet = d.stm.executeQuery("select * from bill where meter_no = '"+meter+"'");
            jTable.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch (Exception E){
            E.printStackTrace();
        }
        JScrollPane  scrollPane = new JScrollPane(jTable);
        scrollPane.setBounds(20,20,620,580);
        add(scrollPane);

        setVisible(true);
    }
    public static void main(String[] args) {
        new Bill_details("");
    }
}
