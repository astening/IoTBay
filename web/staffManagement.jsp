<%-- 
    Document   : staffManagement
    Created on : 12 May 2024, 7:17:22 pm
    Author     : William Sinclair
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Staff Management</title>
    <script>
        // Function to display/hide forms based on button clicks
        function toggleForm(formId) {
            // Hide all forms
            document.querySelectorAll('.form').forEach(form => form.style.display = 'none');
            // Display the selected form
            document.getElementById(formId).style.display = 'block';
        }

        // Function to fetch staff data and display by default
        function fetchStaffData() {
        // AJAX call to fetch staff data
        var xhr = new XMLHttpRequest();
        xhr.open('GET', 'ShowAllStaffServlet', true);
        xhr.onreadystatechange = function() {
        if (xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
            // Update the staff list container with fetched data
            document.getElementById('staffList').innerHTML = xhr.responseText;
        }
        };
    xhr.send();
}

        // Fetch staff data on page load
        window.onload = function() {
            fetchStaffData();
        };
    </script>
</head>
<body>
    <h1>Staff Management</h1>
    
    <!-- Display all staff -->
    <div id="staffList"></div>
    
    <!-- Buttons to show forms -->
    <button onclick="toggleForm('addStaffForm')">Add Staff</button>
    <button onclick="toggleForm('updateStaffForm')">Update Staff</button>
    <button onclick="toggleForm('deleteStaffForm')">Delete Staff</button>
    
    <!-- Add Staff Form -->
    <div id="addStaffForm" class="form" style="display: none;">
        <!-- Add staff form goes here -->
        <h2>Add Staff Form</h2>
    </div>
    
    <!-- Update Staff Form -->
    <div id="updateStaffForm" class="form" style="display: none;">
        <!-- Update staff form goes here -->
        <h2>Update Staff Form</h2>
    </div>
    
    <!-- Delete Staff Form -->
    <div id="deleteStaffForm" class="form" style="display: none;">
        <!-- Delete staff form goes here -->
        <h2>Delete Staff Form</h2>
    </div>
</body>
</html>
