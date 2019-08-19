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
				<a class="btn  mgrt" href="/${user.id}/stories">My Stories</a> 
				<a class="btn  mgrt" href="/logout">Logout</a>
			</div>
		</div>
	</div>
	<div class="container"></div>

<section id="pricing">

    <h2 class = "center">Pick Your Path</h2>
    <p class = "center">You can read, create, or collab on an adventure!</p>

    <div class="row">


      <div class="pricing-column col-lg-4 col-md-6">
        <div class="card text-center">
          <div class="card-header">
            <h3>Read A Story</h3>
          </div>
          <div class="card-body">
            <h5>Read User Created Adventures</h5>
            <p>*Updated Daily*</p>
            <p>View the Top 10 User Voted Stories</p>
            <p>Reread your favorites with new choices</p>
            <button type="button" class="btn btn-lg btn-success">Let's Read!</button>
          </div>
        </div>
      </div>

        

        <div class="pricing-column col-lg-4">
          <div class="card text-center">
            <div class="card-header">
              <h3>Create A Story</h3>
            </div>
            <div class="card-body">
              <h5>Create a Story to Share</h5>
              <p>*Unlimited Endings*</p>
              <p>Collection of Your Stories Archived!</p>
              <p>Publish Your Work!</p>
              <button type="button" class="btn btn-lg btn-warning">Let's Create!</button>
            </div>
          </div>
        </div>
        
        <div class="pricing-column col-lg-4 col-md-6">
          <div class="card text-center">
          <div class="card-header">
            <h3>Collaborate A Story</h3>
          </div>
          <div class="card-body">
            <h5>Collaborate a Story</h5>
            <p>Endless Possibilities</p>
            <p>Create A Story and Collab With Other Authors</p>
            <p>Jump In and Write Endings for Stories</p>
            <button type="button" class="btn btn-lg btn-danger">Let's Collaborate!</button>
          </div>
        </div>
      </div>

      </div>

  </section>






</body>
<script>
	$('.dropdown-toggle').dropdown()
</script>

</html>