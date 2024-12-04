
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.ResultSet;


@WebServlet("/AttendanceController")
public class AttendanceController extends HttpServlet {
    private AttendanceModel model;

    @Override
    public void init() {
        try {
			model = new AttendanceModel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("show".equals(action)) {
                ResultSet data = model.getAttendance();
                if (data != null && data.next()) { // Check if ResultSet has data
                    data.beforeFirst(); // Reset cursor to process in JSP
                    request.setAttribute("attendanceData", data);
                    request.getRequestDispatcher("showAttendance.jsp").forward(request, response);
                } else {
                    response.getWriter().write("No attendance records found.");
                }
            } else if ("mark".equals(action)) {
                ResultSet data = model.getAttendance();
                request.setAttribute("attendanceData", data);
                request.getRequestDispatcher("markAttendance.jsp").forward(request, response);
            }else if ("clear".equals(action)) {
                model.clearAttendance();
                ResultSet data = model.getAttendance();
                if (data != null && data.next()) { // Check if ResultSet has data
                    data.beforeFirst(); // Reset cursor to process in JSP
                    request.setAttribute("attendanceData", data);
                    request.getRequestDispatcher("showAttendance.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] selectedRollNumbers = request.getParameterValues("attendance");
        if (selectedRollNumbers != null) {
            try {
                for (String roll : selectedRollNumbers) {
                    model.markAttendance(Integer.parseInt(roll));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("index.html");
    }
}
