package icon.splash;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DataBase {
    Connection con;
    public Statement stm;

    public DataBase(){
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bill_System","root","test");
            stm = con.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
