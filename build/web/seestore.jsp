 <%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>See store</title>
        <link rel="stylesheet" type="text/css" href="styles/stylesheet.css">
        <script type="text/javascript" src="scripts/jquery.js"></script>
        <script type="text/javascript" src="scripts/showitems.js"></script>
        
    </head>
    <body>
      

        <%
        String username=(String)session.getAttribute("username");
        if(username==null)
        {
        session.invalidate();
        response.sendRedirect("accessdenied.html");
        }
        else{
        StringBuffer displayBlock=new StringBuffer("<h1>My Categories</h><p>Select a catagory to see its items.<p>");
        List<String>itemList=(List<String>)request.getAttribute("categories");
        for(String item:itemList)
        {
        displayBlock.append("<p id='"+item+"'><strong><a href='#' onclick=getItemNames('"+item+"')><span>+"+item+"</span></a></strong></p>");
        
        }
        displayBlock.append("<h4 id='logout'><a hreh='myorders.jsp'>My Orders</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='LoginControllerServlet?logout=logout'>Logout</a></h4><p id='we'></p>");
        out.println(displayBlock);
        }
        
        %>
        
        
        
    </body>
</html>
