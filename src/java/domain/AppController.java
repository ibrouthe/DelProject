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
 * lav ny mapper i constructor
 *
 * @author Pc
 */
public class AppController {

    Mapper mapper;
    // Mapper mapper = DbMapper.getInstance();

    public AppController() {
    }

    public AppController(Mapper mapper) {
        this.mapper = mapper;
    }

    public ArrayList listAllProjects() {

        mapper = new Mapper();

        ArrayList<Project> showlist;

        showlist = mapper.listProject();

        return showlist;

    }

    public ArrayList searchProjects(String searchField) {
        mapper = new Mapper();

        ArrayList<Project> searchList;

        searchList = mapper.searchProjects(searchField);

        return searchList;

    }

        public ArrayList searchParProjects(String searchField, String email) {
        mapper = new Mapper();

        ArrayList<Project> searchList;

        searchList = mapper.searchParProjects(searchField, email);

        return searchList;

    }
    
    public ArrayList searchPartners(String searchField) {
        mapper = new Mapper();

        ArrayList<Partner> searchList;

        searchList = mapper.searchPartner(searchField);

        return searchList;

    }
    
    
    

    public ArrayList searchEmployees(String searchField) {
        mapper = new Mapper();

        ArrayList<Employee> searchList;

        searchList = mapper.searchEmployee(searchField);

        return searchList;

    }

    public ArrayList listParProjects(String email) {

        mapper = new Mapper();

        ArrayList<Project> ownlist;

        ownlist = mapper.listParProjects(email);

        return ownlist;

    }
//Test at du får alle partners ud og at du kun kalder mappereren een gang

    public ArrayList listAllPartners() {

        mapper = new Mapper();

        ArrayList<Partner> showlist;

        showlist = mapper.listPartner();

        return showlist;

    }

    public boolean createNewPartner(int parId, String parName, String parAdress, String parPhone,
            String eMail, String CVR, String parPass, int parFunds, String contactName) {

        mapper = new Mapper();

        return mapper.addPartner(parId, parName, parAdress, parPhone, eMail, CVR, parPass, parFunds, contactName);

    }

    public boolean createNewProject(int proID, int proEmpID, int proParID, String proName, String proStartDate,
            String proEndDate, String proPOE, int proStatus, int proSteps, int proReqFunds, int proFunds, Part filePart) {

        mapper = new Mapper();

        return mapper.addProject(proID, proEmpID, proParID, proName, proStartDate, proEndDate, proPOE, proStatus, proSteps, proReqFunds, proFunds, filePart);

    }

    public boolean checkParPassword(String user, String pw) {

        mapper = new Mapper();

        return mapper.checkParPw(user, pw);

    }

    public void parTimeStamp(String email) {
        mapper = new Mapper();

        mapper.parTimeStamp(email);
    }

    public boolean checkEmpPassword(String user, String pw) {

        mapper = new Mapper();

        return mapper.checkEmpPw(user, pw);

    }

    public String returnName() {

        return mapper.returnName();

    }

    public String returnRole() {

        return mapper.returnRole();

    }

    public String returnEmail() {
        return mapper.returnEmail();
    }

    public Project listSelectedProject(String ClickedID) {

        Mapper mapper = new Mapper();

        Project p;

        p = mapper.getSelectedProject(ClickedID);

        return p;
    }

    public void approveProject(int proID, int choice) {

        Mapper mapper = new Mapper();

        mapper.updateApproveProject(proID, choice);

    }

    public void updateStep(Project currentPro) {

        //metoden her skal ikke være i appController
        int currentProID = currentPro.getProID();
        int oldStep = currentPro.getProSteps();
        int newStep = oldStep;
        int currentStat = currentPro.getProStatus();
        String currentPOE = currentPro.getProPeo();

        if (currentPOE.equals("POE_NULL") && oldStep > 5) {
            newStep = 5;
        }
        switch (currentStat) {
            //needs approval
            case 0: {
                newStep = 1;
                break;
            }
            //active
            case 1: {
                if (oldStep < 3) {
                    newStep = 3;
                }
                break;
            }
            //inactive
            case 2: {
                break;
            }
            //completed
            case 3: {
                newStep = 7;
                break;
            }
        }

        // Metoden som skal være i AppController:
        Mapper mapper = new Mapper();
        mapper.updateStep(currentProID, newStep);

    }

    public Partner listSelectedPartner(String ClickedID) {

        Mapper mapper = new Mapper();

        Partner pa;

        pa = mapper.getSelectedPartner(ClickedID);

        return pa;
    }

    public boolean createNewEmployee(int empId, String empName, int empStatus, String empMail, String empPass) {

        mapper = new Mapper();

        return mapper.addEmployee(empId, empName, empStatus, empMail, empPass);

    }

    public ArrayList listAllEmployees() {

        mapper = new Mapper();

        ArrayList<Employee> showlist;

        showlist = mapper.listEmployee();

        return showlist;

    }

    public Employee listSelectedEmployee(String ClickedID) {

        Mapper mapper = new Mapper();

        Employee em;

        em = mapper.getSelectedEmployee(ClickedID);

        return em;
    }

}
