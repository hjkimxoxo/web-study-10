package web_study_10.ds;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {
	public static Connection getConnection() {
		 Connection conn = null;
	      String proptiesPath = "db.properties";

	      try (InputStream is = Thread.currentThread()
	              .getContextClassLoader()
	              .getResourceAsStream(proptiesPath)){
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         Properties props = new Properties();
	         props.load(is);
	         
	         String url = props.getProperty("url");
	         String user = props.getProperty("user");
	         String password = props.getProperty("password");

	         
//	         String url = "jdbc:oracle:thin:@localhost:1521:orcl?useSSL=false";
//	         String user = "user_erp";
//	         String password = "rootroot";
	         conn = DriverManager.getConnection(url, user, password);
	      } catch (SQLException | ClassNotFoundException | IOException e) {
	         e.printStackTrace();
	      }
	      return conn;
	   }

}
