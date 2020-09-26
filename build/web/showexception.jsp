<%
Exception ex=(Exception)request.getAttribute("exception");
String msg=ex.getMessage();
System.out.println("Exception is 00000000000000000000000000000000000000:"+ex);
out.println(msg);
%>