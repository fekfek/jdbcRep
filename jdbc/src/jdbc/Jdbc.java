package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc {
	static Connection con = null;
	static Statement stm = null;
	
	static final String jdbcUrl = "jdbc:mysql://localhost/jdbc";
	static final String user = "root";
	static final String pass = "FryeKonjo";
	
	public static void main(String[] args) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");	//mysql-connector-java... library needs to be
													//added: Rightclick on project - BuildPath - 
													//ConfigureBuildPath - Add External Jar - 
													//search by mysql-conector-java-8.0.11.jar
			con = DriverManager.getConnection(jdbcUrl,user,pass);
			stm = con.createStatement();
			
// Inserting
			String sql = "insert into School values(11, 'Tesfa','Addis',3)";
			stm.executeUpdate(sql);
			
			sql = "insert into School values(12, 'Gofa','Gondor',2)";
			stm.executeUpdate(sql);
			
// Retreiving data from table - use executeQuery() or execute()
			
	      ResultSet rst = stm.executeQuery("select*from School");
	      while(rst.next()) {
	    	  System.out.println(rst.getString("id")+" "+rst.getString("name")+" "+
	    			  rst.getString("city")+" "+rst.getString("rating"));
	      }
	      
// Deleting data from table - use executeUpdate() or execute() - also used for all DML and DDL
	      
	      stm.executeUpdate("delete from School where id=12");	// delete partially

	      System.out.println("After Partial Deletion");
	      rst = stm.executeQuery("select*from School");
	      while(rst.next()) {
	    	  System.out.println(rst.getString("id")+" "+rst.getString("name")+" "+
	    			  rst.getString("city")+" "+rst.getString("rating"));
	      }
	      
//	      stm.executeUpdate("delete from School");				//delete all data
	      System.out.println("After Total Deletion");
	      rst = stm.executeQuery("select*from School");
	      while(rst.next()) {
	    	  System.out.println(rst.getString("id")+" "+rst.getString("name")+" "+
	    			  rst.getString("city")+" "+rst.getString("rating"));
	      }
		}
		catch (SQLException e) {
				e.printStackTrace();
		}
		 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(con!=null) {
					con.close();
					}
				} 
			catch (SQLException e) {
				e.printStackTrace();
				}
			}
			try {
				if(stm!=null) {
					stm.close();
					}
				} 
			catch (SQLException e) {
				e.printStackTrace();
			}
	}

}
