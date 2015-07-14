<%@ page language="java" contentType="text/html;	charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Registro Resturantes</title>
<%@include file="include/init.jsp"%>
</head>
<body onload="loadMap();">
	<div class="container">
		<div class="page-header">
			<h1>Registro de Restaurantes</h1>
		</div>
	</div>
	<div id="mapContainer" class="span12">
		<div class="row-fluid">
			<div class="container">
				<div id="map-canvas"></div>
			</div>
			<div class="container">
				<div class="btn-group btn-group-justified padded" role="group"
					aria-label="...">
					<div class="btn-group" role="group">
						<button type="button" class="btn btn-default">Ver mi Localizaci&oacute;n</button>
					</div>
					<div class="btn-group" role="group">
						<button type="button" class="btn btn-default">A&ntilde;adir Restaurante</button>
						<!-- Despliegua lista de atributos -->
					</div>
					<div class="btn-group" role="group">
						<button type="button" class="btn btn-default">Cancelar</button>
					</div>
					<div class="btn-group" role="group">
						<button type="button" class="btn btn-primary">Guardar</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<form role="form" action="xxx" method="post">
			<div class="form-group">
				<label for="name">Nombre:</label> <input type="text"
					class="form-control" id="name" name="name" required>
			</div>
			<div class="form-group">
				<label for="desc">Descripci&oacute;n:</label>
				<textarea type="text" class="form-control" id="desc" name="desc"
					required></textarea>
			</div>
			<div class="form-group">
				<label>Horario:</label> <input type="time" class="form-control"
					id="time_open" name="time_open" required> <input
					type="time" class="form-control" id="time_close" name="time_close"
					required>
			</div>
			<div class="form-group">
				<label for="price">Precio Medio:</label> <input type="number"
					class="form-control" id="price" name="price" required>
			</div>
			<div class="form-group">
				<label for="score">Puntuaci&oacute;n:</label> <input type="number"
					class="form-control" id="score" name="score" required>
			</div>
			<div class="form-group">
				<label for="nTables">Nº de Mesas:</label> <input type="number"
					class="form-control" id="nTables" name="nTables" required>
			</div>
			<div class="form-group">
				<label for="desc">Telef:</label> <input type="text"
					class="form-control" id="score" name="telf" required>
			</div>
			<div class="form-group">
				<label for="prov">Provinc:</label> <input type="text"
					class="form-control" id="v" name="prov" required>
			</div>
			<button type="submit" class="btn btn-default">Submit</button>
		</form>
	</div>
</body>
</html>