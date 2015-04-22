/*
 Servlet, the gateway between web view and Domain Logic
 Call methods in Controller and get returnvalues. Use these values to
 generate a new .jsp view
 */
package UI;
//

import domain.AppController;
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

/**
 *
 * @Gruppe 2 Silas, Thomas, Christian, Martin, Ib
 */
@WebServlet(name = "PageControl", urlPatterns = {"/PageControl"})
public class PageControl extends HttpServlet {

    AppController appcon;

    HttpSession session;

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

            case "partnerForm":

                request.getSession().setAttribute("message", "you have registrated succesfully");
                int parId = 268505; // Dummy input
                String parName = request.getParameter("parName");
                String parAdress = request.getParameter("parAdress");
                String parPhone = request.getParameter("parPhone");
                String parPass = request.getParameter("parPass");
                String eMail = request.getParameter("eMail");
                String CVR = request.getParameter("CVR");
                int parFunds = 0; // Dummy input

                boolean ap = appcon.createNewPartner(parId, parName, parAdress, parPhone, eMail, CVR, parPass, parFunds);
                if (ap) {
                    response.sendRedirect("index.jsp");
                } else {
                    response.sendRedirect("newPartnerForm.jsp");
                }
                return;

            case "projectForm":

                request.getSession().setAttribute("message", "you have created a project succesfully");

                int proID = 30611; //Dummy input                
                int proEmpID = Integer.parseInt(request.getParameter("proEmpID"));
                int proParID = Integer.parseInt(request.getParameter("proParID"));
                String proName = request.getParameter("proName");
                String proStartDate = request.getParameter("proStartDate");
                String proEndDate = request.getParameter("proEndDate");
                String proPOE = "This is a proPOE"; //Dummy input
                int proStatus = 0; //Dummy input
                int proSteps = 1; //Dummy input
                int proReqFunds = Integer.parseInt(request.getParameter("proReqFunds"));
                int proFunds = 5000; //Dummy input

                boolean aPro = appcon.createNewProject(proID, proEmpID, proParID, proName, proStartDate, proEndDate, proPOE, proStatus, proSteps, proReqFunds, proFunds);
                if (aPro) {
                    response.sendRedirect("index.jsp");
                } else {
                    response.sendRedirect("newProjektForm.jsp");
                }
                return;

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
