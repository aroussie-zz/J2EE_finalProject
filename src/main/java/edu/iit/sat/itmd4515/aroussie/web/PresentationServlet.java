/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.aroussie.web;

import edu.iit.sat.itmd4515.aroussie.domain.Administrator;
import edu.iit.sat.itmd4515.aroussie.domain.City;
import edu.iit.sat.itmd4515.aroussie.domain.Student;
import edu.iit.sat.itmd4515.aroussie.domain.Visitor;
import edu.iit.sat.itmd4515.aroussie.domain.security.UserGroup;
import edu.iit.sat.itmd4515.aroussie.service.AdminService;
import edu.iit.sat.itmd4515.aroussie.service.StudentService;
import edu.iit.sat.itmd4515.aroussie.service.VisitorService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Roussiere
 */
@WebServlet(name = "PresentationServlet", urlPatterns = {"/app/PresentationServlet"})
public class PresentationServlet extends HttpServlet {

    @EJB
    VisitorService visitorService;

    @EJB
    StudentService studentService;

    @EJB
    AdminService adminService;

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>PresentationServlet</title>");
            out.println("</head>");
            out.println("<body>");

            //If the user is a Visitor
            if (request.isUserInRole("VISITOR")) {
                Visitor v = visitorService.findByUserName(request.getRemoteUser());
                out.println("<h1> Welcome " + v.getFirstName() + " " + v.getName() + "</h1>");
                for (UserGroup g : v.getUser().getUserGroups()) {
                    out.println("<h2>" + g.getGroupDescription() + "</h2>");
                }
                out.println("<h1> Here are the informations about you</h1>");
                out.println("<li>");
                out.println("<ul> Your Name: " + v.getName() + "</ul>");
                out.println("<ul> Your First Name: " + v.getFirstName() + "</ul>");
                out.println("<ul> Your mail: " + v.getMail() + "</ul>");
                out.println("</li>");
                List<City> cities = v.getCities();
                out.println("<h2> Here are all the cities you can see information about: </h2>");
                out.println("<li>");
                for (City c : cities) {
                    out.println("<ul>" + c.getName() + "</ul>");
                }
                out.println(" </li>");
                out.println("<br>");
            }
            //If the user is a Student
            if (request.isUserInRole("STUDENT")) {
                Student s = studentService.findByUserName(request.getRemoteUser());
                out.println("<h1> Welcome " + s.getFirstName() + " " + s.getName() + "</h1>");
                for (UserGroup g : s.getUser().getUserGroups()) {
                    out.println("<h2>" + g.getGroupDescription() + "</h2>");
                }
                out.println("<h1> Here are the informations about you</h1>");
                out.println("<li>");
                out.println("<ul> Your Name: " + s.getName() + "</ul>");
                out.println("<ul> Your First Name: " + s.getFirstName() + "</ul>");
                out.println("<ul> Your mail: " + s.getMail() + "</ul>");
                out.println("</li>");
                out.println("You studied in <STRONG>" + s.getCity().getName() + "</STRONG>. That means that you can only put "
                        + "contents on the <STRONG>" + s.getCity().getName() + " section </STRONG> <br><br>");

            }

            //If the user is the admin
            if (request.isUserInRole("ADMIN")) {
                Administrator admin = adminService.findByUserName(request.getRemoteUser());
                out.println("<h1> Welcome Admin </h1>");
                for (UserGroup g : admin.getUser().getUserGroups()) {
                    out.println("<h2>" + g.getGroupDescription() + "</h2>");
                }
                out.println("<h2> Here the list of Visitor: </h2>");
                out.println("<li>");
                for (Visitor v : visitorService.findAll()) {
                    out.println("<li><ul><strong> Username:</strong> " + v.getUser().getLogin() + "</ul>" + " <ul> <strong>Name:</strong> " + v.getName() + "</ul>" + "<ul><strong>FirstName:</strong> " + v.getFirstName() + "</ul>" + "<ul> <strong>Email:</strong> " + v.getMail() + "</ul></li><br>");
                }
                out.println("</li>");

                out.println("<h2> Here the list of Students: </h2>");
                out.println("<li>");
                for (Student s : studentService.findAll()) {
                    out.println("<li><ul> <strong>Username:</strong> " + s.getUser().getLogin() + "</ul>" + "<ul><strong>Name:</strong> " + s.getName() + "</ul>" + "<ul><strong>FirstName:</strong> " + s.getFirstName() + "</ul>" + "<ul><strong>Email:</strong> " + s.getMail()
                            + "</ul>" + "<ul><strong>Studied in</strong> " + s.getCity().getName() + "</ul></li><br>");
                }
                out.println("</li>");
                //Link to Create a Visitor
                out.println("<h2> Do you want to add a Visitor ? " + " <a href=\""
                        + request.getContextPath() + "/CreateVisitor.xhtml\"> Create a Visitor </a></h2> <br><br>");

                out.println("<h2> Do you want to add a Student ? " + " <a href=\""
                        + request.getContextPath() + "/CreateStudent.xhtml\"> Create a Student </a></h2> <br><br>");
            }

            out.println("<a href=\"" + request.getContextPath() + "/Logout\" >Logout </a>");
            out.println("</body>");
            out.println("</html>");
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
