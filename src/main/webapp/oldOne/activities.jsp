<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Activities</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
	<section class="page-section portfolio" id="portfolio">
        <div class="container">
            <div class="row justify-content-center">        
                <c:forEach items="${activities}" var="activity">
                    <!-- Portfolio Item 2-->
                    <div class="col-md-4">
                        <div class="card mb-3" >
                            <div class="card-header">
                                <p>${activity.name}</p>
                            </div>
                            <div class="card-body">
                                <p>${activity.id}</p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>
</body>
</html>