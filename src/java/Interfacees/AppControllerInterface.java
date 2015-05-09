
package Interfacees;

import domain.Employee;
import domain.Partner;
import domain.Project;
import java.io.InputStream;
import java.util.ArrayList;

/**
 *
 * @author Ib Routhe
 */
public interface AppControllerInterface {

    ArrayList listAllProjects();

    ArrayList searchProjects(String searchField);

    ArrayList searchParProjects(String searchField, String email);

    ArrayList searchPartners(String searchField);

    ArrayList searchEmployees(String searchField);

    ArrayList listParProjects(String email);

    ArrayList listAllPartners();

    boolean createNewPartner(int parId, String parName, String parAdress, String parPhone,
            String eMail, String CVR, String parPass, int parFunds, String contactName);

    boolean createNewProject(int proID, int proEmpID, int proParID, String proName, String proStartDate,
            String proEndDate, InputStream proPOE, int proStatus, int proSteps, int proReqFunds, int proFunds);

    boolean checkParPassword(String user, String pw);

    void parTimeStamp(String email);

    boolean checkEmpPassword(String user, String pw);

    String returnName();

    String returnRole();

    String returnEmail();

    ArrayList listRelevantProjects(String name, String role);

    void uploadPOE(InputStream iS, String clickedID);

    Project listSelectedProject(String ClickedID);

    void approveProject(int proID, int choice);

    void updateStep(Project currentPro);

    Partner listSelectedPartner(String ClickedID);

    boolean createNewEmployee(int empId, String empName, int empStatus, String empMail, String empPass);

    ArrayList listAllEmployees();

    Employee listSelectedEmployee(String ClickedID);

}
