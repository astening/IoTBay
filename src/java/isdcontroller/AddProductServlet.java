package isdcontroller;

import isdmodel.dao.DBManager;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddProductServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");
        
        String name = request.getParameter("name");
        Float price = Float.valueOf(request.getParameter("price"));
        String type = request.getParameter("type");
        Integer stock = 1; Integer.valueOf(request.getParameter("stock"));
        
        try{
            manager.addProduct(name, price, type, stock);
            response.sendRedirect("DeviceCatalogueServlet");
        } catch (SQLException ex){
            Logger.getLogger(DeleteProductServlet.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getErrorCode() + " and " + ex.getMessage());
        }
    }

}
