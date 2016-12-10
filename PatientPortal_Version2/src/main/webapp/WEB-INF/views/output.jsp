<%@ page language="java" import="java.util.*,java.util.ArrayList" %> 
<html>
<head></head>
<body>
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