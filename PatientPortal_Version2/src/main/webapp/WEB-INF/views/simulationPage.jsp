<%@ page language="java" import="java.util.*,java.util.ArrayList" %> 
<html>
<head>
<title>Simulation</title>
<style>
	body{
    	background: #000000 url("resources/images/simulation.jpg") no-repeat fixed center;
    	background-size: 1600px 900px;
    	
	}
	.usecases{
		margin-up: 500px;
		margin-right:250px;
		margin-left:250px;
		height: 150px;

	}
	.button {
    background-color: black; /* Green #4CAF50*/
    border: none;
    color: white;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
    border: 2px;
    border-radius: 4px;
    border-color: white;
    
}
	.output{
	border-radius: 4px;
	overflow-y: auto;
	background-color: #F7F8E0;
	height: 400px;
	margin-left:80px;
	margin-right:80px;
	padding: 40px;
	}
</style>
</head>
<body>
	<div class="usercases" style="height:80px;">
	</div>
	<div class="usecases">
	<input type="button" class="button" value="Use Case 1" onclick="location.href='/myportal/submit.htm'" > 
	<button class="button" type="submit" name="Use Case 2" onclick="location.href='/myportal/submit2.htm'">Use Case 2</button>
	<button class="button" onclick="location.href='/myportal/submit3.htm'">Use Case 3</button>
	<button class="button" onclick="location.href='/myportal/submit4.htm'">Use Case 4</button>
	<button class="button" onclick="location.href='/myportal/submit5.htm'">Use Case 5</button>
	</div>
	<h2 style="text-align:center;">Output: After running the scripts</h2>
	<div class="output">
		
		<%  
		
		List<String> output = new ArrayList<String>();
        output = (List<String>) request.getAttribute("consoleOutput");
        if (output==null){
           out.println("");
        }else{
           for (int i = 0; i < output.size(); i++) {
                out.println(output.get(i)+"<br>");
                
            }
        }
	%>
	</div>
</body>
</html>