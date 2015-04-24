/*
 * Builds up the select statement
 * Executes the statement
 * Transfers data from the resultset an ArrayList
 * Returns an ArrayList with Projects
 */
package datasource;

import domain.AppController;
import domain.Partner;
import domain.Project;
import domain.UserLogin;
import java.awt.image.BufferedImage;
import java.beans.Statement;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.servlet.http.Part;

/**
 *
 * @Gruppe 2 Silas, Thomas, Christian, Martin, Ib
 */
public class Mapper {

    UserLogin ul;
    Statement statement;
    ResultSet rs;
    private int createdParID;

    public ArrayList<Project> listProject(Connection con) {

        ArrayList<Project> list = new ArrayList<Project>();

        String SQLString = "SELECT * FROM Project";
        PreparedStatement statement = null;

        try {

            statement = con.prepareStatement(SQLString);

            rs = statement.executeQuery();

            while (rs.next()) {

                Project project = new Project();

                project.setProID(rs.getInt(1));
                project.setProEmpID(rs.getInt(2));
                project.setProParID(rs.getInt(3));
                project.setProName(rs.getString(4));
                project.setProStartDate(rs.getString(5));
                project.setProEndDate(rs.getString(6));
                project.setProPeo(rs.getString(7));
                project.setProStatus(rs.getInt(8));
                project.setProSteps(rs.getInt(9));
                project.setProReqFunds(rs.getInt(10));
                project.setProFunds(rs.getInt(11));

                System.out.println("DEBUGGER " + project);
                list.add(project);

            }

        } catch (SQLException ee) {

        }

        return list;

    }

    public ArrayList<Partner> listPartner(Connection con) {

        ArrayList<Partner> list = new ArrayList<Partner>();

        String SQLString = "SELECT * FROM Partner";
        PreparedStatement statement = null;

        try {

            statement = con.prepareStatement(SQLString);

            rs = statement.executeQuery();

            while (rs.next()) {

                Partner partner = new Partner();

                partner.setParID(rs.getInt(1));
                partner.setParName(rs.getString(2));
                partner.setParAdress(rs.getString(3));
                partner.setParPhone(rs.getString(4));
                partner.seteMail(rs.getString(5));
                partner.setCVR(rs.getString(6));
                partner.setParFunds(rs.getInt(7));

                System.out.println("DEBUGGER " + partner);
                list.add(partner);

            }

        } catch (SQLException ee) {

        }

        return list;

    }

    public boolean addPartner(Connection con, int parId, String parName, String parAdress, String parPhone,
            String eMail, String CVR, String parPass, int parFunds) {

        Partner p = new Partner(parId, parName, parAdress, parPhone, eMail, CVR, parPass, parFunds);
        if (checkInputPartner(p) == true) {
            int rowsInserted = 0;

            String sql = "INSERT INTO Partner VALUES(parSeq.nextval,'" + p.getParName() + "','" + p.getParAdress() + "','"
                    + p.getParPhone() + "','" + p.geteMail() + "','" + p.getCVR() + "'," + p.getParFunds() + ")";

            PreparedStatement statement1 = null;
            PreparedStatement statement2 = null;

            try {

                statement1 = con.prepareStatement(sql);

                rowsInserted = statement1.executeUpdate();

                String SQLString = "SELECT * FROM PARTNER WHERE PAREMAIL LIKE'" + p.geteMail() + "'";

                statement1 = con.prepareStatement(SQLString);

                rs = statement1.executeQuery();

                while (rs.next()) {

                    createdParID = rs.getInt(1);

                }
                String SQLparUserTable = "INSERT INTO PARUSER VALUES('" + createdParID + "','" + p.getParPass() + "')";

                statement2 = con.prepareStatement(SQLparUserTable);

                rowsInserted = statement1.executeUpdate();
                rowsInserted = statement2.executeUpdate();

            } catch (Exception e) {

                System.out.println("Fail1 in Partner details - addPartner");
                System.out.println(e.getMessage());
            } finally // must close statement1
            {
                try {
                    statement1.close();
                } catch (SQLException e) {
                    System.out.println("Fail2 in Partner details - addPartner");
                    System.out.println(e.getMessage());
                }
            }
            return rowsInserted == 1;
        }
        return true;
    }

