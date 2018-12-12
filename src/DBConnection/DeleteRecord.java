/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnection;

import Model.UserAccount;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Dinesh
 */
public class DeleteRecord {
    private static final int  FREELANCER = 1;
    private  static final int  CONTRACTOR = 0;
    private static UserAccount userAccount;
    
    public static void deleteRecord(UserAccount useraccount) throws SQLException{      
            
         DeleteRecord.userAccount = useraccount;
         getUserInfo( useraccount.getUsername(), useraccount.getPassword());
         
         DBConnection  conn = new DBConnection();
         conn.connectDatabase();
         
         String query = null;
         

         
         int userID =  DeleteRecord.userAccount.getUserID();
         
         if( DeleteRecord.userAccount.getUserType() == CONTRACTOR ){
             query = "delete from contractor where userID = ?";
          
         }else if( DeleteRecord.userAccount.getUserType() == FREELANCER ){
             query = "delete from freelancer where userID = ?";
            
         }
             PreparedStatement ps = conn.insertRecord(query);
             ps.setInt( 1,userID) ;                                         
             ps.execute();
             
             
         query = "delete from user where username = ? and password = ?";
         ps = conn.insertRecord(query);
         ps.setString( 1, useraccount.getUsername());
         ps.setString( 2, useraccount.getPassword());                                
         ps.execute();
             
    }

    private static void getUserInfo(String username , String password){
         DBConnection  conn = new DBConnection();
         conn.connectDatabase();

         conn.setStatement("select userID, userType from user where username =" + "'" + username + "'" + " and " + "password =" + "'"+ password + "'");
         ResultSet sqlResult  = conn.getStatement();

         int userID  = 0;
         int userType = 0;
         try{
             while( sqlResult.next() ){
                 userID = sqlResult.getInt("userID");
                 userType = sqlResult.getInt("userType");
             }
             DeleteRecord.userAccount.setUserID(userID);
             DeleteRecord.userAccount.setUserType(userType);
         }catch(Exception e){
             e.printStackTrace();
         }

         
    }

    
}
