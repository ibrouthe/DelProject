/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import datasource.DBconnector;
import datasource.Mapper;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.http.Part;

/**
 *
 * @author Pc
 */
public class AppController {

    Mapper mapper;
    

    

    public ArrayList listAllProjects() {

        mapper = new Mapper();

        ArrayList<Project> showlist;

        showlist = mapper.listProject(DBconnector.getInstance().getConnection());

        return showlist;

    }

    public ArrayList listAllPartners() {

        mapper = new Mapper();

        ArrayList<Partner> showlist;

        showlist = mapper.listPartner(DBconnector.getInstance().getConnection());

        return showlist;

    }

    public boolean createNewPartner(int parId, String parName, String parAdress, String parPhone,
            String eMail, String CVR, String parPass, int parFunds) {

        mapper = new Mapper();

        return mapper.addPartner(DBconnector.getInstance().getConnection(), parId, parName, parAdress, parPhone, eMail, CVR, parPass, parFunds);

    }

    public boolean createNewProject(int proID, int proEmpID, int proParID, String proName, String proStartDate,
            String proEndDate, String proPOE, int proStatus, int proSteps, int proReqFunds, int proFunds, Part filePart) {

        mapper = new Mapper();

        return mapper.addProject(DBconnector.getInstance().getConnection(), proID, proEmpID, proParID, proName, proStartDate, proEndDate, proPOE, proStatus, proSteps, proReqFunds, proFunds,filePart);

    }

    public boolean checkPassword(String user, String pw){
    
        mapper = new Mapper();
        
        return mapper.checkPw(user, pw, DBconnector.getInstance().getConnection());
    
    
    }
    
    public Project listSelectedProject(String ClickedID){
    
    Mapper mapper = new Mapper();
    
    Project p;
    
    p = mapper.getSelectedProject(DBconnector.getInstance().getConnection(), ClickedID);
    
    return p;
    }
    
    
}
