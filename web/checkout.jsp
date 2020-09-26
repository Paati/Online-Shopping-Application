<%-- 
    Document   : checkout
    Created on : Jan 9, 2019, 10:39:46 AM
    Author     : HP
--%>

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
           String totalAmount=request.getParameter("totalAmount");
           StringBuffer db=new StringBuffer("<h1>amount:</h1>"+totalAmount);
           out.println(db);
           }
       %>
    </body>
</html>
