<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Add Activity</title>
</head>
<body>
	<form:form action="addActivity" method="post" modelAttribute="activity">

		<form:label path="name">
			<form:input path="name" class="form-control" placeholder="Enter Name" id="name" />
		</form:label>

		<input type="submit" name="">
	</form:form>
</body>
</html>