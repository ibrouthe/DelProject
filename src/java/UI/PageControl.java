/*
 Servlet, the gateway between web view and Domain Logic
 Call methods in Controller and get returnvalues. Use these values to
 generate a new .jsp view
 */
package UI;
//

import domain.AppController;
import domain.Employee;
import domain.Partner;
import domain.Project;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @Gruppe 2 Silas, Thomas, Christian, Martin, Ib
 */
@WebServlet(name = "PageControl", urlPatterns = {"/PageControl"})
public class PageControl extends HttpServlet {

    AppController appcon;

    HttpSession session;

    Part filePart;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        appcon = new AppController();

        String command = request.getParameter("command");
        
        
        switch (command) {
            
            case "listProjects":

                ArrayList<Project> showlist;

                showlist = appcon.listAllProjects();

                session = request.getSession();
                session.setAttribute("returnlist", showlist);

                try {
                    response.sendRedirect("listProjects.jsp");
                } catch (Exception ee) {

                }

                break;
                
                
            case "listParProjects":
                String parEmail = (String) session.getAttribute("email");
                
                ArrayList<Project> ownlist;

                ownlist = appcon.listParProjects(parEmail);
                
                session = request.getSession();
                session.setAttribute("returnlist", ownlist);

                try {
                    response.sendRedirect("listProjects.jsp");
                } catch (Exception ee) {
                }

                break;

                
                

            case "listPartners":

                ArrayList<Partner> showpartnerlist;

                showpartnerlist = appcon.listAllPartners();

                session = request.getSession();
                session.setAttribute("returnpartnerlist", showpartnerlist);

                try {
                    response.sendRedirect("listPartners.jsp");
                } catch (Exception ee) {

                }

                break;

            case "search":
                
                
                ArrayList<Project> searchParProjects;
                ArrayList<Project> searchProjects;
                ArrayList<Partner> searchPartners;
                ArrayList<Employee> searchEmployees;
                
                
                String pEmail = (String) session.getAttribute("email");
                String searchField = request.getParameter("searchField");
                //String searchField = "silas";
                
                searchParProjects = appcon.searchParProjects(searchField, pEmail);
                searchProjects = appcon.searchProjects(searchField);
                searchPartners = appcon.searchPartners(searchField);
                searchEmployees = appcon.searchEmployees(searchField);
                
                session = request.getSession();
                
                session.setAttribute("searchParProjects", searchParProjects);
                session.setAttribute("searchProjects", searchProjects);
                session.setAttribute("searchPartners", searchPartners);
                session.setAttribute("searchEmployees", searchEmployees);
                
                  try {
                    response.sendRedirect("Search.jsp");
                } catch (Exception ee) {

                }

                break;
                
                
            case "partnerForm":

                request.getSession().setAttribute("message", "you have registrated succesfully");
                int parId = 268505; // Dummy input
                String parName = request.getParameter("parName");
                String parAdress = request.getParameter("parAdress");
                String parPhone = request.getParameter("parPhone");
                String parPass = request.getParameter("parPass");
                String parPass1 = request.getParameter("parPass1");
                String eMail = request.getParameter("eMail");
                String CVR = request.getParameter("CVR");
                int parFunds = 0; // Dummy input
		String contactName = request.getParameter("contactName");

                if (parPass.equals(parPass1)) {
                    boolean ap = appcon.createNewPartner(parId, parName, parAdress, parPhone, eMail, CVR, parPass, parFunds, contactName);
                    if (ap) {
                        response.sendRedirect("Dashboard.jsp");
                    } else {
                        response.sendRedirect("newPartnerForm.jsp");
                    }
                } else {

                    //request.getSession().setAttribute("message", "passwords do not match");
                    response.sendRedirect("newPartnerForm.jsp");
                }

                return;

            case "approve":

                System.out.println("Vi er i approve Case");
                int choice = 0;
                Project selPro = (Project) request.getSession().getAttribute("clickedProject");
                String stat = request.getParameter("changeStatus");
                System.out.println("changeStats in PageControl: " + stat);
                switch (stat) {
                    case "needsAproval": {
                        choice = 0;
                        break;
                    }
                    case "active": {
                        choice = 1;
                        break;
                    }
                    case "inactive": {
                        choice = 2;
                        break;
                    }
                    case "completed": {
                        choice = 3;
                        break;
                    }
                }
//                This updates the status of the Project:                
                appcon.approveProject(selPro.getProID(), choice);
                Project updatedP = appcon.listSelectedProject("" + selPro.getProID());
                //This checks and updates the Step accordingly:
                appcon.updateStep(updatedP);
                updatedP = appcon.listSelectedProject("" + selPro.getProID());
                request.getSession().setAttribute("clickedProject", updatedP);
                response.sendRedirect("selectedProject.jsp");

                break;

            case "projectForm":

                request.getSession().setAttribute("message", "you have created a project succesfully");

                int proID = 1; //Dummy input. Skal autogenereres af database.                
                int proEmpID = Integer.parseInt(request.getParameter("proEmpID"));
                int proParID = Integer.parseInt(request.getParameter("proParID"));
                String proName = request.getParameter("proName");
                String proStartDate = request.getParameter("proStartDate");
                String proEndDate = request.getParameter("proEndDate");
                String proPOE = "This is a proPOE"; //Dummy input
                int proStatus = Integer.parseInt(request.getParameter("proStatus")); //Dummy input
                int proSteps = 1; //Dummy input
                int proReqFunds = Integer.parseInt(request.getParameter("proReqFunds"));
                int proFunds = 5000; //Dummy input

//                filePart = request.getPart("photo");    
                boolean aPro = appcon.createNewProject(proID, proEmpID, proParID, proName, proStartDate, proEndDate, proPOE, proStatus, proSteps, proReqFunds, proFunds, filePart);
                if (aPro) {
                    response.sendRedirect("Dashboard.jsp");
                } else {
                    response.sendRedirect("newProjektForm.jsp");
                }
                return;

            case "partnerLogin":
                String user = request.getParameter("email");
                String pass = request.getParameter("pw");

                boolean checkParPw = appcon.checkParPassword(user, pass);

                if (checkParPw == true) {
                    response.sendRedirect("Dashboard.jsp");
                    System.out.println("You are now logged in as: " + user + "!");
                } else {
                    response.sendRedirect("PartnerLogin.jsp");
                    System.out.println("Login Failed! User: " + user + " not found!");
                }

                session = request.getSession();
                String pname = appcon.returnName();
                String prole = appcon.returnRole();
                String pmail = appcon.returnEmail();
                
                session.setAttribute("role", prole);
                session.setAttribute("name", pname);
                session.setAttribute("email", pmail);
                
                return;

            case "employeeLogin":
                String eUser = request.getParameter("email");
                String ePass = request.getParameter("pw");

                boolean checkEmpPw = appcon.checkEmpPassword(eUser, ePass);

                if (checkEmpPw == true) {

                    response.sendRedirect("Dashboard.jsp");

                } else {
                    response.sendRedirect("EmployeeLogin.jsp");

                }

                session = request.getSession();
                String ename = appcon.returnName();
                String erole = appcon.returnRole();
                String email = appcon.returnEmail();
                
                session.setAttribute("role", erole);
                session.setAttribute("name", ename);
                session.setAttribute("email", email);
                
                return;

            case "selectedProject":

                System.out.println("Vi er i selectedProject Case");
                String clickedProID = request.getParameter("param1");
                System.out.println("clickedPro fra PageControl: " + clickedProID);
                Project p = appcon.listSelectedProject(clickedProID);
                System.out.println("proName: " + p.getProName());
                request.getSession().setAttribute("clickedProject", p);
                response.sendRedirect("selectedProject.jsp");
                return;

            //------------------------
            case "selectedPartner":

                String clickedParID = request.getParameter("param2");
                Partner pa = appcon.listSelectedPartner(clickedParID);

                request.getSession().setAttribute("clickedPartner", pa);
                response.sendRedirect("selectedPartner.jsp");
                return; //ud af case.. Break går ud af doGet

            case "employeeForm":
                //request.getSession().setAttribute("message", "passwords do not match");
                //request.getSession().setAttribute("message", "you have registrated succesfully");
                int empID = 0; //Dummy input. Skal autogenereres af database.
                String empName = request.getParameter("empName");
                String empMail = request.getParameter("empMail");
                int empStatus = 1; //Dummy input. 
                String empPass = request.getParameter("empPass");
                String empPass1 = request.getParameter("empPass1");

                if (empPass.equals(empPass1)) {
                    boolean em = appcon.createNewEmployee(empID, empName, empStatus, empMail, empPass);
                    if (em) {
                        response.sendRedirect("Dashboard.jsp");
                    } else {
                        response.sendRedirect("newEmployeeForm.jsp");
                    }
                } else {

                    //request.getSession().setAttribute("message", "passwords do not match");
                    response.sendRedirect("newEmployeeForm.jsp");

                }
                return;

            case "listEmployees":

                ArrayList<Employee> showemployeelist;

                showemployeelist = appcon.listAllEmployees();

                session = request.getSession();
                session.setAttribute("returnemployeelist", showemployeelist);

                try {
                    response.sendRedirect("listEmployees.jsp");
                } catch (Exception ee) {

                }

                break;

            case "selectedEmployee":

                String clickedEmpID = request.getParameter("param3");
                Employee em = appcon.listSelectedEmployee(clickedEmpID);
                request.getSession().setAttribute("clickedEmployee", em);
                response.sendRedirect("selectedEmployee.jsp");
                return;

            case "logout":
                String logoutEmail = (String) session.getAttribute("email");
                appcon.parTimeStamp(logoutEmail);
                session.invalidate();
                response.sendRedirect(request.getContextPath() + "/index.jsp");

        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
