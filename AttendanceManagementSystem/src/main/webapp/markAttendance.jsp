<%@ page import="java.sql.ResultSet" %>
<%
    ResultSet data = (ResultSet) request.getAttribute("attendanceData");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Mark Attendance</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        h1 {
            text-align: center;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        button {
            background-color: #4CAF50; /* Green */
            border: none;
            color: white;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 10px auto;
            cursor: pointer;
        }

        button:hover {
            background-color: #3e8e41;
        }
    </style>
</head>
<body>
    <h1>Mark Attendance</h1>
    <form action="AttendanceController" method="post">
        <input type="hidden" name="action" value="mark">
        <table border="1">
            <tr>
                <th>Roll Number</th>
                <th>Name</th>
                <th>Mark Attendance</th>
            </tr>
            <% while (data.next()) { %>
                <tr>
                    <td><%= data.getInt("roll_number") %></td>
                    <td><%= data.getString("name") %></td>
                    <td>
                        <input type="checkbox" name="attendance" value="<%= data.getInt("roll_number") %>">
                    </td>
                </tr>
            <% } %>
        </table>
        <button type="submit">Submit Attendance</button>
    </form>
    
</body>
</html>