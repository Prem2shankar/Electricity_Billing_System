package electricity.billing.system;

import icon.splash.DataBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class main_class extends JFrame implements ActionListener {
    JMenuItem newcustomer, calculateBIll, customerDetails, depositeDetails, updateInfo, viewInfo, payBill, billDetails, generateBIll, notepad, calculator, exitItem;
    String userTtype, meter_no;
    main_class(String userTtype, String meter_no){
        this.userTtype= userTtype;
        this.meter_no = meter_no;
        setExtendedState(JFrame.MAXIMIZED_BOTH);


        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/ebs.png"));
        Image image = imageIcon.getImage().getScaledInstance(1920, 1125, Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(image);
        JLabel imageLable = new JLabel(imageIcon2);
        add(imageLable);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("Menu");
        menu.setFont(new Font("monospaced",Font.PLAIN,15));


        newcustomer = new JMenuItem("New Customer");
        ImageIcon customerImg = new ImageIcon(ClassLoader.getSystemResource("icon/newcustomer.png"));
        Image customerImgage = customerImg.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        newcustomer.setIcon(new ImageIcon(customerImgage));
        newcustomer.addActionListener(this);
        menu.add(newcustomer);

        customerDetails = new JMenuItem("Customer Details");
        ImageIcon customerDetImg = new ImageIcon(ClassLoader.getSystemResource("icon/customerDetails.png"));
        Image customerDetImage = customerDetImg.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        customerDetails.setIcon(new ImageIcon(customerDetImage));
        customerDetails.addActionListener(this);
        menu.add(customerDetails);

        depositeDetails = new JMenuItem("Deposite Details");
        ImageIcon depositeDetImg = new ImageIcon(ClassLoader.getSystemResource("icon/depositdetails.png"));
        Image depositeDetImage = depositeDetImg.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        depositeDetails.setIcon(new ImageIcon(depositeDetImage));
        depositeDetails.addActionListener(this);
        menu.add(depositeDetails);

        calculateBIll = new JMenuItem("Calculate Bill");
        ImageIcon calculateBillImg = new ImageIcon(ClassLoader.getSystemResource("icon/calculator.png"));
        Image calculateBillImage = calculateBillImg.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        calculateBIll.setIcon(new ImageIcon(calculateBillImage));
        calculateBIll.addActionListener(this);
        menu.add(calculateBIll);

        JMenu info = new JMenu("Information");
        info.setFont(new Font("monospaced",Font.PLAIN,15));


        updateInfo = new JMenuItem("Update Information");
        ImageIcon updateInfoImg = new ImageIcon(ClassLoader.getSystemResource("icon/update.png"));
        Image updateInfoImage = updateInfoImg.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        updateInfo.setIcon(new ImageIcon(updateInfoImage));
        updateInfo.addActionListener(this);
        info.add(updateInfo);

        viewInfo = new JMenuItem("View Information");
        ImageIcon viewInfoImg = new ImageIcon(ClassLoader.getSystemResource("icon/viewInfo.png"));
        Image viewInfoImage = viewInfoImg.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        viewInfo.setIcon(new ImageIcon(viewInfoImage));
        viewInfo.addActionListener(this);
        info.add(viewInfo);

        JMenu user = new JMenu("User");
        user.setFont(new Font("monospaced",Font.PLAIN,15));


        payBill = new JMenuItem("PayBill");
        ImageIcon payBillImg = new ImageIcon(ClassLoader.getSystemResource("icon/pay.png"));
        Image payBillImage = payBillImg.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        payBill.setIcon(new ImageIcon(payBillImage));
        payBill.addActionListener(this);
        user.add(payBill);

        billDetails = new JMenuItem("Bill Details");
        ImageIcon billDetailsImg = new ImageIcon(ClassLoader.getSystemResource("icon/detail.png"));
        Image billDetailsImage = billDetailsImg.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        billDetails.setIcon(new ImageIcon(billDetailsImage));
        billDetails.addActionListener(this);
        user.add(billDetails);

        JMenu bill = new JMenu("Bill");
        bill.setFont(new Font("monospaced",Font.PLAIN,15));


        generateBIll = new JMenuItem("Generate Bill");
        ImageIcon generateBillImg = new ImageIcon(ClassLoader.getSystemResource("icon/bill.png"));
        Image generateBillImage = generateBillImg.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        generateBIll.setIcon(new ImageIcon(generateBillImage));
        generateBIll.addActionListener(this);
        bill.add(generateBIll);

        JMenu utility =  new JMenu("Utility");
        utility.setFont(new Font("monospaced",Font.PLAIN,15));


        notepad = new JMenuItem("Notepad");
        ImageIcon notepadImg = new ImageIcon(ClassLoader.getSystemResource("icon/notepad.png"));
        Image notepadImage = notepadImg.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        notepad.setIcon(new ImageIcon(notepadImage));
        notepad.addActionListener(this);
        utility.add(notepad);

        calculator = new JMenuItem("Calculator");
        ImageIcon calculatorImg = new ImageIcon(ClassLoader.getSystemResource("icon/calculator.png"));
        Image calculatorImage = calculatorImg.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        calculator.setIcon(new ImageIcon(calculatorImage));
        calculator.addActionListener(this);
        utility.add(calculator);

        JMenu exit = new JMenu("Exit");
        exit.setFont(new Font("monospaced",Font.PLAIN,15));


        exitItem = new JMenuItem("Exit");
        ImageIcon exitItemImg = new ImageIcon(ClassLoader.getSystemResource("icon/exit.png"));
        Image exitItemImage = exitItemImg.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        exitItem.setIcon(new ImageIcon(exitItemImage));
        exitItem.addActionListener(this);
        exit.add(exitItem);

        if(userTtype.equals("Admin")){
            menuBar.add(menu);
        }else{
            menuBar.add(bill);
            menuBar.add(info);
            menuBar.add(user);
        }
        menuBar.add(utility);
        menuBar.add(exit);

        setLayout(new FlowLayout());
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = e.getActionCommand();
        if(msg.equals("New Customer")){
            new NewCustomer();
        } else if (msg.equals("Customer Details")) {
            new Customer_details();
        } else if (msg.equals("Deposite Details")) {
            new Deposit_details();
        } else if (msg.equals("Calculate Bill")) {
            new Calculate_bill();
        } else if (msg.equals("Update Information")) {
            new Update_information(meter_no);
        } else if (msg.equals("View Information")) {
            new View_information(meter_no);
        } else if (msg.equals("Bill Details")) {
            new Bill_details(meter_no);
        } else if (msg.equals("Notepad")) {
            try{
                Runtime.getRuntime().exec("notepad.exe");
            }catch (Exception E){
                E.printStackTrace();
            }
        } else if (msg.equals("Calculator")) {
            try{
                Runtime.getRuntime().exec("calc.exe");
            }catch (Exception E){
                E.printStackTrace();
            }
        } else if (msg.equals("Exit")) {
                setVisible(false);
                new Login();
        } else if (msg.equals("PayBill")) {
            new Pay_Bill(meter_no);
        } else if (msg.equals("Generate Bill")) {
            new Generate_Bill(meter_no);
        }


        try{
            if(e.getSource()== newcustomer){
                new NewCustomer();
                setVisible(false);
            } else if (e.getSource()==calculateBIll) {
                new Calculate_bill();
                setVisible(false);
            } else if (e.getSource()==customerDetails) {
                new Customer_details();
                setVisible(false);
            } else if (e.getSource()==depositeDetails) {
                new Deposit_details();
                setVisible(false);
            }
        }catch (Exception E){
            E.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new main_class("","");
    }
}
