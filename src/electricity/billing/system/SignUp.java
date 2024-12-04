package electricity.billing.system;


import icon.splash.DataBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class SignUp extends JFrame implements ActionListener{
    Choice choice;
    JLabel meaterNumber, employeeId, userName, name, password;
    JButton create, back;
    JTextField meaterTextField, employeeText, userNameText, nameTextField;
    JPasswordField passwordText;

    SignUp(){
        super("SignUp Page");
        getContentPane().setBackground(new Color(168,203,255));

        JLabel j = new JLabel("Create-Account");
        j.setBounds(5,0,125,20);
        add(j);


        JLabel createAccount = new JLabel("Create Account As ");
        createAccount.setBounds(30,50,125,20);
        add(createAccount);
        choice = new Choice();
        choice.add("Admin");
        choice.add("Customer");
        choice.setBounds(170,50,120,20);
        add(choice);

        meaterNumber = new JLabel("Meater Number");
        meaterNumber.setBounds(30,100,125,20);
        meaterNumber.setVisible(false);
        add(meaterNumber);
        meaterTextField = new JTextField();
        meaterTextField.setBounds(170,100,125,20);
        meaterNumber.setVisible(false);
        add(meaterTextField);

        employeeId = new JLabel("Employer ID ");
        employeeId.setBounds(30,100,125,20);
        employeeId.setVisible(true);
        add(employeeId);
        employeeText = new JTextField();
        employeeText.setBounds(170,100,125,20);
        employeeText.setVisible(true);
        add(employeeText);

        choice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {//user select admin or customer will come into the e, from the e we can understand user select admin or customer
                String user = choice.getSelectedItem();
                if(user.equals("Customer")){
                    employeeId.setVisible(false);
                    employeeText.setVisible(false);
                    meaterNumber.setVisible(true);
                    nameTextField.setEditable(false);
                    meaterTextField.setVisible(true);
                } else if (user.equals("Admin")) {
                    employeeId.setVisible(true);
                    employeeText.setVisible(true);
                    meaterNumber.setVisible(false);
                    meaterTextField.setVisible(false);
                }
            }
        });

        userName = new JLabel("UserName");
        userName.setBounds(30,140,125,20);
        add(userName);
        userNameText = new JTextField();
        userNameText.setBounds(170,140,125,20);
        add(userNameText);

        name = new JLabel("Name");
        name.setBounds(30,180,125,20);
        add(name);
        nameTextField = new JTextField("");
        nameTextField.setBounds(170,180,125,20);
        add(nameTextField);

        meaterTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try{
                    DataBase d = new DataBase();
                    String query = "Select * from Signup where meter_no='"+meaterTextField.getText()+"' ";
                    ResultSet resultSet = d.stm.executeQuery(query);
                    if(resultSet.next()){
                        nameTextField.setText(resultSet.getString("name"));
                    }
                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        password = new JLabel("Password");
        password.setBounds(30,220,125,20);
        add(password);
        passwordText = new JPasswordField();
        passwordText.setBounds(170,220,125,20);
        add(passwordText);

        create = new JButton("Create");
        create.setBounds(50,285,100,25);
        create.setBackground(new Color(66,127,219));
        create.setForeground(Color.BLACK);
        create.addActionListener(this);
        add(create);

        back = new JButton("Back");
        back.setBounds(180,285,100,25);
        back.setBackground(new Color(66,127,219));
        back.setForeground(Color.BLACK);
        back.addActionListener(this);
        add(back);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/boy.png"));
        Image image = imageIcon.getImage().getScaledInstance(250,250, Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(image);
        JLabel imageIcon3 = new JLabel(imageIcon2);
        imageIcon3.setBounds(320,30,250,250);
        add(imageIcon3);


        setSize(600,380);
        setLocation(500,200);
        setLayout(null);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== create){
            String sloginAs = choice.getSelectedItem();
            String susername = userNameText.getText();
            String sname = nameTextField.getText();
            String spassword = passwordText.getText();
            String smeter = meaterTextField.getText();
            try{
                DataBase c = new DataBase();
                String query= null;
                if (sloginAs.equals("Admin")) {
                    query = "insert into Signup value('" + smeter + "', '" + susername + "', '" + sname + "','" + spassword + "','" + sloginAs + "')";
                }else {
                    query = "update Signup set username = '"+susername+"', password = '"+spassword+"', usertype = '"+sloginAs+"' where meter_no = '"+smeter+"'";
                }
                c.stm.executeUpdate(query);

                JOptionPane.showMessageDialog(null,"Account Created");
                setVisible(false);
                new Login();

            }catch (Exception E){
                E.printStackTrace();
            }
        } else if (e.getSource()==back) {
            setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
        new SignUp();

    }
}
