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
<link rel="stylesheet" href='<c:url value="/css/create-story.css"/>'
	type="text/css" />
<script type="text/javascript" src="js/myscript.js"></script>
<meta charset="UTF-8">
<title>Solo Create</title>
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
<meta name="viewport" content="width=device-width, initial-scale=1">



</head>
<body>
<body>
	<div
		class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-black border-bottom box-shadow">
		<h5 class="my-0 mr-md-auto font-weight-normal wh">Let's Create A
			Story!</h5>

		<nav class="my-2 my-md-0 mr-md-3 mgb"></nav>
		<div class="dropdown mvl">
			<button class="btn btn-light dropdown-toggle" type="button"
				id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false">
				<c:out value="${user.firstName}"></c:out>
			</button>
			<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
				<a class="btn  mgrt" href="/home">Home</a> <a class="btn  mgrt"
					href="/${user.id}/stories">My Stories</a> <a class="btn  mgrt"
					href="/logout">Logout</a>
			</div>
		</div>
	</div>
	<div class="container"></div>
	<img class="book" src="/images/book.png">

	<form action="/createNextPath/${path.id}" method="POST">
		<section class="inside-book-left">
			<h3><c:out value = "${path.title}"></c:out></h3>
			<h4><c:out value = "${path.choice}"></c:out></h4>
			<div class="form-group">
				<label class = "mgt" for="exampleFormControlTextarea1">What Happens Next:</label>
				<textarea name="stage" class="form-control"
					id="exampleFormControlTextarea1" rows="12"></textarea>
			</div>
		</section>

		<div class="inside-book-right">
			<div class="card text-white bg-success mb-3"
				style="max-width: 18rem;">
				<div class="card-header">
					<input type="text" name="pathOneTitle" class="form-control"
						id="InputTitle" aria-describedby="emailHelp"
						placeholder="Enter path title" />
				</div>
				<div class="card-body">
					<textarea name="pathOne" class="form-control"
						id="exampleFormControlTextarea1" rows="4"></textarea>
				</div>
			</div>
			<div class="card text-white bg-warning mb-3"
				style="max-width: 18rem;">
				<div class="card-header">
					<input type="text" name="pathTwoTitle" class="form-control"
						id="InputTitle" aria-describedby="emailHelp"
						placeholder="Enter path title" />
				</div>
				<div class="card-body">
					<textarea name="pathTwo" class="form-control"
						id="exampleFormControlTextarea1" rows="4"></textarea>
				</div>
			</div>
			<div class="card text-white bg-danger mb-3" style="max-width: 18rem;">
				<div class="card-header">
					<input type="text" name="pathThreeTitle" class="form-control"
						id="InputTitle" aria-describedby="emailHelp"
						placeholder="Enter path title" />
				</div>
				<div class="card-body">
					<textarea name="pathThree" class="form-control"
						id="exampleFormControlTextarea1" rows="4"></textarea>
				</div>
			</div>
		</div>

		<a href="/"><button type="submit"
				class="btn btn-primary submit-book">Continue</button></a>
	</form>

</body>
<script>
	$('.dropdown-toggle').dropdown()
</script>

</html>