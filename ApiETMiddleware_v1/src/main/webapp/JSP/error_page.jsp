<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<link type="text/css" href="favicon.ico" rel="shortcut icon"
	type="image/x-icon" />
<link type="text/css" rel="stylesheet"
	href="Fonts/Roboto-Font/Roboto-RCondensed.css" />
<link type="text/css" rel="stylesheet"
	href="Fonts/Font-Awesome/font-awesome.css" />
<link type="text/css" rel="stylesheet" media="screen"
	href="CSS/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" media="screen"
	href="CSS/style.css" />
 <style>
.err-page-container {
position: absolute;
background-color: #e2e2e2;
width: 100%;
height: 100%;
display: table;
text-align: center;

background: #03496e;  
background: -moz-linear-gradient(-45deg, #03496e 0%, #0f7daf 50%, #1088b6 100%);  
background: -webkit-linear-gradient(-45deg, #03496e 0%,#0f7daf 50%,#1088b6 100%); 
background: linear-gradient(135deg, #03496e 0%,#0f7daf 50%,#1088b6 100%);  
filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#03496e', endColorstr='#1088b6',GradientType=1 ); 
}

.err-page-container .err-page-inner-container {
display: table-cell;
vertical-align: middle;
}

.err-page-container .err-page-inner-container .err-icon {
font-size: 100px;
padding: 0px 0px 30px 0px;
border-radius: 100%;
color: #FFF;
text-shadow: 10px 10px 5px #00395f;
}

.err-page-container .err-page-inner-container .err-txt {
margin: 0px;
padding: 0px;
font-size: 24px;
color: #FFF;
font-weight: 200;
letter-spacing: 0.5px;
}
</style>
<body>

<!-- <form method="POST" action='ctx1' + "/user_validation1" enctype="multipart/form-data">
 -->
 <form method="POST" action="${pageContext.request.contextPath}/user_validation1" enctype="multipart/form-data">
<div class="err-page-container">
<div class="err-page-inner-container">
<i class="fa fa-user-times err-icon"></i>
<p class="err-txt">${message}</p>
</div>
</div>


    
    
    
    
    
    
</form>





</body>
</html>