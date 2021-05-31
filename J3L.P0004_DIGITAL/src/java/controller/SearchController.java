/*
 * Copyright(C) 2005,  FPT University.
 * J3.L.P0004:
 * Digital News
 *
 * Record of change:
 * DATE          Version             AUTHOR             DESCRIPTION
 * 2021-05-16     1.0              TungCTHE141487      First Implement
 */
package controller;

import dao.IDigitalDAO;
import dao.impl.DigitalNewsDAO;
import entity.Digital;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Represent get list data from <code>DigitalNewsDAO</code> and load the content of
 * Digital News that had inputed by user at Search page
 *
 * @author TungCTHE141487
 */
@WebServlet(name = "SearchController", urlPatterns = {"/SearchController"})
public class SearchController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request is the <code> HttpServletRequest </code>
     * object
     * @param response servlet response is the <code> HttpServletResponse
     * </code> object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String txt = request.getParameter("txtSearch").trim();
            String pageIndex = request.getParameter("index");

            //check that if it is first time run this page
            if (pageIndex == null) {
                pageIndex = "1";
            }
            int index = 1;
            IDigitalDAO digitalDAO = new DigitalNewsDAO();
            
            List<Digital> list = digitalDAO.getTop(6);
            // get the newest news
            Digital top1 = list.remove(0);
            request.setAttribute("top1", top1);
            request.setAttribute("top5", list);

            int total = digitalDAO.count(txt);

            // set number of digital news in each page
            int pageSize = 3;
            int maxPage = total / pageSize;
            // check total numbers of page
            if (total % pageSize != 0) {
                maxPage++;
            }
            //check that total page div pageSize equal 0 or not
            try {
                index = Integer.parseInt(pageIndex);
                //check that page index is bigger than maxpage or lower than 1
                if (index > maxPage || index < 1) {
                    request.setAttribute("errormess", "Can not find anything");
                    request.getRequestDispatcher("Error.jsp").forward(request, response);
                }
            } catch (NumberFormatException e) {
                request.setAttribute("errormess", e.getMessage());
                request.getRequestDispatcher("Error.jsp").forward(request, response);
            }

            List<Digital> listSearch = digitalDAO.getSearch(txt, index, pageSize);
            request.setAttribute("maxPage", maxPage);
            request.setAttribute("list", listSearch);
            request.setAttribute("txt", txt);
            request.setAttribute("index", index);

            request.getRequestDispatcher("Search.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("errormess", e.getMessage());
            request.getRequestDispatcher("Error.jsp").forward(request, response);
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
