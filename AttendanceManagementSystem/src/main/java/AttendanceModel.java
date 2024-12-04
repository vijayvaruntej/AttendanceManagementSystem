
import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpResponse;
import java.sql.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
public class AttendanceModel {
    private Connection connection;

    public AttendanceModel() throws IOException {
    	
    	try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Drivers loaded successfully!!!");

        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try {
            // Connect to databasejdbc:mysql://localhost:3306/?user=root
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/attendance_db", "root", "root");
            System.out.println("Database connected");
          
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getAttendance() throws SQLException {
        String query = "SELECT * FROM attendance";
        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        return stmt.executeQuery(query);
    }

    public void markAttendance(int rollNumber) throws SQLException {
        String query = "UPDATE attendance SET attended_classes = attended_classes + 1 WHERE roll_number = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, rollNumber);
        pstmt.executeUpdate();
        
    }
    public void clearAttendance() throws SQLException {
    	ResultSet data = getAttendance();
    	while(data.next())
    	{
    		int rollNumber = data.getInt("roll_number");
    	
        String query = "UPDATE attendance SET attended_classes = 0 WHERE roll_number = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, rollNumber);
        pstmt.executeUpdate();
       
    	}
        
        
    }
    
}