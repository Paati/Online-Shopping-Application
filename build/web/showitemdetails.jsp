<%@page import="shoppingcatalog.dto.itemDTO"%>
<%@page import="java.util.List"%>
<%
        String username=(String)session.getAttribute("username");
        if(username==null)
        {
        session.invalidate();
        response.sendRedirect("accessdenied.html");
        }
        else{
            itemDTO item=(itemDTO)request.getAttribute("item");
            StringBuffer displayBlock=new StringBuffer("<h1>My Store-Item Details</h><p><em>You are viewing:</em></br>");
            displayBlock.append("<strong> <a href='StoreControllerServlet'>"+item.getItemType()+"&gt;</a>"+item.getItemType()+"</strong></p>");
            displayBlock.append("<div style='float:left;'>");
            displayBlock.append("<img src=\'images/"+item.getItemImage()+"'></div>");
            displayBlock.append("<div style='float:left;padding-left:12px'>");
            displayBlock.append("<p><strong>Descritpion:</strong>br"+item.getItemDesc()+"</p>");
            displayBlock.append("<p><strong>Price:</strong>Rs."+item.getItemPrice()+"</p>");
            displayBlock.append("<p><a href='AddToCart.jsp'?itemId="+item.getItemId()+">Add To Cart</a></p></div>");
            displayBlock.append("<h4 id='logout'><a hreh='myorders.jsp'>My Orders</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='LoginControllerServlet?logout=logout'>Logout</a></h4><p id='we'></p>");
        out.println(displayBlock);
        }
%>