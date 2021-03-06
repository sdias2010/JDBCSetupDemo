

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.DBConnection;

/**
 * Servlet implementation class ProductDetails
 */
@WebServlet("/ProductDetails")
public class ProductDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
				PrintWriter out = response.getWriter();
				out.println("<html><body>");
				
				InputStream in = getServletContext().getResourceAsStream("/WEB-INF/config.properties");
				Properties props = new Properties();
				props.load(in);
				
				DBConnection conn = new DBConnection(props.getProperty("url"), props.getProperty("userid"), props.getProperty("password"));
				
				//JDBC Statement and ResultSet
//				Statement stmt = conn.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//				stmt.executeUpdate("insert into eproduct (name, price, date_added) values('Apple', 20800.00, now())");
//				
//				ResultSet rst = stmt.executeQuery("select * from eproduct");
//				
//				while(rst.next()) {
//					out.println(rst.getInt("ID") + ",    " + rst.getString("name") + ",    " + rst.getDouble("price") + "<Br>");
//				}
				
				
				//CallableStatement and Stored procedure
//				CallableStatement stmt = conn.getConnection().prepareCall("{call add_product(?, ?)}");
//				stmt.setString(1, "new product");
//				stmt.setBigDecimal(2, new BigDecimal(1900.50));
//				stmt.executeUpdate();
//				out.println("Stored Procedure has been updated.<Br>");
				
				//Insertion, Updation and Deletion of Database Records.
				Statement stmt = conn.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				
				stmt.executeUpdate("insert into eproduct (name, price, date_added) values('New Apple', 14500.00, now())");
				out.println("Executed an insert operation<br>");
				
				stmt.executeUpdate("update eproduct set price=2000 where name = 'New Apple'");
				out.println("Executed and update operation<br>");
				
				stmt.executeUpdate("delete from eproduct where name = 'Apple'");
				out.println("Executed a delete operation<br>");
				
				stmt.close();
				
				out.println("</body></html");
				conn.closeConnection();
				
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
