package datasource;

import Interfacees.Interface;
import domain.Partner;
import domain.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author TOcvfan
 */
public class Authservice implements Interface {

    private final String username = "cphir17";
    private final String password = "cphir17";
    private final String driver = "oracle.jdbc.driver.OracleDriver";
    private final String URL = "jdbc:oracle:thin:@datdb.cphbusiness.dk:1521:dat";
    private ResultSet rs;
    private Statement statement;
    private Connection connection;

//    private Map<String, Partner> partner = new HashMap();
    public Authservice() {

    }

    @Override
    public boolean authCheckPartner(String partnerId, String pwd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean authCheckEmployee(String employeeId, String pwd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public domain.Partner getPartner(String partnerId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    public Employee getEmployee(String employeeId) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public boolean addEmployee(String empId, String empName, String empPass, String empMail) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addPartner(int parId, String parName, String parAdress, String parPhone,
            String eMail, String CVR, String parPass, int parFunds) {

        Partner p = new Partner(parId, parName, parAdress, parPhone, eMail, CVR, parPass, parFunds);
        if (checkInputPartner(p) == true) {
            int rowsInserted = 0;
            String sql = "INSERT INTO Partner VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement statement = null;
            try {
                Class.forName(driver);
                connection = java.sql.DriverManager.getConnection(URL, username, password);
                statement = connection.prepareStatement(sql);
                statement.setInt(1, 64111);
                statement.setString(2, p.getParName());
                statement.setString(3, p.getParAdress());
                statement.setString(4, p.getParPhone());
                statement.setString(5, "poelse@mad.dk");
                statement.setString(6, "65461846");
                statement.setString(7, "skdjhfsiu");
                statement.setInt(8, 0);

//		System.out.println( p.getParID()+ " " + p.getParName()+ " " + p.getParAdress()+ " " +p.getParPhone() 
//			+ " " +p.geteMail()+ " " + p.getCVR()+ " " +p.getParPass());
                rowsInserted = statement.executeUpdate();
            } catch (Exception e) {

                System.out.println("Fail1 in Partner details - addPartner");
                System.out.println(e.getMessage());
            } finally // must close statement
            {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("Fail2 in Partner details - addPartner");
                    System.out.println(e.getMessage());
                }
            }
            return rowsInserted == 1;
        }
        return true;
    }
    public boolean checkInputPartner(Partner p) {
        return true;
    }
    public boolean checkInputProject(Project pro) {
        return true;
    }

    @Override
    public boolean authCheckProject(String eMail, String pwd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Project getProject(String projectId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addProject(int proID, int proEmpID, int proParID, String proName, String proStartDate, String proEndDate, String proPOE, int proStatus, int proSteps, int proReqFunds, int proFunds) {
        Project pro = new Project(proID, proEmpID, proParID, proName, proStartDate, proEndDate, proReqFunds);
        if (checkInputProject(pro) == true) {
            int rowsInserted = 0;
            String sql = "INSERT INTO PROJECT VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = null;
            try {
                Class.forName(driver);
                connection = java.sql.DriverManager.getConnection(URL, username, password);
                statement = connection.prepareStatement(sql);
                statement.setInt(1, 77);
                statement.setInt(2, pro.getProEmpID());
                statement.setInt(3, pro.getProParID());
                statement.setString(4, pro.getProName());
                statement.setString(5, pro.getProStartDate());
                statement.setString(6, pro.getProEndDate());
                statement.setString(7, "DetteErPOE_01");
                statement.setInt(8, pro.getProStatus());
                statement.setInt(9, pro.getProSteps());
                statement.setInt(10, pro.getProReqFunds());
                statement.setInt(11, 0);

//		System.out.println( p.getParID()+ " " + p.getParName()+ " " + p.getParAdress()+ " " +p.getParPhone() 
//			+ " " +p.geteMail()+ " " + p.getCVR()+ " " +p.getParPass());
                rowsInserted = statement.executeUpdate();
            } catch (Exception e) {

                System.out.println("Fail1 in Partner details - addPartner");
                System.out.println(e.getMessage());
            } finally // must close statement
            {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("Fail2 in Partner details - addPartner");
                    System.out.println(e.getMessage());
                }
            }
            return rowsInserted == 1;
        }
        return true;
    }

}
