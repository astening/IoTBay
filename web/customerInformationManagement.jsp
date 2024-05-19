<%-- 
    Document   : customerInformationManagement
    Created on : 17 May 2024, 12:16:53â¯am
    Author     : taylah
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="isdmodel.User"%>
<%@page import="isdmodeldao.DBManager"%>
<%@page import="java.util.Date"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="style.css">
        <title>Customer Information Management</title>
        <!--Script to toggle buttons for search/manage customer forms-->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    </head>
    <body>

        <% User u = (User) session.getAttribute("user");%>

    <div class="navBar">
        <a class="navBarTitle">IoT Bay</a>
        <a class="node" href="main.jsp">Home</a>
        <% if (u != null) { %>
            <a class="node" href="account.jsp">Account</a>
        <% } %>
        <a class="node" href="DeviceCatalogueServlet">Device Catalogue</a>
        <a class="node" href="orders.jsp">Orders</a>
        <a class="node" href="orderForm.jsp">Order Form</a>
        <% if (u != null && (u.getPosition().equals("Individual") || u.getPosition().equals("Company"))){%>
            <a class="node" href="PaymentMethodServlet?userID=<%=u.getUserID()%>">Payments</a>

        <% } %>
        <a class="active" href="customerInformationManagement.jsp">Manage Customers</a>
        <a class="node" href="StaffInformationManagement.jsp">Manage Staff</a>
        <div class="navBar-right">
                <a class="node" href="logout.jsp">Logout</a>
        </div>
    </div>        
        <h1>Customer Information Management</h1> 
        <!--Call servlet to generate list of all customers-->
            <form action="ShowAllCustomersServlet" method="get">
                <input class="button" type="submit" value="Show All Customers">
            </form>
        
            <br>
            
            <table id="table">
                <tr>
                    <th>User ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Password</th>
                    <th>Phone Number</th>
                    <th>Address</th>
                    <th>Activation Status</th>
                    <th>Registration Date</th>
                    <th>Type</th>
                </tr>
                <!--Create validation for textfields - Retrieve session attribute and display error message if string isn't null-->
                <%
                   String existError = (String) session.getAttribute("existError");
                   String fnameError = (String) session.getAttribute("fnameError");
                   String lnameError = (String) session.getAttribute("lnameError");
                   String phoneError = (String) session.getAttribute("phoneError");
                   String emailError = (String) session.getAttribute("emailError");
                   String passwordError = (String) session.getAttribute("passwordError");
                   String addressError = (String) session.getAttribute("addressError");
                   String cityError = (String) session.getAttribute("cityError");
                   String stateError = (String) session.getAttribute("stateError");
                   String postError = (String) session.getAttribute("postError");
                   String positionError = (String) session.getAttribute("positionError");
                   %>
                
                <!--Retrieve request attribute for customer list, specific customer, customer list by first name and customer list by type-->
                <%        
                    List<User> customerList = (List<User>) request.getAttribute("listOfCustomers");
                    User foundCustomer = (User) request.getAttribute("foundCustomer");
                    List<User> customerListByType = (List<User>) request.getAttribute("listOfCustomersByType");
                    List<User> customerListByFName = (List<User>) request.getAttribute("listOfCustomersByFName"); %>
                
                <!--Display results for all customers-->
                <%  if (customerList != null) {
                        for (User user : customerList) {
                %>
                <tr>
                    <td><%= user.getUserID() %></td>
                    <td><%= user.getFname() %> <%= user.getLname() %></td>
                    <td><%= user.getEmail() %></td>
                    <td><%= user.getPassword() %></td>
                    <td><%= user.getPhoneNo() %></td>
                    <td><%= user.getAddress() %> <%= user.getCity() %> <%= user.getState() %> <%= user.getPostcode() %></td>
                    <td <% if (user.isActivation()) { %>style="color: green;"<% } else { %>style="color: red;"<% } %>>
                        <%= user.isActivation() %>
                    </td>

                    <td><%= user.getRegistrationDate() %></td>
                    <td><%= user.getPosition() %></td>
                    
                </tr>
                
                <!--Display results for specific customer-->
                <%
                        }
                    } else if (foundCustomer != null) {
                %>
                    <tr>
                        <td><%= foundCustomer.getUserID() %></td>
                        <td><%= foundCustomer.getFname() %> <%= foundCustomer.getLname() %></td>
                        <td><%= foundCustomer.getEmail() %></td>
                        <td><%= foundCustomer.getPassword() %></td>
                        <td><%= foundCustomer.getPhoneNo() %></td>
                        <td><%= foundCustomer.getAddress() %> <%= foundCustomer.getCity() %> <%= foundCustomer.getState() %> <%= foundCustomer.getPostcode() %></td>
                        <td <% if (foundCustomer.isActivation()) { %>style="color: green;"<% } else { %>style="color: red;"<% } %>>
                            <%= foundCustomer.isActivation() %>
                        </td>
                        <td><%= foundCustomer.getRegistrationDate() %></td>
                        <td><%= foundCustomer.getPosition() %></td>

                    </tr>
                <!--Display results for customer list by type-->
                <%
                    } else if (customerListByType != null) {
                        for (User user : customerListByType) {
                %>
                    <tr>
                        <td><%= user.getUserID() %></td>
                        <td><%= user.getFname() %> <%= user.getLname() %></td>
                        <td><%= user.getEmail() %></td>
                        <td><%= user.getPassword() %></td>
                        <td><%= user.getPhoneNo() %></td>
                        <td><%= user.getAddress() %> <%= user.getCity() %> <%= user.getState() %> <%= user.getPostcode() %></td>
                        <td <% if (user.isActivation()) { %>style="color: green;"<% } else { %>style="color: red;"<% } %>>
                            <%= user.isActivation() %>
                        </td>

                        <td><%= user.getRegistrationDate() %></td>
                        <td><%= user.getPosition() %></td>

                    </tr>
                    
                <!--Display results for customer list by first name-->
                <%
                        }
                    } else if (customerListByFName != null) {
                        for (User user : customerListByFName) {
                %>
                    <tr>
                        <td><%= user.getUserID() %></td>
                        <td><%= user.getFname() %> <%= user.getLname() %></td>
                        <td><%= user.getEmail() %></td>
                        <td><%= user.getPassword() %></td>
                        <td><%= user.getPhoneNo() %></td>
                        <td><%= user.getAddress() %> <%= user.getCity() %> <%= user.getState() %> <%= user.getPostcode() %></td>
                        <td <% if (user.isActivation()) { %>style="color: green;"<% } else { %>style="color: red;"<% } %>>
                            <%= user.isActivation() %>
                        </td>

                        <td><%= user.getRegistrationDate() %></td>
                        <td><%= user.getPosition() %></td>
                    </tr>
                <!--Otherwise display instruction message-->
                <%      }
                    } else {
                %>
                <tr>
                    <td colspan="9" style="text-align: center;">Click 'Show All Customers' to show Customer List</td>
                </tr>
                <%
                    }
                %>
            </table>
    
        <br>
        
        <p>After making changes to Customer records, click 'Sync Customer Records' to display changes made to the Database</p>
            <!--Call servlet to generate updated list of all customers-->
            <form action="ShowAllCustomersServlet" method="get">
            <input class="button" type="submit" value="Sync Customer Records">
            </form>
            
        <br>
        <br>
        
        <hr>
       
        
        <h2> Search Customer List </h2>
        <p>Click the buttons below to access the page's functionalities</p>
        <small>Note: If Customer List does not appear upon clicking Search, the Customer record does not exist in the Database (expand the form to view input error message)</small><br>
        <br>
        
        <!--Toggle button for search by name and type-->
        <button id="toggleButtonForNameAndType">Find Customer</button>
        <form action="SearchForSpecificCustomerServlet" method="get" id="byNameAndType">
        <p>Enter the following details to find a specific Customer: </p>
            <!--Validate inputs-->
            First Name: <input type="text" placeholder="<%=(fnameError != null ? fnameError : "Enter First Name:")%>" name="fname"></td>
            Last Name: <input type="text" placeholder="<%=(lnameError != null ? fnameError : "Enter Last Name:")%>" name="lname">
            Type: <input type="text" placeholder="<%=(positionError != null ? positionError : "Enter Type:")%>" name="position">
        <input class="button" type="submit" value="Search">
        </form>
        <!--Script to toggle button for search by name and type-->
        <script>
        $(document).ready(function(){
            $("#byNameAndType").hide();
            $("#toggleButtonForNameAndType").click(function(){
                $("#byNameAndType").toggle();
            });
        });
        </script>
        <br>
        <br>
        
        <!--Toggle button for search by first name-->
        <button id="toggleButtonForFirstName">Search Customer by First Name</button>  
        <form action="SearchCustomerByFirstNameServlet" method="get" id="byFName">
        <p>Enter an existing Customer's First Name:</p>
            <!--Validate inputs-->
            First Name: <input type="text" placeholder="<%=(fnameError != null ? fnameError : "Enter First Name:")%>" name="fname">
        <input class="button" type="submit" value="Search">
        </form>
        <!--Script to toggle button for search by first name-->
        <script>
        $(document).ready(function(){
            $("#byFName").hide();
            $("#toggleButtonForFirstName").click(function(){
                $("#byFName").toggle();
            });
        });
        </script>
        
        <br>
        <br>
        <!--Toggle button for search by type-->
        <button id="toggleButtonForType">Search Customer by Type</button> 
        <form action="SearchCustomerByTypeServlet" method="get" id="byType">
        <p>Enter Customer Type:</p>
        <small>Note: Customers are classified as 'Individual' or 'Company'</small>
        <br>
        <br>
            <!--Validate inputs-->
            Type: <input type="text" placeholder="<%=(positionError != null ? positionError : "Enter Type:")%>" name="position">
        <input class="button" type="submit" value="Search">
        </form>
        <!--Script to toggle button for search by type-->
        <script>
        $(document).ready(function(){
            $("#byType").hide();
            $("#toggleButtonForType").click(function(){
                $("#byType").toggle();
            });
        });
        </script>
        
        <br><br><br>
        <hr>
        
        <h2> Manage Customer Database </h2>
        <p>Click the buttons below to access the page's functionalities</p>
        <small>Note: Before clicking 'Sync Customer Records', expand the form to check for input error message</small>
        <br>
        
        <br>
        <!--Toggle button to add customer-->
        <button id="toggleButton">Add Customer</button>
        <br>
        <form action="AddCustomerServlet" method="post" id="addForm">
        <p>To add a new customer to the database, enter the following details: </p>
        <!--Validate inputs-->
        <table>
                <tr>
                    <td><label for="fname">First Name:</label></td>
                    <td><input type="text" placeholder="<%=(fnameError != null ? fnameError : "Enter First Name:")%>" id="fName" name="fName" required></td>
                <tr>
                <tr>
                    <td><label for="lname">Last Name:</label></td>
                    <td><input type="text" placeholder="<%=(lnameError != null ? lnameError : "Enter Last Name:")%>" id="lName" name="lName" required></td>
                <tr>
                <tr>
                    <td><label for="phoneNo">Phone Number:</label></td>
                    <td><input type="number" placeholder="<%=(phoneError != null ? phoneError : "Enter Phone No:")%>" id="phoneNo" name="phoneNo" required></td>
                <tr>
                    <td><label for="email">Email:</label></td>
                    <td><input type="text" placeholder="<%=(emailError != null ? emailError : "Enter Email:")%>" id="email" name="email" required></td>
                </tr>
                <tr>
                    <td><label for="password">Password:</label></td>
                    <td><input type="password" placeholder="<%=(passwordError != null ? passwordError : "Password:")%>" id="password" name="password" required></td>
                </tr>
                <tr>
                    <td><label for="address">Address:</label></td>
                    <td><input type="text" placeholder="<%=(addressError != null ? addressError : "Enter Address:")%>" id="address" name="address" required></td>
                </tr>
                <tr>
                    <td><label for="city">City:</label></td>
                    <td><input type="text" placeholder="<%=(cityError != null ? cityError : "Enter City:")%>" id="city" name="city"></td>
                </tr>
                <tr>
                    <td><label for="state">State:</label></td>
                    <td><input type="text" placeholder="<%=(stateError != null ? stateError : "Enter State:")%>" id="state" name="state" required></td>
                </tr>
                <tr>
                    <td><label for="postcode">Postcode:</label></td>
                    <td><input type="number" placeholder="<%=(postError != null ? postError : "Enter Postcode:")%>" id="postcode" name="postcode" required></td>
                </tr>
                <tr>
                    <td><label for="position">Position:</label></td>
                    <td><input type="text" placeholder="<%=(positionError != null ? positionError : "Enter Type:")%>" id="position" name="position" required></td>
                </tr>
                <tr><tr><td>
                    <td>
                        <input class="button" type="submit" value="Add to Database">
                    </td>
                </tr>
            </table>
            <br>
        </form>
        <!--Script to toggle button to add customer-->    
        <script>
            $(document).ready(function(){
                $("#addForm").hide();
                $("#toggleButton").click(function(){
                    $("#addForm").toggle();
                });
            });
        </script>
    
   
        <br>
        <!--Toggle button to delete customer-->
        <button id="toggleButton2">Delete Customer</button>  
        <br>
        <form action="DeleteCustomerServlet" method="post" id="deleteForm">
        <p>To delete an existing customer in the database, enter the customer's user ID: </p>
        <table>                
        <td>
                        User ID: <input type="number" placeholder="Enter User ID:" name="userID">
                        </td>

                        <td>
                            <input class="button" type="submit" value="Delete from Database">
                        </td>
                    </tr>
                </table>
                <br>
        </form>
        <!--Script to toggle button to delete customer-->    
        <script>
            $(document).ready(function(){
                $("#deleteForm").hide();
                $("#toggleButton2").click(function(){
                    $("#deleteForm").toggle();
                });
            });
        </script>
    
   
        <br>
        <!--Toggle button to update customer-->
        <button id="toggleButton3">Update Customer</button> 
        <br>
        <form action="UpdateCustomerServlet" method="post" id="updateForm">
        <p>To update an existing customer in the database, enter the following details: </p>
                        User ID: <input type="number" placeholder="Enter User ID:" name="userID">
                        <br>
                        <br>
            <!--Validate inputs-->
            Update details:            
            <table>
                <tr>
                    <td><label for="fname">First Name:</label></td>
                    <td><input type="text" placeholder="<%=(fnameError != null ? fnameError : "Enter First Name:")%>" id="fName" name="fName" required></td>
                <tr>
                <tr>
                    <td><label for="lname">Last Name:</label></td>
                    <td><input type="text" placeholder="<%=(lnameError != null ? lnameError : "Enter Last Name:")%>" id="lName" name="lName" required></td>
                <tr>
                <tr>
                    <td><label for="phoneNo">Phone Number:</label></td>
                    <td><input type="number" placeholder="<%=(phoneError != null ? phoneError : "Enter Phone No:")%>" id="phoneNo" name="phoneNo" required></td>
                <tr>
                    <td><label for="email">Email:</label></td>
                    <td><input type="text" placeholder="<%=(emailError != null ? emailError : "Enter Email:")%>" id="email" name="email" required></td>
                </tr>
                <tr>
                    <td><label for="password">Password:</label></td>
                    <td><input type="password" placeholder="<%=(passwordError != null ? passwordError : "Password:")%>" id="password" name="password" required></td>
                </tr>
                <tr>
                    <td><label for="address">Address:</label></td>
                    <td><input type="text" placeholder="<%=(addressError != null ? addressError : "Enter Address:")%>" id="address" name="address" required></td>
                </tr>
                <tr>
                    <td><label for="city">City:</label></td>
                    <td><input type="text" placeholder="<%=(cityError != null ? cityError : "Enter City:")%>" id="city" name="city"></td>
                </tr>
                <tr>
                    <td><label for="state">State:</label></td>
                    <td><input type="text" placeholder="<%=(stateError != null ? stateError : "Enter State:")%>" id="state" name="state" required></td>
                </tr>
                <tr>
                    <td><label for="postcode">Postcode:</label></td>
                    <td><input type="number" placeholder="<%=(postError != null ? postError : "Enter Postcode:")%>" id="postcode" name="postcode" required></td>
                </tr>
                <tr>
                    <td><label for="activation">Activation:</label></td>
                    <td><select name="activation">
                        <option value="true">True</option>
                        <option value="false">False</option>
                    </select></td>
                </tr>
                <tr>
                    <td><label for="position">Type:</label></td>
                    <td><input type="text" placeholder="<%=(positionError != null ? positionError : "Enter Type:")%>" id="position" name="position" required></td>
                </tr>
                <tr><tr><td>
                    <td>
                        <input class="button" type="submit" value="Update Customer">
                    </td>
                </tr>
            </table>
           <br>
        </form>
        <!--Script to toggle button to update customer-->    
        <script>
        $(document).ready(function(){
            $("#updateForm").hide();
            $("#toggleButton3").click(function(){
                $("#updateForm").toggle();
            });
        });
        </script>
        <br>
          
        <!--Toggle button to change customer status-->    
        <button id="toggleButton4">Change Customer Account Status</button> 
        <br>
        <form action="ChangeActivationStatusServlet" method="post" id="changeStatusForm">
        <p>To change an existing customer's account status, enter the customer's User ID: </p>
        <small>Account Settings: true = Account is Activated, false = Account is Deactivated</small>
        <br>
                    User ID: <input type="number" placeholder="Enter User ID:" name="userID">
                    <br>
                    <br>
                    <td><label for="activation">Activation:</label></td>
                    <td><select name="activation">
                        <option value="true">True</option>
                        <option value="false">False</option>
                    </select></td>
                    <td>
                        <input class="button" type="submit" value="Change Status">
                    </td>
        </form>
        <!--Script to toggle button to change customer status-->    
        <script>
        $(document).ready(function(){
            $("#changeStatusForm").hide();
            $("#toggleButton4").click(function(){
                $("#changeStatusForm").toggle();
            });
        });
        </script>
        <br>
        <br>
        
        <hr>
        <br>
    </body>
</html>
