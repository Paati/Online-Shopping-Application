<%
boolean result=(Boolean)request.getAttribute("result");
boolean userFound=(Boolean)request.getAttribute("userFound");
if(userFound)
out.println("uap");
else{
if(result)
out.println("success");
else
out.println("failure");
}


%>