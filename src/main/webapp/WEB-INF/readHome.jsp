<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" href='<c:url value="/css/home.css"/>'
	type="text/css" />
<script type="text/javascript" src="js/myscript.js"></script>
<meta charset="UTF-8">
<title><c:out value="${user.firstName}"></c:out>'s Home</title>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
</head>
<body>
<body>
	<div
		class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-black border-bottom box-shadow">
		<h5 class="my-0 mr-md-auto font-weight-normal wh">Welcome to
			endfinite!</h5>

		<nav class="my-2 my-md-0 mr-md-3 mgb"></nav>
		<div class="dropdown mvl">
			<button class="btn btn-light dropdown-toggle" type="button"
				id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false">
				<c:out value="${user.firstName}"></c:out>
			</button>
			<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
				<a class="btn  mgrt" href="/home">Home</a>
				<a class="btn  mgrt" href="/stories/${user.id}">My Stories</a> <a
					class="btn  mgrt" href="/logout">Logout</a>
			</div>
		</div>
	</div>
	<div class="container">

	<table class="table">
		<thead class="thead-dark">
			<tr>
				<th scope="col">Title</th>
				<th scope="col">Author</th>
				<!-- <th scope="col">Collaborators?</th> -->
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${publishedStories}" var="story">
		
			<tr>
				<th scope="row"><a href="/create/${story.id}"><c:out value = "${story.title}"></c:out></a></th>
				<th scope="row"><c:out value = "${story.author.firstName}"></c:out></th>
				<%-- <th scope="row"><c:out value = "${story.collaborators.size()}"></c:out></th> --%>
			</tr>
			</c:forEach>
		</tbody>
	</table>

</div>
</body>
<script>
	$('.dropdown-toggle').dropdown()
</script>

</html>