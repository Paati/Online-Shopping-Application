

<%@page import="shoppingcatalog.dto.ItemInfoDTO"%>
<%@page import="java.util.List"%>
<%
 String username=(String)session.getAttribute("username");
        if(username==null)
        {
        session.invalidate();
        response.sendRedirect("accessdenied.html");
        }
        else{
       StringBuffer displayBlock=new StringBuffer("</ul>");
       List<ItemInfoDTO> itemList=(List<ItemInfoDTO>)request.getAttribute("itemList");
       for(ItemInfoDTO obj:itemList)
       {
         displayBlock.append("<li id='"+obj.getItemId()+"'><a href='StoreControllerServlet?itemId="+obj.getItemId()+"'>"+obj.getItemName()+"</a></li>");
         
       }
       displayBlock.append("</ul>");
       out.println(displayBlock);
       }
        
%>
