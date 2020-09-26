<%-- 
    Document   : placeorder
    Created on : Jan 9, 2019, 9:35:16 AM
    Author     : HP
--%>


<%@page import="shoppingcatalog.DAO.StoreDAO"%>
<%@page import="shoppingcatalog.dto.itemDTO"%>
<%@page import="java.util.ArrayList"%>

<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
          <title>Store Items</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          <link rel="stylesheet" type="text/css" href="styles/stylesheet.css" >
        <script src="scripts/jquery.js" type="text/javascript"></script>
    <script src="scripts/ShowItems.js" type="text/javascript"></script> 
    </head>
    <body>
 <%
          String username=(String)session.getAttribute("username");
           if(username==null)
           {
           session.invalidate();
           response.sendRedirect("accessdenied.html");
           }
           else
           {
               ArrayList<itemDTO> itemList=new ArrayList<itemDTO>();
              StringBuffer db=new StringBuffer("<h1>My store-Order</h1>"); 
           Enumeration en=session.getAttributeNames();
           db.append("<table broder='2'>");
           db.append("<tr><th>Item Name</th><th>ItemPrice</th></tr>");
           double amount=0.0;
           while(en.hasMoreElements())
           {
               Object o=en.nextElement();
           if(o.equals("username")==false)
           {
         itemDTO item=(itemDTO)session.getAttribute(o.toString());
         db.append("<tr><td>"+item.getItemName()+"</td><td>"+item.getItemPrice()+"</td></tr>");
         amount+=item.getItemPrice();
         itemList.add(item);
           }
           }
           db.append("</table>");
           boolean val=StoreDAO.addOrder(username,itemList,amount);
       
               
            db.append("<p><a href='StoreControllerServlet'>ContinueShopping</a>");
           db.append("<a href='checkout.jsp?totalAmount="+amount+"'>CheckOut</a></p></div>");
           out.println(db);
           }
          %>
    </body>
</html>