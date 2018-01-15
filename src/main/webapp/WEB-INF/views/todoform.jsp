<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>﻿
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<title>Create</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<link rel="stylesheet" type="text/css"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" />

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<div class="container">
		<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Todo</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="#">Add Todo</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
		</nav>

		<c:set var="isEditingMode" value="${isEditing}" />
		<c:set var="action" value="${isEditingMode ? '../edit/':'./create'}"/>
		
		<form action="${action}" method="post">
		
			<div class="form-group">
				<label for="title">Title:</label> <input type="text"
					class="form-control" name="title" value="${todo.title}">
			</div>

			<p class="text-danger">
				<form:errors path="todo.title" />
			</p>

			<div class="form-group">
				<label for="description">Description:</label>
				<textarea class="form-control" rows="5" id="comment"
					name="description">${todo.description}</textarea>
			</div>

			<p class="text-danger">
				<form:errors path="todo.description" />
			</p>

			<fmt:formatDate value="${todo.completeBy}" var="completeByDate" type="date"
				pattern="dd/MM/yyyy" />
				
			<div class="form-group">
				<label for="completeBy">Complete By:</label> <input type="text"
					class="form-control" name="completeBy" id="datepicker"
					value="${completeByDate}">
			</div>

			<p class="text-danger">
				<form:errors path="todo.completeBy" />
			</p>

			<c:if test="${isEditingMode}">
				<div class="form-group">
					<label for="createdOn">Created On:</label> <input type="text"
						class="form-control" name="createdOn" value="${todo.createdOn}"
						disabled>
				</div>

				<div class="form-group">
					<label for="modifiedOn">Modified On:</label> 
					<input type="text" class="form-control" name="modifiedOn" value="${todo.modifiedOn}" disabled>
				</div>
				
				<input type="hidden" name="id" value="${todo.id}">
			</c:if>
			
			<button type="submit" class="btn btn-primary">Submit</button>

		</form>

	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<!-- Latest compiled and minified JavaScript -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
		crossorigin="anonymous"></script>

	<script
		src=" http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
</body>

<script>
	$(document).ready(function() {
		$("#datepicker").datepicker({
			dateFormat : 'dd/mm/yy'
		});
	});
</script>

</html>
