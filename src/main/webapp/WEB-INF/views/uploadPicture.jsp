<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Upload Picture</title>
</head>
<body>
<p1>Please upload the picture for this patient: </p1><br><br>
<p3>Please choose the file to upload</p3>
<form action="createpatient" method="post" enctype="multipart/form-data">
<br><input type="file" name="file" size="50" />
<br><br><input type="submit" name="submit" value="Create patient" />
</form>
</body>
</html>