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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author dmagadi
 */
public class UserBO {

    String ip;
    String user;
    String password;

    public UserData login(String userName, String password, String ip, String serverUser, String serverPassword) {
        this.ip = ip;
        this.user = serverUser;
        this.password = serverPassword;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            conn = DBConnectionHandler.getConnectionToDatabase(ip, serverUser, serverPassword);

            pstmt = conn.prepareStatement("Select * from users where username = ? and password = ?");

            pstmt.setString(1, userName);
            pstmt.setString(2, password);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                Logger.getLogger(getClass().getName()).info(String.format("User First Name is %s ", rs.getString("FirstName")));
                UserData user = new UserData();
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setIsAdmin(rs.getBoolean("isAdmin"));
                return user;
            }

        } catch (Exception e) {

            throw new RuntimeException(e);

        } finally {// always close connection/preparedstatment/statement/resultset here 
            DBConnectionHandler.closeRS(rs);
            DBConnectionHandler.closePreparedStatement(pstmt);
            DBConnectionHandler.closeConnection(conn);

        }

        return null;

    }

    public Boolean addUser(String userName, String password, String firstName, String lastName, int adminValue) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        Boolean userSuccessfullyAdded = true;
        try {

            conn = DBConnectionHandler.getConnectionToDatabase(this.ip, this.user, this.password);
            pstmt = conn.prepareStatement("INSERT INTO `cms`.`users` (`username`, `password`, `FirstName`, `LastName`, `createdts`) VALUES (?, ?, ?, ?, now());");
            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            pstmt.setString(3, firstName);
            pstmt.setString(4, lastName);
            pstmt.execute();

        } catch (Exception e) {
            userSuccessfullyAdded = false;
            throw new RuntimeException(e);

        } finally {
            DBConnectionHandler.closePreparedStatement(pstmt);
            DBConnectionHandler.closeConnection(conn);
        }
        return userSuccessfullyAdded;
    }

    public ObservableList<UserData> getUsers() {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            conn = DBConnectionHandler.getConnectionToDatabase(this.ip, this.user, this.password);

            pstmt = conn.prepareStatement("Select * from users");

            rs = pstmt.executeQuery();

            ObservableList<UserData> userDataList = FXCollections.observableArrayList();
            while (rs.next()) {
                UserData user = new UserData();
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setIsAdmin(rs.getBoolean("isAdmin"));
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setId(rs.getInt("id"));
                user.setCreatedTS(rs.getTimestamp("createdts"));
                user.setModifiedTS(rs.getTimestamp("modifiedts"));
                userDataList.add(user);
            }

            return userDataList;

        } catch (Exception e) {

            throw new RuntimeException(e);

        } finally {// always close connection/preparedstatment/statement/resultset here 
            DBConnectionHandler.closeRS(rs);
            DBConnectionHandler.closePreparedStatement(pstmt);
            DBConnectionHandler.closeConnection(conn);

        }

        //return null;
    }

    public void deleteUser(UserData selectedUser) {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {

            conn = DBConnectionHandler.getConnectionToDatabase(this.ip, this.user, this.password);

            pstmt = conn.prepareStatement("DELETE FROM `cms`.`users` WHERE `id`=?;");

            pstmt.setInt(1, selectedUser.getId());

            pstmt.execute();

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DBConnectionHandler.closePreparedStatement(pstmt);
            DBConnectionHandler.closeConnection(conn);
        }

    }

}
