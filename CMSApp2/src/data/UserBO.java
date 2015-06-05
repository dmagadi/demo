/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;


import data.helper.DBConnectionHandler;
import data.model.UserData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

/**
 *
 * @author dmagadi
 */
public class UserBO {
    
    public UserData login(String userName, String password){
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            
            conn = DBConnectionHandler.getConnectionToDatabase();
            
            pstmt = conn.prepareStatement("Select * from users where username = ? and password = ?");
            
            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            
            rs = pstmt.executeQuery();
            
            if(rs.next()){
                Logger.getLogger(getClass().getName()).info(String.format("User First Name is %s ", rs.getString("FirstName")));
                UserData user = new UserData();
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setIsAdmin(rs.getBoolean("isAdmin"));
                return user;
            }
            
            
        }catch(Exception e){
            
            throw new RuntimeException(e);
            
        }finally{// always close connection/preparedstatment/statement/resultset here 
            DBConnectionHandler.closeRS(rs);
            DBConnectionHandler.closePreparedStatement(pstmt);
            DBConnectionHandler.closeConnection(conn);
            
            
        }
        
        
        
        return null;
        
    }
    
}
