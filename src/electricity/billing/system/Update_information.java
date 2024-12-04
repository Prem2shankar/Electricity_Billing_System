package electricity.billing.system;

import icon.splash.DataBase;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Update_information extends JFrame implements ActionListener{
    String meter_no;
    JTextField addressTextField, cityTextField, stateTextField, emailTextField, phoneTextField ;
    JLabel nameTextField,meter_noTextField;
    JButton cancle, update;
    Update_information(String meter_no){
        super("Update Information");
        this.meter_no=meter_no;

        setBounds(400,150,777,450);
        getContentPane().setBackground(new Color(229,255,227));
        setLayout(null);

        JLabel heading = new JLabel("Update Customer Information");
        heading.setBounds(50,10,400,40);
        heading.setFont(new Font("serif",Font.BOLD,20));
        add(heading);

        JLabel name = new JLabel("Name");
        name.setBounds(30,70,100,20);
        add(name);
        nameTextField = new JLabel("");
        nameTextField.setBounds(150,70,200,20);
        add(nameTextField);

        JLabel meterNo = new JLabel("Meter Number");
        meterNo.setBounds(30,110,100,20);
        add(meterNo);
        meter_noTextField = new JLabel("");
        meter_noTextField.setBounds(150,110,100,20);
        add(meter_noTextField);

        JLabel address = new JLabel("Address");
        address.setBounds(30,150,100,20);
        add(address);
        addressTextField = new JTextField("");
        addressTextField.setBounds(150,150,200,20);
        add(addressTextField);

        JLabel city = new JLabel("City");
        city.setBounds(30,190,100,20);
        add(city);
        cityTextField = new JTextField("");
        cityTextField.setBounds(150,190,200,20);
        add(cityTextField);

        JLabel state = new JLabel("State");
        state.setBounds(30,230,100,20);
        add(state);
        stateTextField = new JTextField("");
        stateTextField.setBounds(150,230,200,20);
        add(stateTextField);

        JLabel email = new JLabel("Email");
        email.setBounds(30,270,100,20);
        add(email);
        emailTextField = new JTextField("");
        emailTextField.setBounds(150,270,200,20);
        add(emailTextField);

        JLabel phone = new JLabel("Phone");
        phone.setBounds(30,310,100,20);
        add(phone);
        phoneTextField = new JTextField("");
        phoneTextField.setBounds(150,310,200,20);
        add(phoneTextField);

        try{
            DataBase d  = new DataBase();
            ResultSet resultSet = d.stm.executeQuery("select * from new_customer where meter_no='"+meter_no+"' ");
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

        update = new JButton("UPDATE");
        update.setBounds(50,360,120,25);
        update.setBackground(new Color(33,106,145));
        update.setForeground(Color.white);
        update.addActionListener(this);
        add(update);

        cancle = new JButton("CANCEL");
        cancle.setBackground(new Color(33,106,145));
        cancle.setForeground(Color.white);
        cancle.setBounds(200,360,120,25);
        cancle.addActionListener(this);
        add(cancle);


        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/update.png"));
        Image image = imageIcon.getImage().getScaledInstance(400,410,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel jLabel = new JLabel(imageIcon1);
        jLabel.setBounds(360,0,400,410);
        add(jLabel);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==cancle){
            setVisible(false);
        } else if (e.getSource()==update) {
               // String name = nameTextField.getText();
               // String meterNo=meter_noTextField.getText();
                String address = addressTextField.getText();
                String city = cityTextField.getText();
                String state = stateTextField.getText();
                String email = emailTextField.getText();
                String phone = phoneTextField.getText();
                String query = "update new_customer set address='"+address+"', city='"+city+"', state='"+state+"', email='"+email+"', phone_no='"+phone+"' where meter_no='"+meter_no+"'";
            try{
                DataBase d = new DataBase();
                d.stm.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Information updated successfully");
                setVisible(false);
            }catch (Exception E){
                E.printStackTrace();
            }

        }

    }

    public static void main(String[] args) {
        new Update_information("");
    }

}
