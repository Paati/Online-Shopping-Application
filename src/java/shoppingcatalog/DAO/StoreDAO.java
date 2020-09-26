/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcatalog.DAO;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import shoppingcatalog.dbutil.DBConnection;
import shoppingcatalog.dto.ItemInfoDTO;
import shoppingcatalog.dto.itemDTO;
import java.util.Date;
import shoppingcatalog.dto.OrderDTO;
/**
 *
 * @author Paaty
 */
public class StoreDAO {
   
    private static Statement st,st2;
    private static PreparedStatement ps1,ps2,ps3,ps4,ps5,ps6,ps8,ps9;
    static{
    try{
    st=DBConnection.getConnection().createStatement();
    ps1=DBConnection.getConnection().prepareStatement("select id,item_name from store_items where item_type=?");
     ps2=DBConnection.getConnection().prepareStatement("select * from store_items where id=?");
       ps3=DBConnection.getConnection().prepareStatement("insert into order_master values(?,?,?,?)");
    ps4=DBConnection.getConnection().prepareStatement("insert into order_details values(?,?,?)");
    ps5=DBConnection.getConnection().prepareStatement("select count(*) as count from order_master");
    ps6=DBConnection.getConnection().prepareStatement("select *from order_master where cust_name=?");
     ps8=DBConnection.getConnection().prepareStatement("Select max(id) as count from store_items");
            ps9=DBConnection.getConnection().prepareStatement("Insert into store_items values(?,?,?,?,?,?)");
      st2=DBConnection.getConnection().createStatement();
   }   catch (SQLException ex) {
           System.out.println("fh");
        }
    
    }
     public static List<String> getItemTypes()throws SQLException
    {
    ArrayList<String> itemList=new ArrayList<String>();
    ResultSet rs=st.executeQuery("select distinct item_type from store_items");
    while(rs.next())
    {
    itemList.add(rs.getString(1));
    }
    return itemList;
    }
     public static List<ItemInfoDTO>getItemsByType(String type)throws SQLException
     {
      ArrayList<ItemInfoDTO>itemId=new ArrayList<ItemInfoDTO>();
     ps1.setString(1, type);
     ResultSet rs=ps1.executeQuery();
     if(rs.next())
     {
         ItemInfoDTO obj=new ItemInfoDTO();
         obj.setItemId(rs.getInt(1));
         obj.setItemName(rs.getString(2));
         itemId.add(obj);
     }
     return itemId;
     }
     
     public static itemDTO getItemDetails(int itemId)throws SQLException
     {
     itemDTO allItems= null;
     ps2.setInt(1,itemId);
     ResultSet rs=ps2.executeQuery();
     if(rs.next())
     {
         allItems.setItemId(itemId);
     allItems.setItemName(rs.getString("item_name"));
     allItems.setItemType(rs.getString("item_type"));
     allItems.setItemDesc(rs.getString("item_desc"));
     allItems.setItemImage(rs.getString("item_image"));
     allItems.setItemPrice(rs.getDouble("item_price"));
     }
     return allItems;
     }
     public static boolean addOrder(String custName,ArrayList<itemDTO>itemList,double totalAmount) throws SQLException
   {
       
       ResultSet rs=ps5.executeQuery();
      rs.next();
        int id=Integer.parseInt(rs.getString(1));
      String nextId="ORD-00"+(id+1);
      ps3.setString(1, nextId);
      ps3.setString(2, custName);
      ps3.setDouble(3, totalAmount);
      Date d=new Date();
      long ms=d.getTime();
      java.sql.Date currDate=new java.sql.Date(ms);
      ps3.setDate(4, currDate);
      int count=0;
      int ans1=ps3.executeUpdate();
      for(itemDTO item:itemList)
      {
      ps4.setString(1,nextId);
      ps4.setString(2,item.getItemName());
      ps4.setDouble(3, item.getItemPrice());
      int ans2=ps4.executeUpdate();
      if(ans2==1)
          count++;
      }
      return (ans1==1 && count==itemList.size());
      
   }
     public static ArrayList<OrderDTO> getOrdersByCustomer(String custName)throws SQLException
     {
         ArrayList<OrderDTO> orderList=new ArrayList<OrderDTO>();
     ps6.setString(1,custName);
     ResultSet rs=ps6.executeQuery();
     while(rs.next())
     {
     OrderDTO ob=new OrderDTO();
     ob.setOrderId(rs.getString(1));
     ob.setOrderAmount(rs.getDouble(3));
     ob.setOrderDate(rs.getDate(4));
     orderList.add(ob);
     }
     return orderList;
     }
     public static boolean addNewProduct(itemDTO obj)throws SQLException
{
    ResultSet rs=ps8.executeQuery();
    rs.next();
    int lastId=rs.getInt(1);
    int nextId=lastId+1;
    ps9.setInt(1, nextId);
    ps9.setString(2, obj.getItemType());
    ps9.setString(3, obj.getItemName());
    ps9.setDouble(4, obj.getItemPrice());
    ps9.setString(5, obj.getItemDesc());
    ps9.setString(6, obj.getItemImage());
    int ans=ps9.executeUpdate();
    return ans==1;
}
     public static ArrayList<Integer> getAllProductId()throws SQLException
{
   ArrayList<Integer> itemIdList=new ArrayList<Integer>();
   ResultSet rs=st2.executeQuery("Select id from store_items");
    while(rs.next())
    {
        itemIdList.add(rs.getInt(1));
    }
     
        System.out.println("List is of "+itemIdList.size()+" items");
        return itemIdList; 
}
}
