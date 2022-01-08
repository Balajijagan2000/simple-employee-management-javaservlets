
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Db_connection {

	public static String username;
	public static String password;
	public static String dbname;
	public static String url;
	public static Connection con;
	
	public Db_connection(String dbname) {
		
		Db_connection.dbname = dbname;
		Db_connection.username = "root";
		Db_connection.password = "root";
		Db_connection.url = "jdbc:mysql://localhost:3306/";
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(Exception e) {
			
			System.out.println("Error: "+e);
		}
	}
	public void Create_db() throws IOException{
		
		String query = "CREATE DATABASE IF NOT EXISTS "+dbname;
		try {
			
			con = DriverManager.getConnection(url,username,password);
			
			Statement stm = con.createStatement();
			
			stm.executeUpdate(query);
			stm.executeUpdate("USE "+dbname);
			System.out.println("DB created..");
			query = "CREATE TABLE IF NOT EXISTS employee(ID VARCHAR(20) PRIMARY KEY, "
					+ "empname VARCHAR(30),empage INT,empphno VARCHAR(20),"
					+ "dept VARCHAR(30),email VARCHAR(30))";
			stm.executeUpdate(query);
			System.out.println("Table created..");
			
		} catch(Exception e) {
			
			System.out.println("Error: "+e);
		}
		
	}
	
	public void addEmployee(ArrayList<String> empDetail) {
		try {
			String query = "INSERT INTO EMPLOYEE VALUES(?,?,?,?,?,?)";
			PreparedStatement smt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			smt.setString(1, empDetail.get(0));
			smt.setString(2, empDetail.get(1));
			smt.setInt(3, Integer.parseInt(empDetail.get(2) ));
			smt.setString(4, empDetail.get(3));
			smt.setString(5, empDetail.get(4));
			smt.setString(6, empDetail.get(5));
			int queryExecuted = smt.executeUpdate();
			if(queryExecuted == 1) {
				System.out.println("Query executed and row is inserted");
			}
		} catch(Exception e) {
			
			System.out.println("Error in insertion:"+e);
		}
		
		
	}
	public ArrayList<String> getEmployee(String eid) {
		
	
		ArrayList<String> data = null;
		String query = "SELECT * FROM EMPLOYEE WHERE ID='"+eid+"';";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			if(rs.next()) {
				
				data = new ArrayList<>();
				data.add(rs.getString("ID"));
				data.add(rs.getString("empname"));
				data.add(rs.getString("empage"));
				data.add(rs.getString("empphno"));
				data.add(rs.getString("email"));
				data.add(rs.getString("dept"));
			}
			
			
			
			
			
		} catch(Exception e) {
			System.out.println("Error in getting employee:"+e);
		}
		return data;
		
	}
	
	
	public int updateEmployee(ArrayList<String> data) {
		int affectedRow = 0;
		String query = "UPDATE EMPLOYEE SET empname=?,empage=?,empphno=?,email=?,dept=? WHERE ID=?";
		try {
			PreparedStatement ps = con.prepareStatement(query); 
			ps.setString(1, data.get(1));
			ps.setInt(2, Integer.parseInt(data.get(2)));
			ps.setString(3, data.get(3));
			ps.setString(4, data.get(4));
			ps.setString(5, data.get(5));
			ps.setString(6, data.get(0));
			
			affectedRow = ps.executeUpdate();
			
			
		} catch(Exception e) {
			System.out.println("Error in updating employee "+e);
		}
		
		return affectedRow;
		
	}
		
	
	public int removeEmployee(String eid) {
		int affectedRows = 0;
		try {
			String query = "DELETE FROM EMPLOYEE WHERE ID=?;";
			
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, eid);
			affectedRows = ps.executeUpdate();
			System.out.println(affectedRows);
		} catch(Exception e) {
			
			System.out.println("Error in deletion: "+e);
		}
		return affectedRows;
		
	}
		
		
	public ArrayList<ArrayList<String>> displayAllemployees() {
		
		ArrayList<ArrayList<String>> emp = new ArrayList<>();
		try {
			String query = "SELECT * FROM EMPLOYEE";
			Statement smt = con.createStatement();
			ResultSet rs = smt.executeQuery(query);
			
			while(rs.next()) {
				
				ArrayList<String> row = new ArrayList<>();
				row.add(rs.getString("ID"));
				row.add(rs.getString("empname"));
				row.add(""+rs.getInt("empage"));
				row.add(rs.getString("empphno"));
				row.add(rs.getString("email"));
				row.add(rs.getString("dept"));
				emp.add(row);
//				System.out.println(rs.getClass().getName());
				
			}
//			System.out.println(emp);
//			System.out.println(emp.get(4).getString("empname"));
			
			
			
			
		} catch(Exception e) {
			System.out.println("Error in displaying employee: "+e);
		}
		
		return emp;
	}
	

}
	
