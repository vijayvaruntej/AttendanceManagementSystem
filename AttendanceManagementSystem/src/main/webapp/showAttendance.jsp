<%@ page import="java.sql.ResultSet" %>
<%
    ResultSet data = (ResultSet) request.getAttribute("attendanceData");
    if (data == null) {
        out.println("<h2>No data found.</h2>");
    } else {
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Show Attendance</title>
    <style>
        /* Table styling */
        table {
            border-collapse: collapse; /* Ensures borders don't overlap */
            width: 100%; /* Makes the table fit the container */
            margin: 20px auto; /* Centers the table horizontally */
        }
        th, td {
            padding: 10px; /* Adds padding for better readability */
            text-align: left; /* Aligns content to the left */
            border: 1px solid #ddd; /* Sets a thin border */
        }
        th {
            background-color: #f2f2f2; /* Light gray background for headers */
            font-weight: bold; /* Makes headers bold for emphasis */
        }
    </style>
</head>
<body>
    <h1>Attendance Records</h1>
    <table border="0"> <tr>
            <th>Roll Number</th>
            <th>Name</th>
            <th>Attended Classes</th>
        </tr>
        <% while (data.next()) { %>
            <tr>
                <td><%= data.getInt("roll_number") %></td>
                <td><%= data.getString("name") %></td>
                <td><%= data.getInt("attended_classes") %></td>
            </tr>
        <% } %>
    </table>
<% } %>
<button style="background-color:red;color:white ;border:2px black solid" onclick="window.location.href='AttendanceController?action=clear'">Clear Attendance</button>
</body>
</html>