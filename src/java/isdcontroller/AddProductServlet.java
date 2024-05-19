package isdcontroller;

import isdmodeldao.DBManager;
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
        Validator validator = new Validator();
        validator.clear(session);
        
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String type = request.getParameter("type");
        String stock = request.getParameter("stock");
                   
        
        try{
            if (!validator.validateProductName(name)){
                session.setAttribute("prodNameErr", "Invalid Name!");
            } else if (!validator.validateProductType(type)){
                session.setAttribute("prodTypeErr", "Invalid Type!");
            } else if (!validator.validateProductPrice(price)){
                session.setAttribute("prodPriceErr", "Invalid Price!");
            } else if (!validator.validateProductStock(stock)){
                session.setAttribute("prodStockErr", "Invalid Stock Level!");
            } else {
                Float priceFloat = Float.valueOf(price);
                Integer stockInt = Integer.valueOf(stock);
                manager.addProduct(name, priceFloat, type, stockInt);
                response.sendRedirect("DeviceCatalogueServlet");
                return;
            }
            request.getRequestDispatcher("addProduct.jsp").forward(request, response);
            
        } catch (SQLException ex){
            Logger.getLogger(DeleteProductServlet.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getErrorCode() + " and " + ex.getMessage());
        }
    }

}
