<%@ page language="java" contentType="text/html;	charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true"%>
<c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>
<!DOCTYPE HTML>
<html>
<head>
<title>Let's Eat | Error Page</title>
<%@include file="include/init.jsp"%>
</head>
<body>
	<div class="container">
		<header>
			<div class="row">
				<div class="page-header">
					<h1>Let's Eat | Error</h1>
				</div>
			</div>
		</header>
		<nav>
			<div class="row">
				<ol class="breadcrumb">
					<li><a href="/index.jsp">Home</a></li>
					<li class="active">Error</li>
				</ol>
			</div>
		</nav>
		<section>
			<div class="row">
				<table class="table table-bordered table-condensed table-striped">
					<col style="width: 20%">
					<col style="width: 80%">
					<thead>
						<tr>
							<td colspan="2">DESCRIPCION</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>Mensaje</th>
							<td><%=exception.toString()%></td>
						</tr>
						<tr>
							<th>Stack</th>
							<td><jsp:scriptlet>
  							exception.printStackTrace(new java.io.PrintWriter(out));
							</jsp:scriptlet></td>
						</tr>
					</tbody>
				</table>
			</div>
		</section>
		<footer>
			<%@include file="include/footer.jsp"%>
		</footer>
	</div>
</body>
</html>