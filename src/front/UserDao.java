package front;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
	

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "amulya");
			System.out.println("hi");
			System.out.println("connected");
		} catch (Exception e) {
//			System.out.println(e);
			e.printStackTrace();
		}
		return con;
	}
	
	public static boolean validate(String username, String password) {
		boolean status = false;
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select id from users where username=? and password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				status = true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
	
	public static boolean register(String username,String password,String email,String country){
		boolean status=false;
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("insert into users (username,password,country,email) values(?,?,?,?)");
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, country);
			ps.setString(4, email);
			if(ps.executeUpdate()>0)
				status = true;
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
}