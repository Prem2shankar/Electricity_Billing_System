package electricity.billing.system;

import icon.splash.DataBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NewCustomer extends JFrame implements ActionListener {

    JLabel newCustomer, meterNo, address, city, state, email, phone, meterNojLabel;
    JTextField newCustomerTextField, meterNoTextField, addressTextField, cityTextField, stateTextField, emailTextField, phoneTextField;
    JButton jnext, jcancle;
    NewCustomer(){
        super("New Customer Details");

        setSize(700,500);
        setLocation(400,200);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBackground(new Color(252,186,3));
        add(jPanel);

        JLabel jLabel =new JLabel("New Customer");
        jLabel.setBounds(180,10,200,20);
        jLabel.setFont(new Font("Tahoma",Font.BOLD,20));
        jPanel.add(jLabel);

        newCustomer = new JLabel("New Customer");
        newCustomer.setBounds(50,80,100,20);
        newCustomer.setFont(new Font("Tahoma",Font.BOLD,12));
        jPanel.add(newCustomer);

        newCustomerTextField = new JTextField();
        newCustomerTextField.setBounds(180,80,150,20);
        jPanel.add(newCustomerTextField);

        meterNo  = new JLabel("Meter Number");
        meterNo.setBounds(50,120,100,20);
        meterNo.setFont(new Font("Tahoma", Font.BOLD,12));
        jPanel.add(meterNo);

        meterNojLabel = new JLabel("");
        meterNojLabel.setBounds(180,120,150,20);
        jPanel.add(meterNojLabel);

        Random ran = new Random();
        Long number = ran.nextLong() % 1000000;
        meterNojLabel.setText(""+Math.abs(number));

        address = new JLabel("Address");
        address.setBounds(50,160,100, 20);
        address.setFont(new Font("Tahoma",Font.BOLD,12));
        jPanel.add(address);

        addressTextField = new JTextField();
        addressTextField.setBounds(180,160,150,20);
        jPanel.add(addressTextField);

        city = new JLabel("City");
        city.setBounds(50,200,100,20);
        city.setFont(new Font("Tahoma", Font.BOLD,12));
        jPanel.add(city);

        cityTextField = new JTextField();
        cityTextField.setBounds(180,200,150,20);
        jPanel.add(cityTextField);

        state = new JLabel("State");
        state.setBounds(50,240,100,20);
        state.setFont(new Font("Tahoma",Font.BOLD,12));
        jPanel.add(state);

        stateTextField = new JTextField();
        stateTextField.setBounds(180,240,150,20);
        jPanel.add(stateTextField);

        email = new JLabel("Email");
        email.setBounds(50,280,100,20);
        email.setFont(new Font("Tahoma", Font.BOLD, 12));
        jPanel.add(email);

        emailTextField = new JTextField();
        emailTextField.setBounds(180,280,150,20);
        jPanel.add(emailTextField);

        phone = new JLabel("Phone");
        phone.setBounds(50,320,100,20);
        phone.setFont(new Font("Tahoma",Font.BOLD,12));
        jPanel.add(phone);

        phoneTextField = new JTextField();
        phoneTextField.setBounds(180,320,150,20);
        jPanel.add(phoneTextField);

        jnext = new JButton("Next");
        jnext.setBounds(120,390,100,25);
        jnext.setBackground(Color.BLACK);
        jnext.setForeground(Color.white);
        jnext.addActionListener(this);
        jPanel.add(jnext);

        jcancle = new JButton("Cancle");
        jcancle.setBounds(230,390,100,25);
        jcancle.setBackground(Color.BLACK);
        jcancle.setForeground(Color.white);
        jcancle.addActionListener(this);
        jPanel.add(jcancle);


        setLayout(new BorderLayout());
        add(jPanel,"Center");

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/boy.png"));
        Image image = imageIcon.getImage().getScaledInstance(230,200,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel jLabel1 = new JLabel(imageIcon1);
        add(jLabel1,"West");

          setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            if(e.getSource()==jnext){
                String sname = newCustomerTextField.getText();
                String smeter = meterNojLabel.getText();
                String saddress = addressTextField.getText();
                String scity = cityTextField.getText();
                String sstate = stateTextField.getText();
                String semail = emailTextField.getText();
                String sphone = phoneTextField.getText();
                String query_customer =  "Insert into new_customer values('"+sname+"','"+smeter+"','"+saddress+"','"+scity+"','"+sstate+"','"+semail+"','"+sphone+"')";
                String query_signup = "Insert into SignUp values('"+smeter+"','','"+sname+"','','')";
                try{
                    DataBase d = new DataBase();
                    d.stm.executeUpdate(query_customer);
                    d.stm.executeUpdate(query_signup);
                    JOptionPane.showMessageDialog(null, "Customer details added successfully ");
                    setVisible(false);
                    new MeterInformation(smeter);
                }catch (Exception E){
                    E.printStackTrace();
                }
            }else if (e.getSource()==jcancle){
                setVisible(false);
            }

        }catch (Exception E){
            E.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new NewCustomer();
    }
}
