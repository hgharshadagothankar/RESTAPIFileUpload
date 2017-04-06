<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>
<a href="uploadform">Upload Image</a>  
</h1>

<h2>File Upload Example</h2>  
<form action="rest/files/upload" method="post" enctype="multipart/form-data">  
       <p>  
        Select a file : <input type="file" name="file" size="45" />  
       </p>  
       <input type="submit" value="Upload File" />  
</form>  
</body>
</html>