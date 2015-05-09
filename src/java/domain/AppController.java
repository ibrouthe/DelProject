/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import Interfacees.AppControllerInterface;
import datasource.DBfacade;
import datasource.Mapper;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * lav ny mapper i constructor
 *
 * @author Pc
 */
public class AppController implements AppControllerInterface {

    Mapper mapper;

    private static AppController instance; //Singleton

    private DBfacade dbFacade;

    public AppController() {

        dbFacade = DBfacade.getInstance();

        
    }

    public static AppController getInstance() {
        if (instance == null) {
            instance = new AppController();
        }
        return instance;
    }

    @Override
    public ArrayList listAllProjects() {

        ArrayList<Project> showlist;

        showlist = dbFacade.listProject();

        return showlist;

    }

    @Override
    public ArrayList searchProjects(String searchField) {

        ArrayList<Project> searchList;

        searchList = dbFacade.searchProjects(searchField);

        return searchList;

    }

    @Override
    public ArrayList searchParProjects(String searchField, String email) {

        ArrayList<Project> searchList;

        searchList = dbFacade.searchParProjects(searchField, email);

        return searchList;

    }

    @Override
    public ArrayList searchPartners(String searchField) {

        ArrayList<Partner> searchList;

        searchList = dbFacade.searchPartner(searchField);

        return searchList;

    }

    @Override
    public ArrayList searchEmployees(String searchField) {

        ArrayList<Employee> searchList;

        searchList = dbFacade.searchEmployee(searchField);

        return searchList;

    }

    @Override
    public ArrayList listParProjects(String email) {

        ArrayList<Project> ownlist;

        ownlist = dbFacade.listParProjects(email);

        return ownlist;

    }

    @Override
    public ArrayList listAllPartners() {

        ArrayList<Partner> showlist;

        showlist = dbFacade.listPartner();

        return showlist;

    }

    @Override
    public boolean createNewPartner(int parId, String parName, String parAdress, String parPhone,
            String eMail, String CVR, String parPass, int parFunds, String contactName) {

        return dbFacade.addPartner(parId, parName, parAdress, parPhone, eMail, CVR, parPass, parFunds, contactName);

    }

    @Override
    public boolean createNewProject(int proID, int proEmpID, int proParID, String proName, String proStartDate,
            String proEndDate, InputStream proPOE, int proStatus, int proSteps, int proReqFunds, int proFunds) {

        return dbFacade.addProject(proID, proEmpID, proParID, proName, proStartDate, proEndDate, proPOE, proStatus, proSteps, proReqFunds, proFunds);

    }

    @Override
    public boolean checkParPassword(String user, String pw) {

        return dbFacade.checkParPw(user, pw);

    }

    @Override
    public void parTimeStamp(String email) {

        dbFacade.parTimeStamp(email);
    }

    @Override
    public boolean checkEmpPassword(String user, String pw) {

        return dbFacade.checkEmpPw(user, pw);

    }

    @Override
    public String returnName() {

        return dbFacade.returnName();

    }

    @Override
    public String returnRole() {

        return dbFacade.returnRole();

    }

    @Override
    public String returnEmail() {
        return dbFacade.returnEmail();
    }

    @Override
    public ArrayList listRelevantProjects(String name, String role) {

        ArrayList<Project> showRelList;

        showRelList = dbFacade.listRelevantProjects(name, role);

        return showRelList;

    }

    @Override
    public void uploadPOE(InputStream iS, String clickedID) {

        dbFacade.uploadPOE(iS, clickedID);

    }

    @Override
    public Project listSelectedProject(String ClickedID) {

        Project p;

        p = dbFacade.getSelectedProject(ClickedID);

        return p;
    }

    @Override
    public void approveProject(int proID, int choice) {

        Mapper mapper = new Mapper();

        dbFacade.updateApproveProject(proID, choice);

    }

    @Override
    public void updateStep(Project currentPro) {

        dbFacade.updateStep(currentPro);

    }

    @Override
    public Partner listSelectedPartner(String ClickedID) {

        Partner pa;

        pa = dbFacade.getSelectedPartner(ClickedID);

        return pa;
    }

    @Override
    public boolean createNewEmployee(int empId, String empName, int empStatus, String empMail, String empPass) {

        return dbFacade.addEmployee(empId, empName, empStatus, empMail, empPass);

    }

    @Override
    public ArrayList listAllEmployees() {

        ArrayList<Employee> showlist;

        showlist = dbFacade.listEmployee();

        return showlist;

    }

    @Override
    public Employee listSelectedEmployee(String ClickedID) {

        Employee em;

        em = dbFacade.getSelectedEmployee(ClickedID);

        return em;
    }

}
