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
<link rel="stylesheet" href='<c:url value="/css/login.css"/>'
	type="text/css" />
<script type="text/javascript" src="js/myscript.js"></script>
<meta charset="UTF-8">
<title>endfinite</title>
</head>
<body>
	<div class="container-fluid">
	<img class = "top" src="/images/inf.png" style = "width: 400px"> 
	<p class = "tag">Where stories have endless possibilities!</p>
		<div class="container">
		   
			<div class="row">
				<div class="col-md-5 bx">


					<p class="text-uppercase pull-center wh">Register Here!</p>

					<p class="red">
						<form:errors path="user.*" />
					</p>

					<form:form method="POST" action="/register"
						modelAttribute="user">
						<div class="form-group">
							<form:label for="InputFirstName" path="firstName">First Name</form:label>
							<form:input type="text" path="firstName" class="form-control"
								id="InputFirstName" aria-describedby="fNameHelp"
								placeholder="Enter first name" />
						</div>
						<div class="form-group">
							<form:label for="InputLastName" path="lastName">Last Name</form:label>
							<form:input type="text" path="lastName" class="form-control"
								id="InputLastName" aria-describedby="lNameHelp"
								placeholder="Enter last name" />
						</div>
						<div class="form-group">
							<form:label for="exampleInputEmail1" path="email">Email address</form:label>
							<form:input type = "email" path="email" class="form-control"
								id="exampleInputEmail1" aria-describedby="emailHelp"
								placeholder="Enter email"/> <small id="emailHelp"
								class="form-text text-muted">We'll never share your
								email with anyone else.</small>
						</div>

						<div class="form-group">
							<form:label for="InputPassword1" path="password">Password</form:label>
							<form:password path="password" class="form-control"
								id="InputPassword1" placeholder="Enter password" />
						</div>

						<div class="form-group">
							<form:label for="InputConfPassword1" path="passwordConfirmation">Confirm Password</form:label>
							<form:password path="passwordConfirmation" class="form-control"
								id="InputConfPassword1" placeholder="Enter confirmation" />
						</div>

						<button type="submit" class="btn btn-dark">Create Account</button>
					</form:form>
				</div>

				<div class="col-md-2 "></div>

				<div class="col-md-5 bx1">
				<p class="text-uppercase pull-center wh">Login Here!</p>
					<p class = "red">
						<c:out value="${error}" />
					</p>
					<form method="post" action="/login">
						<div class="form-group">
							<label for="exampleInputEmail1">Email address</label> <input
								type="email" name="email" class="form-control"
								id="exampleInputEmail1" aria-describedby="emailHelp"
								placeholder="Enter email">
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">Password</label> <input
								type="password" name="password" class="form-control"
								id="exampleInputPassword1" placeholder="Password">
						</div>

						<button type="submit" class="btn btn-dark">Submit</button>
					</form>


				</div>
			</div>
		</div>
	</div>
</body>


</html>