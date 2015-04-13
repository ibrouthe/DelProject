/*
 Servlet, the gateway between web view and Domain Logic
 Call methods in Controller and get returnvalues. Use these values to
 generate a new .jsp view
 */
package UI;
//

import datasource.DBconnector;
import datasource.ProjectMapper;
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
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

//
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet (PageControl.java)</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Printing all data from Project table</h1>");
            out.println("</body>");
            out.println("</html>");

            /*   Testkode til at skrive ud på webben       /Start            */
            ProjectMapper mapper = new ProjectMapper();

            ArrayList<Project> showlist;

            showlist = mapper.listProject(DBconnector.getInstance().getConnection());

            for (Project temp : showlist) {

                out.println(temp.getProID() + "\t" + temp.getProEmpID() + "\t" + temp.getProParID() + "\t" + temp.getProName() + "\t" + temp.getProStartDate() + "\t" + temp.getProEndDate() + "\t" + temp.getProPeo() + "\t" + temp.getProStatus() + "\t" + temp.getProSteps() + "\t" + temp.getProFunds() + "\n");
                out.println("</br>");

            }

            /*   Testkode til at skrive ud på webben       /Slut           */
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
