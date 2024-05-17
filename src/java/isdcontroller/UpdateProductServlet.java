/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package isdcontroller;

import isdmodel.dao.DBManager;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anna
 */
public class UpdateProductServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");
        
        session.setAttribute("productID", request.getParameter("productID"));
        request.getRequestDispatcher("updateProduct.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");
        
        int productID = Integer.parseInt(request.getParameter("productID"));
        String name = request.getParameter("name");
        Float price = Float.valueOf(request.getParameter("price"));
        String type = request.getParameter("type");
        Integer stock = 1; Integer.valueOf(request.getParameter("stock"));
        
        try{
            manager.updateProduct(productID, name, price, type, stock);
            response.sendRedirect("DeviceCatalogueServlet");
        } catch (SQLException ex){
            Logger.getLogger(DeleteProductServlet.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getErrorCode() + " and " + ex.getMessage());
        }
    }

}
