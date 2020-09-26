
$(document).ready(
        function(){$("#nextbtn").click(function(){dotask()});
}
);
function dovalidate()
{
   $("#result").css("display","inline");
   isRegChecked=$("#rbtnReg").is(':checked');//true or false if checked or not
   isLogChecked=$("#rbtnLog").is(':checked'); 
  status=true;
   if(isRegChecked==false && isLogChecked==false)
   {
       $("#result").text("Please select an option first");
       $("#result").css("font-weight","bold");
       $("#result").css("color","red");
       $("#result").fadeOut(4000);
       status=false;
   }
   return status;
}

function dotask(){
   var ans=dovalidate();
   if(ans==false)
   {
       
       return;
   }
   
       if(isRegChecked==true)
       {
           window.location="registration.html";//location in place of sendRedirect as of servlet
           
       }
       else if(isLogChecked==true)
       {
           window.location="login.html";
       }
     }