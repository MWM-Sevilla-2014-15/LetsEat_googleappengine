<%@ page language="java" contentType="text/html;	charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Registro Resturantes</title>
<%@include file="include/init.jsp"%>
</head>
<body onload="initRegistroRest();">
	<div class="container">
		<header>
			<div class="row">
				<div class="page-header">
					<h1>Let's Eat | Registro Restaurantes</h1>
				</div>
			</div>
		</header>
		<nav>
			<div class="row">
				<ol class="breadcrumb">
					<li><a href="/index.jsp">Home</a></li>
					<li class="active">Registro Restaurantes</li>
				</ol>
			</div>
		</nav>
	</div>
	<section>
		<div class="container-fluid">
			<div class="row-fluid">
				<div id="map-canvas"></div>
			</div>
		</div>
	</section>
	<section>
		<div class="container">
			<div class="row"></div>
			<div class="row"></div>
			<div class="row">
				<div class="col-md-7">
					<h3>
						<span class="badge">1</span> Georreferenciaci&oacute;n
					</h3>
				</div>
				<div class="col-md-5 padded well">
					<div class="btn-group pull-right" role="group" aria-label="...">
						<div class="btn-group" role="group">
							<button type="button" class="btn btn-default"
								onclick="removeRestaurant_Marker();">
								<i class="fa fa-minus-square fa-fw"></i>&nbsp; Restaurante
							</button>
						</div>
						<div class="btn-group" role="group">
							<button type="button" class="btn btn-primary"
								onclick="addRestaurant_Marker();">
								<i class="fa fa-plus-square fa-fw"></i>&nbsp; Restaurante
							</button>
						</div>
					</div>
					<button style="margin-right: 2%" type="button"
						class="btn btn-default pull-right" onclick="getMyLocation();">
						<i class="fa fa-user fa-fw"></i>&nbsp; Mi Localizaci&oacute;n
					</button>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<h3>
						<span class="badge">2</span> Informaci&oacute;n Asociada
					</h3>
				</div>
			</div>
			<div class="row">
				<form role="form" id="formRestaurant" method="post"
					action="createrestaurant">
					<div class="row">
						<div class="form-group col-md-6">
							<label for="name">Nombre:</label> <input type="text"
								class="form-control" id="name" name="name" required>
							<label for="desc">Telf:</label>
							<div class="input-group">
									<span class="input-group-addon"> <i
										class="fa fa-phone fa-fw"></i></span> <input type="text"
								class="form-control" id="telf" name="telf" required>
							</div>
						</div>
						<div>
							<div class="form-group col-md-3">
								<label>Horario de Ma&ntilde;ana:</label>
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="fa fa-clock-o fa-fw"></i></span> <input type="text"
										class="form-control time" id="m_t_open" name="m_t_open"
										data-time-format="H:i" required placeholder="Desde">
								</div>
								<div class="input-group padded">
									<span class="input-group-addon"> <i
										class="fa fa-clock-o fa-fw"></i></span> <input type="text"
										class="form-control time" id="m_t_close" name="m_t_close"
										data-time-format="H:i" required placeholder="Hasta">
								</div>
							</div>
						</div>
						<div>
							<div class="form-group col-md-3">
								<label>Horario de Tarde:</label>
								<div class="input-group">
									<span class="input-group-addon"> <i
										class="fa fa-clock-o fa-fw"></i></span> <input type="text"
										class="form-control time" id="t_t_open" name="t_t_open"
										data-time-format="H:i" required placeholder="Desde">
								</div>
								<div class="input-group padded">
									<span class="input-group-addon"> <i
										class="fa fa-clock-o fa-fw"></i></span> <input type="text"
										class="form-control time" id="t_t_close" name="t_t_close"
										data-time-format="H:i" required placeholder="Hasta">
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-12">
							<label for="desc">Descripci&oacute;n:</label>
							<textarea maxlength="500" type="text" class="form-control"
								id="desc" name="desc" required></textarea>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-3">
							<label for="price">Precio Medio por persona:</label> <input
								type="text" class="form-control" id="avg_price" name="avg_price"
								required />
						</div>
						<div class="form-group col-md-3">
							<label for="discount">Descuento:</label> <input
								type="text" class="form-control" id="discount" name="discount"
								required />
						</div>
						<div class="form-group col-md-3">
							<label for="score">Puntuaci&oacute;n:</label> <input type="text"
								class="form-control" id="score" name="score" required />
						</div>
						<div class="form-group col-md-2">
							<label for="typeSelector">Tipo:</label> <select
								class="form-control" id="typeSelector" name="typeSelector">
								<option value="Espanol">Espanol</option>
								<option value="Italiano">Italiano</option>
								<option value="Chino">Chino</option>
								<option value="Japones">Japones</option>
								<option value="Mexicano">Mexicano</option>
							</select>
						</div>
						<div class="form-group col-md-1">
							<label for="nTables">Num. de Mesas:</label> <input type="number"
								class="form-control" id="nTables" min="1" max="100" value="1"
								name="nTables" required>
						</div>
						<!-- Coordenadas del Restaurante -->
						<input type="hidden" id="lat" name="lat"> <input
							type="hidden" id="lon" name="lon">
					</div>
					<div class="row padded">
						<div class="btn-group col-md-2 pull-right" role="group"
							aria-label="...">
							<div class="btn-group" role="group">
								<button type="reset" class="btn btn-default btn-block">Cancelar</button>
							</div>
							<div class="btn-group" role="group">
								<button type="submit" class="btn btn-primary btn-block">Guardar</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</section>
	<footer>
		<div class="container">
			<%@include file="include/footer.jsp"%>
		</div>
	</footer>
</body>
</html>