<html>
<head>
<title>Patient page</title>
<style>
	body{
    	
    	background: #000000 url("resources/images/home1.jpg") no-repeat fixed center;
    	background-size: 1600px 900px;
	}
	div{
		
    	width: 100%;
    	height: 100%;
		position: absolute;
		background-color: rgba(0,0,0,0.4);
	}
}
</style>
</head>
<body>
<div>
<h3>Welcome user: <%= request.getParameter("uname") %></h3>
</div>
</body>
</html>