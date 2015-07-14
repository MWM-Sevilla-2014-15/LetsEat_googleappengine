<%@ page language="java" contentType="text/html;	charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Let's Eat Service Api</title>
<%@include file="include/init.jsp"%>
</head>
<body>
	<div class="container">
		<div class="page-header">
			<h1>Let's Eat Service API</h1>
		</div>
	</div>
	<div class="container">
		<form class="form-inline right padded" action="adminLogin" method="post" role="form">
			<div class="form-group">
				<label for="titulo">User:</label> <input type="text"
					class="form-control" id="name" name="name" required>
			</div>
			<div class="form-group">
				<label for="comment">Pass:</label> <input type="password"
					class="form-control" id="pass" name="pass" required>
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
	<div class="container">
		<table class="table table-bordered table-condensed table-striped">
			<tr>
				<th>Nombre</th>
				<td>Sing up</td>
			</tr>
			<tr>
				<th>Descripci&oacute;n</th>
				<td>Registro en la aplicaci&oacute;n</td>
			</tr>
			<tr>
				<th>URL</th>
				<td>http://12-dot-com-silicon-letseat.appspot.com/<strong>singup</strong></td>
			</tr>
			<tr>
				<th>Request(ejemplo)</th>
				<td>{"request":"{\"email\":\"marisa@gmail.com\",\"name\":\"marisa\",\"pass\":\"marisa\"}"}</td>
			</tr>
			<tr>
				<th>Response con &eacute;xito</th>
				<td>{"code":"SU_OK","desc":"Usuario Registrado Correctamente"}</td>
			</tr>
		</table>
	</div>
	<div class="container">
		<table class="table table-bordered table-condensed table-striped">
			<tr>
				<th>Nombre</th>
				<td>Sing in</td>
			</tr>
			<tr>
				<th>Descripci&oacute;n</th>
				<td>Login en la aplicaci&oacute;n de usuarios registrados</td>
			</tr>
			<tr>
				<th>URL</th>
				<td>http://12-dot-com-silicon-letseat.appspot.com/<strong>singin</strong></td>
			</tr>
			<tr>
				<th>Request(ejemplo)</th>
				<td>{"request":"{\"name\":\"carlos\", \"pass\":\"carlos\"}"}</td>
			</tr>
			<tr>
				<th>Response con &eacute;xito</th>
				<td>{"code":"SI_OK","desc":"Usuario Autenticado
					Correctamente."}</td>
			</tr>
		</table>
	</div>
	<div class="container">
		<table class="table table-bordered table-condensed table-striped">
			<tr>
				<th>Nombre</th>
				<td>GetPass</td>
			</tr>
			<tr>
				<th>Descripci&oacute;n</th>
				<td>Recuperaci&oacute;n de nombre de usuario y
					contrase&ntilde;a de un usuario registrado</td>
			</tr>
			<tr>
				<th>URL</th>
				<td>http://12-dot-com-silicon-letseat.appspot.com/<strong>getpass</strong></td>
			</tr>
			<tr>
				<th>Request(ejemplo)</th>
				<td>{"request":"{\"email\":\"carlosrodriguezlopez82@gmail.com\"}"}</td>
			</tr>
			<tr>
				<th>Response con &eacute;xito</th>
				<td>{"code":"GP_OK","desc":"Esa direcci&oacute;n de email se
					encuentra registrada en nuestro sistema. Le hemos enviado un email
					a esa cuenta para recuperar su contrase&ntilde;a."}</td>
			</tr>
		</table>
	</div>
</body>
</html>
