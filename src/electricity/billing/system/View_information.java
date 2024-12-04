package electricity.billing.system;

import icon.splash.DataBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class View_information extends JFrame implements ActionListener {
    JLabel nameTextField, meter_noTextField, addressTextField, cityTextField, stateTextField, emailTextField, phoneTextField;
    JButton cancle;
    String meterNumber;
    View_information(String meterNumber){
        super("View Customer Information");
        this.meterNumber = meterNumber;

        setLayout(null);
        setBounds(350,150,850,650);
        getContentPane().setBackground(Color.white);

        JLabel heading = new JLabel("View Customer Information");
        heading.setBounds(250,0,500,40);
        heading.setFont(new Font("serif",Font.BOLD,20));
        add(heading);

        JLabel name = new JLabel("Name");
        name.setBounds(70,80,100,20);
        add(name);
        nameTextField = new JLabel("");
        nameTextField.setBounds(200,80,150,20);
        add(nameTextField);

        JLabel meter_no = new JLabel("Meter Number");
        meter_no.setBounds(70,140,100,20);
        add(meter_no);
        meter_noTextField = new JLabel("");
        meter_noTextField.setBounds(200,140,150,20);
        add(meter_noTextField);

        JLabel address = new JLabel("Address");
        address.setBounds(70, 200,100,20);
        add(address);
        addressTextField = new JLabel("");
        addressTextField.setBounds(200,200,150,20);
        add(addressTextField);

        JLabel city = new JLabel("City");
        city.setBounds(70,260,100,20);
        add(city);
        cityTextField = new JLabel("");
        cityTextField.setBounds(200,260,150,20);
        add(cityTextField);

        JLabel state = new JLabel("State");
        state.setBounds(500,80,100,20);
        add(state);
        stateTextField = new JLabel("");
        stateTextField.setBounds(600,80,150,20);
        add(stateTextField);

        JLabel email = new JLabel("Email");
        email.setBounds(500,140,100,20);
        add(email);
        emailTextField = new JLabel("");
        emailTextField.setBounds(600,140,150,20);
        add(emailTextField);

        JLabel phone = new JLabel("Phone");
        phone.setBounds(500,200,100,20);
        add(phone);
        phoneTextField = new JLabel("");
        phoneTextField.setBounds(600,200,150,20);
        add(phoneTextField);

        cancle = new JButton("Cancel");
        cancle.setBackground(new Color(24,118,242));
        cancle.setForeground(Color.white);
        cancle.setBounds(220,350,120,25);
        cancle.addActionListener(this);
        add(cancle);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/viewInfo.png"));
        Image image = imageIcon.getImage().getScaledInstance(600,300,Image.SCALE_DEFAULT);
        ImageIcon i = new ImageIcon(image);
        JLabel jLabel = new JLabel(i);
        jLabel.setBounds(100,320,600,300);
        add(jLabel);

        try{
            DataBase d = new DataBase();
            String query = "select * from new_customer where meter_no = '"+meterNumber+"'";
            ResultSet resultSet = d.stm.executeQuery(query);
            if(resultSet.next()){
                nameTextField.setText(resultSet.getString("Name"));
                meter_noTextField.setText(resultSet.getString("meter_no"));
                addressTextField.setText(resultSet.getString("address"));
                cityTextField.setText(resultSet.getString("city"));
                stateTextField.setText(resultSet.getString("state"));
                emailTextField.setText(resultSet.getString("email"));
                phoneTextField.setText(resultSet.getString("phone_no"));
            }

        }catch (Exception E){
            E.printStackTrace();
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==cancle){
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new View_information("");
    }
}
