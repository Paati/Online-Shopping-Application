     
function connect(){
       username=$("#username").val();
       password=$("#password").val();
       usertype=$("#usertype").val()
     if(validate())
     {
      return;    
     }
       var data={username:username,password:password,usertype:usertype};
        var request=$.post("LoginControllerServlet",data,processResponse);
        request.error(handleError);
     }
                
       
    
    function processResponse(responseText){
       $("#loginresult").text("Logged in successfull");
       var ts=responseText;
        setTimeout(function(){window.location=ts;},3000);
      }
    function handleError(xhr,textStatus){
        
       if(textStatus==='error')
       {
            $("#loginresult").text('An error occurred:'+xhr.status);
    }
    }
    function validate()
    {
        if(username==='' || password==='' || usertype==='')
        {
            if(username==='')
                $("#uname").html("UserName required").css("color","red");
            if(password==='')
                $("#pwd").html("Password required").css("color","red");
            
        return true;    
        }}
        function dev()
        
     {
         password=$("#password").val();
            if(password==='')
                $("#pwd").html("Password required").css("color","red");
       $("#uname").html("");    
        }
        function dev1()
        {
            username=$("#username").val();
            if(username==='')
                $("#uname").html("UserName required").css("color","red");
       $("#pwd").html("");    
        }