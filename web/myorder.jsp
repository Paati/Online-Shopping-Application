<%@page import="java.text.SimpleDateFormat"%>
<%@page import="shoppingcatalog.dto.OrderDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="shoppingcatalog.DAO.StoreDAO"%>
<%
     String username=(String)session.getAttribute("username");
        if(username==null)
        {
        session.invalidate();
        response.sendRedirect("accessdenied.html");
        }
        else{
         ArrayList<OrderDTO> details=StoreDAO.getOrdersByCustomer(username);
        StringBuffer displayBlock=new StringBuffer("<h1>My Store-My Oreder</h1>");
        displayBlock.append("<div style='float:left;'>");
        if(details.size()==0){
        displayBlock.append("<p><strong>You have not placed any order yet</strong></p>");
        }
        else{
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM=YYYY");
        displayBlock.append("<table border='1'>");
        displayBlock.append("<tr><th>Order Id </th><th>Order Amount</th><th>Order Date</th></tr>");
        for(OrderDTO o:details)
        {
        String dateStr=sdf.format(o.getOrderDate());
        displayBlock.append("<tr><td>"+o.getOrderId()+"</td></tr>"+o.getOrderAmount()+"</td><td>"+dateStr+"</td></tr>");
        }
        }
        displayBlock.append("</table>");
        displayBlock.append("<p><a href='StoreControllerServlet'>Sgow Categories</a></p></div>");
        displayBlock.append("<h4 id='logout'><a href='LoginControllerServlet?logout=logout'>Logout</a></h4>");
                out.println(displayBlock);
        }
        }
        }

%>