<%@ page language="java" contentType="text/html;	charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Let's Eat | Service API</title>
<%@include file="include/init.jsp"%>
</head>
<body>
	<div class="container">
		<header>
			<div class="row">
				<div class="page-header">
					<h1>Let's Eat | Service API</h1>
				</div>
			</div>
		</header>
		<nav>
			<div class="row">
				<ol class="breadcrumb">
					<li class="active">Home</li>
				</ol>
			</div>
		</nav>
		<section>
			<div class="row">
				<form class="form-inline pull-right padded" action="adminLogin"
					method="post" role="form">
					<div class="form-group">
						<label for="titulo">User:</label> <input type="text"
							class="form-control" id="name" name="name" required>
					</div>
					<div class="form-group">
						<label for="comment">Pass:</label> <input type="password"
							class="form-control" id="pass" name="pass" required>
					</div>
					<button type="submit" class="btn btn-primary">
						Registro de Restaurantes <span
							class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					</button>
				</form>
			</div>
		</section>
		<section>
			<div class="row">
				<table class="table table-bordered table-condensed table-striped">
					<col style="width: 20%">
					<col style="width: 80%">
					<thead>
						<tr>
							<td colspan="2">SIGN-UP</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>Descripci&oacute;n</th>
							<td>Registro en la aplicaci&oacute;n</td>
						</tr>
						<tr>
							<th>URL</th>
							<td>http://12-dot-com-silicon-letseat.appspot.com/rest/<strong>singup</strong></td>
						</tr>
						<tr>
							<th>Request (datos de ejemplo)</th>
							<td>{"request":{"email":"marisa@gmail.com","name":"marisa","pass":"marisa"}}</td>
						</tr>
						<tr>
							<th>Response (con &eacute;xito)</th>
							<td>{"code":"SU_OK","desc":"Usuario Registrado
								Correctamente"}</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="row">
				<table class="table table-bordered table-condensed table-striped">
					<col style="width: 20%">
					<col style="width: 80%">
					<thead>
						<tr>
							<td colspan="2">SIGN-IN</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>Descripci&oacute;n</th>
							<td>Login en la aplicaci&oacute;n de usuarios registrados</td>
						</tr>
						<tr>
							<th>URL</th>
							<td>http://12-dot-com-silicon-letseat.appspot.com/rest/<strong>singin</strong></td>
						</tr>
						<tr>
							<th>Request (datos de ejemplo)</th>
							<td>{"request": {"name": "carlos","pass": "carlos"}}</td>
						</tr>
						<tr>
							<th>Response (con &eacute;xito)</th>
							<td>{"code":"SI_OK","desc":"Usuario Autenticado
								Correctamente."}</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="row">
				<table class="table table-bordered table-condensed table-striped">
					<col style="width: 20%">
					<col style="width: 80%">
					<thead>
						<tr>
							<td colspan="2">GET PASS</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>Descripci&oacute;n</th>
							<td>Recuperaci&oacute;n de nombre de usuario y
								contrase&ntilde;a de un usuario registrado</td>
						</tr>
						<tr>
							<th>URL</th>
							<td>http://12-dot-com-silicon-letseat.appspot.com/rest/<strong>getpass</strong></td>
						</tr>
						<tr>
							<th>Request (datos de ejemplo)</th>
							<td>{"request":{"email":"carlosrodriguezlopez82@gmail.com"}}</td>
						</tr>
						<tr>
							<th>Response (con &eacute;xito)</th>
							<td>{"code":"GP_OK","desc":"Esa direcci&oacute;n de email se
								encuentra registrada en nuestro sistema. Le hemos enviado un
								email a esa cuenta para recuperar su contrase&ntilde;a."}</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="row">
				<table class="table table-bordered table-condensed table-striped">
					<col style="width: 20%">
					<col style="width: 80%">
					<thead>
						<tr>
							<td colspan="2">GET RESTAURANTS</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>Descripci&oacute;n</th>
							<td>Recuperaci&oacute;n de listado de restaurantes dados de alta en el sistema</td>
						</tr>
						<tr>
							<th>URL</th>
							<td>http://12-dot-com-silicon-letseat.appspot.com/rest/<strong>getrestaurants</strong></td>
						</tr>
						<tr>
							<th>Request (datos de ejemplo)</th>
							<td>(no requiere body)</td>
						</tr>
						<tr>
							<th>Response (con &eacute;xito)</th>
							<td>[<br>
							{"id":1,"isActived":0,"name":"Casa Paco","type":"Espanol","url":"","desc":"Pedir la cola de toro","m_t_open":"08:00","m_t_close":"15:00","t_t_open":"16:00","t_t_close":"20:30",<br>"avg_price":55,"score":2.5,"totalTables":18,"bookTables":10,"lat":37.36,"lon":-5.978,"prov":"","telf":"954151515"},<br>
							{"id":2,"isActived":0,"name":"Japo Guapo","type":"Japones","url":"","desc":"El sushi de gamba seca superrecomendado","m_t_open":"10:00","m_t_close":"14:30","t_t_open":"15:00","t_t_close":"22:00",<br>"avg_price":70,"score":2.5,"totalTables":56,"bookTables":3,"lat":37.36,"lon":-5.977,"prov":"","telf":"954155515"}<br>
							{...},...<br>
							]</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="row">
				<table class="table table-bordered table-condensed table-striped">
					<col style="width: 20%">
					<col style="width: 80%">
					<thead>
						<tr>
							<td colspan="2">BOOK RESTAURANT</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>Descripci&oacute;n</th>
							<td>Reserva de Numero de Meses es un restaurante en base a su id</td>
						</tr>
						<tr>
							<th>URL</th>
							<td>http://12-dot-com-silicon-letseat.appspot.com/rest/<strong>bookrestaurant</strong></td>
						</tr>
						<tr>
							<th>Request (datos de ejemplo)</th>
							<td>{"request":{"id":2, "Ntables2Book":3}}</td>
						</tr>
						<tr>
							<th>Response (con &eacute;xito)</th>
							<td>{"code":"BR_OK","desc":"Restaurante Reservado Correctamente"}</td>
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