    public boolean addProject(Connection con, int proID, int proEmpID, int proParID, String proName, String proStartDate,
            String proEndDate, String proPOE, int proStatus, int proSteps, int proReqFunds, int proFunds, Part filePart) {

        Project pro = new Project(0, proEmpID, proParID, proName, proStartDate, proEndDate, proPOE,
                proStatus, proSteps, proReqFunds, proFunds);

        if (checkInputProject(pro) == true) {
            int rowsInserted = 0;

            String sql = "INSERT INTO PROJECT VALUES(proSeq.nextval," + pro.getProEmpID() + "," + pro.getProParID() + ",'"
                    + pro.getProName() + "','" + pro.getProStartDate() + "','" + pro.getProEndDate() + "','peo',1,2,"
                    + pro.getProReqFunds() + ",100)";

            PreparedStatement statement = null;
            try {

                statement = con.prepareStatement(sql);

                rowsInserted = statement.executeUpdate();

//                try {
//                    InputStream is = filePart.getInputStream();
//                    BufferedImage bi = ImageIO.read(is);
//                    ImageIO.write(bi, "jpg", new File("C:\\Users\\Ib Routhe\\Dropbox\\coding\\java\\Dell\\images" + filePart.getSubmittedFileName()));
//                } catch (IOException e) {
//                    System.out.println(e.getMessage());
//                }
            } catch (Exception e) {

                System.out.println("Fail1 in Partner details - addPartner");
                System.out.println(e.getMessage());
            } finally // must close statement1
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

     //1st method called by checkPW(). Using the user's email to find his ID in the database.
    public void getParID(String email, Connection con) {
        String sql = "SELECT parid FROM partner WHERE paremail LIKE '" + email + "'";
        PreparedStatement statement = null;
        String parID = null;
        try {
//            Class.forName(driver);
//            connection = java.sql.DriverManager.getConnection(URL, username, password);
            statement = con.prepareStatement(sql);
            rs = statement.executeQuery();
 
            while (rs.next()) {
                parID = rs.getString(1);
 
            }
 
        } catch (Exception e) {
            System.out.println("TRYNA OUTPUT DIS: " + sql);
            System.out.println("parID ?: " + parID);
        }
 
        ul.setId(parID);
    }
 
    //2nd method called by checkPW(), using the Partner ID from getParID() to find the user's password in the database.
    public void getParPW(String parID, Connection con) {
        String sql = "SELECT ppass FROM parUser WHERE pid LIKE '" + parID + "'";
        PreparedStatement statement = null;
        String parPW = null;
        try {
//            Class.forName(driver);
//            connection = java.sql.DriverManager.getConnection(URL, username, password);
            statement = con.prepareStatement(sql);
            rs = statement.executeQuery();
            //parPW = rs.getString(1);
            while (rs.next()) {
 
                parPW = rs.getString(1);
 
            }
 
        } catch (Exception e) {
            System.out.println("TRyNA OUTPUT DIS YO: " + sql);
            System.out.println("WATS DIS ?: " + parPW);
        }
        ul.setPassword(parPW);
    }
 
    //Method to check the user who is trying to login, by getting his ID and password in the database
    //and comparing the password in the database to the one he typed in.
    //Calling methods getParID() and getParPW() to do so.
    //Finally checking if the typed in password, matches the one in our database.
    //Returns true if they match.
    
    public boolean checkPw(String user, String pw, Connection con) {
        ul = new UserLogin();
 
        getParID(user,con);
 
        String dbID = ul.getId();
        System.out.println("dbID: " + dbID);
 
        getParPW(dbID,con);
 
        String dbPass = ul.getPassword();
 
        if (pw.equalsIgnoreCase(dbPass)) {
 
            return true;
        } else {
            return false;
        }
 
    }
    
       
    public Project getSelectedProject(Connection con, String ClickedID) {

        System.out.println("ClickedID fra ProjectMapper: " + ClickedID);
        String SQLString = "SELECT * FROM Project WHERE proID = " + ClickedID;
        PreparedStatement statement = null;
        Project selProject = new Project();

        try {

            statement = con.prepareStatement(SQLString);

            rs = statement.executeQuery();

            while (rs.next()) {

                selProject.setProID(rs.getInt(1));
                selProject.setProEmpID(rs.getInt(2));
                selProject.setProParID(rs.getInt(3));
                selProject.setProName(rs.getString(4));
                selProject.setProStartDate(rs.getString(5));
                selProject.setProEndDate(rs.getString(6));
                selProject.setProPeo(rs.getString(7));
                selProject.setProStatus(rs.getInt(8));
                selProject.setProSteps(rs.getInt(9));
                selProject.setProReqFunds(rs.getInt(10));
                selProject.setProFunds(rs.getInt(11));

                System.out.println("DEBUGGER " + selProject);

            }}catch (SQLException ee){
                    
                    
                    }
    
        return selProject;
    
    }
    
    
    public void updateApproveProject(Connection con, int currentProID, int choice) {

      
        String SQLString = "UPDATE project SET prostatus = " + choice + " WHERE proID = " + currentProID;
        PreparedStatement statement = null;        

        try {
            statement = con.prepareStatement(SQLString);

            rs = statement.executeQuery();

        } catch (SQLException ee) {

        }
    }
}

