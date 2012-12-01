$(function() {
$('#newForm').form({  
    url:'../json/sendMail.action',  
    onSubmit: function(){  
        // do some check  
        // return false to prevent submit;  
    },  
    success:function(resultObj){
    	if(resultObj){
    		alert("Success!");
        	window.parent.$('#main').tabs('close','New Mail');
    	}
    }  
	});

})


function submitNewForm(){
	// submit the form  
	$('#newForm').submit(); 
};