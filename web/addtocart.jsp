<%-- 
    Document   : addtocart
    Created on : Jan 9, 2019, 9:11:38 AM
    Author     : HP
--%>

<%@page import="shoppingcatalog.DAO.StoreDAO"%>
<%@page import="shoppingcatalog.dto.itemDTO"%>
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
           int itemId=Integer.parseInt((String)request.getParameter("itemId"));
           itemDTO item=StoreDAO.getItemDetails(itemId);
           session.setAttribute(String.valueOf(item.getItemId()), item);
              StringBuffer db=new StringBuffer("<h1>My Store Shopping</h1>");
              db.append("<div style='float:left;'>");
              db.append("<p><strong>Item Added Successfully..!</p></strong>");
              db.append("<p><strong>ItemId</strong>"+item.getItemId()+"</p>");
              db.append("<p><strong>Item Name</strong>"+item.getItemName()+"</p>");
              Enumeration en=session.getAttributeNames();
              int count=0;
              while(en.hasMoreElements())
              {
              ++count;
              en.nextElement();
              }
              db.append("<p><h3>Total Items in Cart</h3>"+(count-1)+"</p>");
              db.append("<p><a href='StoreControllerServlet'>ContinueShopping</a>");
              db.append("<a href='placeorder.jsp'>Place Order</a></p></div>");
              db.append("<h4 id='logout'><a href='myorders.jsp'>My Orders</a>&nbsp;&nbsp;&nbsp;<a href='LoginControllerServlet?logout=logout'>Logout</a></h4>");
              out.println(db);
           }
           %>
    </body>
</html>