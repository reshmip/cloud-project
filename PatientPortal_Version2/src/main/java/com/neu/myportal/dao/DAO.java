package com.neu.myportal.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO {
	private String driver;
    private String dburl;
    private String dbuser;
    private String dbpassword;
    
    public DAO(){
    	 driver = "com.mysql.jdbc.Driver";
         dburl = "jdbc:mysql://localhost:3306/patientportal";
         dbuser = "root";
         dbpassword="password";
        
        //DbUtils.loadDriver(driver);
    }
    
    public Connection getConnection(){
        Connection conn =null;
        try{
        	Class.forName(driver);
        }catch(ClassNotFoundException e){
        	System.out.println(e.getMessage());
        }
        
        try{
            conn = DriverManager.getConnection(dburl,dbuser,dbpassword);
            
        }catch(SQLException ex){
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE,null,ex);
        }
        return conn;
    }
    /*public void close(Connection connection){
        if(connection != null){
            try{
                DbUtils.close(connection);
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    } */
}
