<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
    <%@ page import = "java.io.*,java.util.*, javax.servlet.*" %>
<html>

 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JSON Convertor</title>
<style> 
input[type=button], input[type=submit], input[type=reset] {
  background-color: #4CAF50;
  border: none;
  color: white;
  padding: 16px 32px;
  text-decoration: none;
  margin: 4px 2px;
  cursor: pointer;
}
</style>
</head>
<body>

 
    <div align="center" style="margin-top: 50px;">
    <img src="grootan.jpg" width="100" height="100">
    <h2 align="center">Grootan Technologies Pvt Ltd - Campus Hiring</h2>
    <%
         Date date = new Date();
         out.print( "<h4 align = \"center\">" +date.toString()+"</h4>");
      %>
 		<marquee behavior="scroll" bgcolor="green" style="color: white">Stay Home! Stay Safe! We stand separately to fight COVID Unitedly!</marquee><br><br><br>
        <form action="JSONServlet">
            JSON Request:  <input type="text" name="jsonString" id="jsonString" style="height: 200px;"> <br><br><br><br>
            <input type="submit"  value="Submit">
        <input type="submit"  value="Download">
        </form>
 
    </div>
 
</body>
</html>