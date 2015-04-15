/*
 Servlet, the gateway between web view and Domain Logic
 Call methods in Controller and get returnvalues. Use these values to
 generate a new .jsp view
 */
package UI;
//

import Interfacees.Interface;
import datasource.Authservice;
import datasource.DBconnector;
import datasource.ProjectMapper;
import domain.AppController;
import domain.Project;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @Gruppe 2 Silas, Thomas, Christian, Martin, Ib
 */
@WebServlet(name = "PageControl", urlPatterns = {"/PageControl"})
public class PageControl extends HttpServlet {

    AppController appcon;
    Interface auth;
       
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
        auth = new Authservice();
        
        String command = request.getParameter("command");

        switch (command) {
            case "listProjects":

                ArrayList<Project> showlist;

                showlist = appcon.listAllProjects();

                HttpSession session = request.getSession();
                session.setAttribute("returnlist", showlist);

                try {
                    response.sendRedirect("listProjects.jsp");
                } catch (Exception ee) {

                }

                break;
            case "projectForm":
                System.out.println("VI ER I CASE PROJECTFORM");
                
                request.getSession().setAttribute("message", "you have created a project succesfully");
                
                int proID = 20611116;
                String proName = request.getParameter("proName");
                int proEmpID = request.getIntHeader("proEmpID");
             
               
                int proParID = request.getIntHeader("proParID");
                String proStartDate = request.getParameter("proStartDate");
               
                
                String proEndDate = request.getParameter("proEndDate");
                String proPOE = request.getParameter("proPOE");
                int proStatus = request.getIntHeader("proStatus");
                int proSteps = request.getIntHeader("proSteps");
                int proReqFunds = request.getIntHeader("proReqFunds");
                int proFunds = request.getIntHeader("proFunds");
                

                boolean aPro = auth.addProject(proID, proEmpID, proParID, proName, proStartDate, proEndDate, proPOE, proStatus, proSteps, proReqFunds, proFunds);
                if (aPro) {
                    response.sendRedirect("index.jsp");
                } else {
                    response.sendRedirect("newProjektForm.jsp");
                }
                return;

        }

    }

    public void listProjects(HttpServletRequest request, HttpServletResponse response, AppController appcon) {

        ArrayList<Project> showlist;

        showlist = appcon.listAllProjects();

        HttpSession command = request.getSession();
        command.setAttribute("ListenMedObjekter", showlist);

        for (Project john : showlist) {
            System.out.println(john.getProName());
        }

        try {
            response.sendRedirect("listProjects.jsp");
        } catch (Exception ee) {

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
