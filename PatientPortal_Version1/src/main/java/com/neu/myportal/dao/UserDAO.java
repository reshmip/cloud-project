package com.neu.myportal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.neu.myportal.pojo.User;

public class UserDAO extends DAO{

	Connection conn;
    PreparedStatement ps;
    ResultSet rs;
		    
    public boolean authenticateUser(String userName, String pwd){
    	try{
		User user = getUserByUserName(userName,pwd);
		if(user!=null && user.getUserName().equals(userName) && user.getPassword().equals(pwd)){
			System.out.println("The user exists");
			return true;
		}else{
			System.out.println("The user do not exist");
			return false;
		}
    	}catch(SQLException e){
    		System.out.println(e.getMessage());
    		return false;
    	}
	}
	    public User getUserByUserName(String uname, String pwd) throws SQLException{
	        
	        try {
	            conn = getConnection();
	            String query = "select * from user where username= ? and password=?";
	            ps = conn.prepareStatement(query);
	            ps.setString(1, uname);
	            ps.setString(2, pwd);
	            rs = ps.executeQuery();
	            while(rs.next()){
	                User u = new User();
	                u.setUserName(rs.getString("username"));
	                u.setPassword(rs.getString("password"));
	                return u;
	            }
	            
	        } catch (SQLException ex) {
	            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
	        }finally{
	            //close(conn);
	            rs.close();
	            ps.close();
	        }
	        return null;
	    }
	    
	    public void loginSession(String uname, String pwd) throws SQLException{
	    	try {
	    		int row;
	    		System.out.println("inside login flag");
	            conn = getConnection();
	            String query = "select * from user where logged='true'";
	            ps = conn.prepareStatement(query);
	            ps.setString(1, uname);
	            ps.setString(2, pwd);
	            row = ps.executeUpdate();
	            /*while(rs.next()){
	                User u = new User();
	                u.setLogged("true");
	                u.setUserName(rs.getString("username"));
	                u.setPassword(rs.getString("password"));
	                return u;
	            }*/
	            conn.commit();
	            
	        } catch (SQLException ex) {
	            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
	        }finally{
	            //close(conn);
	            rs.close();
	            ps.close();
	        }
	        //return null;
	    }

}
