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
 
  Welcome to my Alchemy Page! <br>
 
        <form action="AlchemyAPI">
            Enter the link to be analyzed: <input type="text" name="words" value="www.cnn.com" size="80px"> <br><br>
        <input type="submit" value="submit">
        </form>
 
		  <form action="AlchemyAPI2">
		     Enter the text to be analyzed: <input type="text" name="words2" size="80px"> <br><br>
		 	<input type="submit" value="submit">
		        </form>
	 	<div>
			<p>Return to the homepage. <a href="index.jsp">Click here</a>.</p>
 		</div>
    </div>
 
</body>
</html>
