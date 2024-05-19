package isdcontroller;

import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import uts.isd.model.dao.DBManager;

/*Post method creates new Http Session and Validator. Following Validation Checks, 
it updates the chosen user details in the DB by looking at the UserID*/
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession();
            Validator validator = new Validator();
            DBManager manager = (DBManager) session.getAttribute("manager");

            int userID = Integer.parseInt(request.getParameter("userID"));
            String fName = request.getParameter("fName");
            String lName = request.getParameter("lName");
            Integer phoneNo = Integer.valueOf(request.getParameter("phoneNo"));
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String address = request.getParameter("address");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            Integer postcode = Integer.valueOf(request.getParameter("postcode"));
            boolean activation = Boolean.parseBoolean(request.getParameter("activation"));
            String position = request.getParameter("position");
            
            validator.clear(session);
            if(!validator.validateFName(fName)) {
                 session.setAttribute("fnameErr", "Error: Fname format incorrect");
                 request.getRequestDispatcher("Account.jsp").forward(request, response);
             } else if (!validator.validateLName(lName)) {
                 session.setAttribute("lnameErr", "Error: Lname format incorrect");
                 request.getRequestDispatcher("Account.jsp").forward(request, response);
             } else if (!validator.validatePhone(request.getParameter("phoneNo"))) {
                 session.setAttribute("phoneErr", "Error: Phone Number format incorrect");
                 request.getRequestDispatcher("Account.jsp").forward(request, response);
             } else if (!validator.validateEmail(email)) {
                 session.setAttribute("emailErr", "Error: Email format incorrect");
                 request.getRequestDispatcher("Account.jsp").forward(request, response);
             }else if (!validator.validatePassword(password)) {
                 session.setAttribute("passErr", "Error: Password format incorrect");
                 request.getRequestDispatcher("Account.jsp").forward(request, response);
             } else if (!validator.validateAddress(request.getParameter("address"))) {
                 session.setAttribute("addressErr", "Error: Address format incorrect");
                 request.getRequestDispatcher("Account.jsp").forward(request, response);
             }else if (!validator.validateCity(request.getParameter("city"))) {
                 session.setAttribute("cityErr", "Error: City format incorrect");
                 request.getRequestDispatcher("Account.jsp").forward(request, response);
             }else if (!validator.validateState(request.getParameter("state"))) {
                 session.setAttribute("stateErr", "Error: State format incorrect");
                 request.getRequestDispatcher("Account.jsp").forward(request, response);
             } else if (!validator.validatePostcode(request.getParameter("postcode"))) {
                 session.setAttribute("postErr", "Error: Postcode format incorrect");
                 request.getRequestDispatcher("Account.jsp").forward(request, response);
             }  else if (!validator.validatePosition(position)) {
                 session.setAttribute("positionErr", "Error: Position format incorrect");
                 request.getRequestDispatcher("Account.jsp").forward(request, response);
             } else {
             try{
                 manager.updateUser(userID, fName, lName, phoneNo, email, password, address, city, state, postcode, activation, position);
                 response.sendRedirect("logout.jsp");
                 System.out.println("User successfully updated in the database!");
             } catch (SQLException e) {
                 e.printStackTrace(); // Handle your exception here
                 System.out.println("User does not exist in the database!");
             }
        }
    }
}
