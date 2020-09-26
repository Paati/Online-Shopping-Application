/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcatalog.DAO;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import shoppingcatalog.dbutil.DBConnection;
import shoppingcatalog.dto.UserDTO;

/**
 *
 * @author Paaty
 */
public class RegistrationDAO {
    private static PreparedStatement ps1,ps2;
    static{
    try{
    
    ps1=DBConnection.getConnection().prepareStatement("select username from members where username=? and membertype=?");
     ps2=DBConnection.getConnection().prepareStatement("insert into members values(?,?,?)");
    
    }   catch (SQLException ex) {
           System.out.println("fh");
        }
    
    }
    
    
    public static boolean searchUser(String username)throws SQLException
    {
    ps1.setString(1,username);
    ps1.setString(2,"CUSTOMER");
    ResultSet rs=ps1.executeQuery();
    if(rs.next())
        return true;
    return false;
    }
    public static boolean registerUser(UserDTO user)throws SQLException
    {
    boolean done=false;
    ps2.setString(1,user.getUserName());
    ps2.setString(2,user.getPassword());
    ps2.setString(3,user.getUserType());
    int ans=ps2.executeUpdate();
    if(ans!=0)
        done=true;
    return done;
    }
}
