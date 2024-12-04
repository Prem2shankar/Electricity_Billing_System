package electricity.billing.system;

import icon.splash.DataBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login extends JFrame implements ActionListener {
    JTextField jTextField;
    JPasswordField jPasswordField;
    JButton longinButton,cancleButton,signUpButton ;
    Choice jchoice;
    Login(){
        super("Login");

        JLabel username = new JLabel("UserName");
        username.setBounds(300,60,100,20);
        add(username);

        jTextField = new JTextField();
        jTextField.setBounds(400,60,150,20);
        add(jTextField);

        JLabel password = new JLabel("PassWord");
        password.setBounds(300,100,100,20);
        add(password);

        jPasswordField = new JPasswordField();
        jPasswordField.setBounds(400,100,150,20);
        add(jPasswordField);

        JLabel loginAs = new JLabel("Longgin In As");
        loginAs.setBounds(300, 140,100,20);
        add(loginAs);

        jchoice = new Choice();
        jchoice.add("Admin");
        jchoice.add("Customer");
        jchoice.setBounds(400,140,150,20);
        add(jchoice);

        longinButton = new JButton("Login");
        longinButton.setBounds(330,180,100,20);
        longinButton.addActionListener(this);
        add(longinButton);

        cancleButton = new JButton("Cancle");
        cancleButton.setBounds(460,180,100,20);
        cancleButton.addActionListener(this);
        add(cancleButton);

        signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(400,215,100,20);
        signUpButton.addActionListener(this);
        add(signUpButton);

        //Upload the Image
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/profile.png"));
        Image image = imageIcon.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(image);
        JLabel image2 = new JLabel(imageIcon2);
        image2.setBounds(5,5,250,250);
        add(image2);

        setSize(640, 300);
        setLocation(400, 200);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==longinButton){
                String suserName = jTextField.getText();
                String suserPassWord = jPasswordField.getText();
                String suser = jchoice.getSelectedItem();
                try{
                    DataBase d = new DataBase();
                    String qry = "Select * from Signup where username='"+suserName+"' and password='"+suserPassWord+"' and usertype='"+suser+"' ";
                    ResultSet resultSet = d.stm.executeQuery(qry);
                    if(resultSet.next()){
                        String meter = resultSet.getString("meter_no");
                        setVisible(false);
                        new main_class(suser, meter);
                    }else {
                        JOptionPane.showMessageDialog(null,"Invalid Login");
                    }

                }catch (Exception E){
                    E.printStackTrace();
                }
        } else if (e.getSource()==cancleButton) {
            setVisible(false);
        } else if (e.getSource()==signUpButton) {
            setVisible(false);
            new SignUp();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
