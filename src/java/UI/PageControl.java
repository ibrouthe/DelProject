/*
 Servlet, the gateway between web view and Domain Logic
 Call methods in Controller and get returnvalues. Use these values to
 generate a new .jsp view
 */
package UI;
//

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

/**
 *
 * @Gruppe 2 Silas, Thomas, Christian, Martin, Ib
 */
@WebServlet(name = "PageControl", urlPatterns = {"/PageControl"})
public class PageControl extends HttpServlet {

    AppController con;

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

        con = new AppController();

        String command = request.getParameter("command");
        switch (command) {
            case "listProjects":
                listProjects(request, response, con);
                break;

        }

    }

    public void listProjects(HttpServletRequest request,HttpServletResponse response, AppController con) {

        ArrayList<Project> showlist;

        showlist = con.listAllProjects();
        
        request.getSession().setAttribute("ListenMedObjekter", showlist);

        
      
        
        
     

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
