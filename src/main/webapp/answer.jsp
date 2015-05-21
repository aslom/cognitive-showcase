<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style type="text/css">
body {
    background-image:
        url('http://cdn3.crunchify.com/wp-content/uploads/2013/03/Crunchify.bg_.300.png');
}
</style>
 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Crunchify JSP Servlet Example</title>
</head>
<body>
 
    <div align="center" style="margin-top: 50px;">
 
        <form action="QAServlet">
            Welcome to my Question and Answer Page! <br>
            Enter your Health Question: <input type="text" name="question" size="80px"> <br><br>
        <input type="submit" value="submit">
        </form>
 
		 <div>
			<p>Return to the homepage. <a href="index.jsp">Click here</a>.</p>
 		</div>
 
    </div>
 
</body>
</html>
