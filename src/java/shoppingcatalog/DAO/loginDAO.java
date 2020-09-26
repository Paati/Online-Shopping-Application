/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcatalog.DAO;

import java.sql.*;
import shoppingcatalog.dto.UserDTO;
import shoppingcatalog.dbutil.DBConnection;
/**
 *
 * @author Paaty
 */
public class loginDAO {
    
    private static PreparedStatement ps;
    static{
    try{
                ps=DBConnection.getConnection().prepareStatement("select * from members where username=? and password=? and membertype=?");
    
    }
    catch(Exception e){
        System.out.println("error in db"+e);
    }
    
    }    
     public  static boolean validateUser(UserDTO user)throws SQLException
    {
        boolean result=false;
        ps.setString(1,user.getUserName());
        ps.setString(2,user.getPassword());
        ps.setString(3,user.getUserType());
        ResultSet rs=ps.executeQuery();
        if(rs.next())
            return true;
        return result;
    }
}
