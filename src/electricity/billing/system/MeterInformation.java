package electricity.billing.system;

import icon.splash.DataBase;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MeterInformation extends JFrame implements ActionListener {
            JLabel meterInfo,meterNoChoice, meterNum,meterNumber, meterType,phaseCode,billType,daysBillTime,note,byDefault;
            Choice meterNoChoie,meterTypeChoice,phaseCodeChoice,billTypeChoice;
            JButton jsubmit;
            String meternumber;
    MeterInformation(String meternumber){
        super("Meter Information");
        this.meternumber=meternumber;
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBackground(new Color(252,186,3));
        add(jPanel);

        meterInfo = new JLabel("Meter Information");
        meterInfo.setBounds(180,10,200,20);
        meterInfo.setFont(new Font("Tahoma",Font.BOLD,20));
        jPanel.add(meterInfo);

        meterNoChoice = new JLabel("Meter Number");
        meterNoChoice.setFont(new Font("Tahoma", Font.BOLD,12));
        meterNoChoice.setBounds(50,120,100,20);
        jPanel.add(meterNoChoice);

        meterNumber = new JLabel(meternumber);
        meterNumber.setBounds(180,80,150,20);
        meterNumber.setFont(new Font("Tahoma",Font.BOLD,12));
        jPanel.add(meterNumber);

        meterNum = new JLabel("Meter Number");
        meterNum.setBounds(50,80,100,20);
        meterNum.setFont(new Font("Tahoma",Font.BOLD,12));
        jPanel.add(meterNum);

        meterNoChoie = new Choice();
        meterNoChoie.add("OutSide");
        meterNoChoie.add("Inside");
        meterNoChoie.setBounds(180,120,150,20);
        jPanel.add(meterNoChoie);

        meterType = new JLabel("Meter Type");
        meterType.setBounds(50,160,100,20);
        meterType.setFont(new Font("Tahoma",Font.BOLD,12));
        jPanel.add(meterType);

        meterTypeChoice = new Choice();
        meterTypeChoice.setBounds(180,160,150,20);
        meterTypeChoice.add("Electric Meter");
        meterTypeChoice.add("Solar Meter");
        meterTypeChoice.add("Smart Meter");
        jPanel.add(meterTypeChoice);

        phaseCode = new JLabel("Phase Code");
        phaseCode.setBounds(50,200,100,20);
        phaseCode.setFont(new Font("Tahoma", Font.BOLD,12));
        jPanel.add(phaseCode);

        phaseCodeChoice = new Choice();
        phaseCodeChoice.setBounds(180,200,150,20);
        phaseCodeChoice.add("011");
        phaseCodeChoice.add("022");
        phaseCodeChoice.add("033");
        phaseCodeChoice.add("044");
        phaseCodeChoice.add("055");
        phaseCodeChoice.add("066");
        phaseCodeChoice.add("077");
        phaseCodeChoice.add("088");
        phaseCodeChoice.add("099");
        jPanel.add(phaseCodeChoice);

        billType = new JLabel("Bill Type");
        billType.setBounds(50,240,100,20);
        billType.setFont(new Font("Tahoma",Font.BOLD,12));
        jPanel.add(billType);

        billTypeChoice = new Choice();
        billTypeChoice.setBounds(180,240,150,20);
        billTypeChoice.add("Normal");
        billTypeChoice.add("Industrial");
        jPanel.add(billTypeChoice);

        daysBillTime = new JLabel("30 Days Billing Time....");
        daysBillTime.setBounds(50,280,150,20);
        daysBillTime.setFont(new Font("Tahoma",Font.BOLD,12));
        jPanel.add(daysBillTime);

        note = new JLabel("Note:- ");
        note.setBounds(50,320,100,20);
        note.setFont(new Font("Tahoma", Font.BOLD,12));
        jPanel.add(note);

        byDefault = new JLabel("By default bill is calculated for 30 days only");
        byDefault.setBounds(50,340,500,20);
        byDefault.setFont(new Font("Tahoma",Font.BOLD,12));
        jPanel.add(byDefault);

        jsubmit = new JButton("Submit");
        jsubmit.setBounds(220,390,100,25);
        jsubmit.setBackground(Color.BLACK);
        jsubmit.setForeground(Color.white);
        jsubmit.addActionListener(this);
        jPanel.add(jsubmit);

        setLayout(new BorderLayout());
        add(jPanel,"Center");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/details.png"));
        Image i2 = i1.getImage().getScaledInstance(230,200,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel i4 = new JLabel(i3);
        add(i4,"East");

        setLocation(400,200);
        setSize(700,500);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==jsubmit){
            String smeterNumber = meternumber;
            String smeterLocation = meterNoChoie.getSelectedItem();
            String smeterType = meterTypeChoice.getSelectedItem();
            String sphaseCode = phaseCodeChoice.getSelectedItem();
            String sbillType = billTypeChoice.getSelectedItem();
            String sdays = "30";
           String query_meterInfo = "Insert into meter_info values('"+smeterNumber+"', '"+smeterLocation+"', '"+smeterType+"', '"+sphaseCode+"','"+sbillType+"', '"+sdays+"')";
           try{
               DataBase d = new DataBase();
               d.stm.executeUpdate(query_meterInfo);
               JOptionPane.showMessageDialog(null, "Meter Information submitted successfully");
               setVisible(false);
           }catch (Exception E){
               E.printStackTrace();
           }
        }else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new MeterInformation("");
    }
}
