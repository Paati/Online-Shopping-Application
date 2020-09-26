

function getItemNames(itemType)
{  
    
item=itemType;

var para=document.getElementById(itemType);
var span=para.getElementsByTagName("span")[0];
var spanText=span.innerHTML.trim();
if(spanText.indexOf("+")!==-1)
{
    span.innerHTML="-"+itemType;
}
else if(spanText.indexOf("-")!==-1)
{
    span.innerHTML="+"+itemType;
    $("#"+item+" .itemNames").hide("slow");
    return;
}
var mydata={itemType:itemType};
var request=$.post("StoreControllerServlet",mydata,processResponse);
request.error(handleError);
}
function processResponse(responseText)
{
    var para=document.getElementById(item);
    var resp=responseText;
    resp=resp.trim();
    var olddiv=para.getElementsByClassName("itemNames")[0];
    if(typeof olddiv!=='undefined')
    {
        para.removeChild(olddiv);
    }
    var newdiv=document.createElement("div");
    newdiv.setAttribute("class","itemNames");
    newdiv.innerHTML=resp;
    para.appendChild(newdiv);
}
function handleError(xhr,textStatus){
        
       if(textStatus==='error')
       {
            $("#we").text('An error occurred:'+xhr.status);
        }
    }