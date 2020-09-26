     
function connects(){
       username=$("#username").val();
       password=$("#password").val();
       
     if(validate())
     {
      return;    
     }
        data={username:username,password:password};
         request=$.post("RegistrationControllerServlet",data,processResponse);
        request.error(handleError);
     
     }
     function processResponse(responseText){
      
        $("#regresult").html(responseText);
       
       }
    function handleError(xhr,textStatus){
        
       if(textStatus==='error')
       { 
            $("#regresult").text('An error occurred:'+xhr.status);
    }
    }
    function validate()
    {
        if(username==='' || password==='')
        {
            if(username==='')
                $("#uname").html("UserName required").css("color","red");
            if(password==='')
                $("#pwd").html("Password required").css("color","red");
            
        return true;    
        }}
        function dev2()
        
     {
         password=$("#password").val();
            if(password==='')
                $("#pwd").html("Password required").css("color","red");
       $("#uname").html("");    
        }
        function dev3()
        {
            username=$("#username").val();
            if(username==='')
                $("#uname").html("UserName required").css("color","red");
       $("#pwd").html("");    
        }
    

