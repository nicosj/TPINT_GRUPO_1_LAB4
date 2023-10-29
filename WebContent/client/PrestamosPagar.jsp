<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<title>Prestamos a pagar</title>
</head>
<body>

	<div class="container mt-4">
		<h1>Préstamos Pendientes a Pagar</h1>

		<div class="row">
			<div class="col-md-4">
				<div class="card mb-3">
					<div class="card-body">
						<h5 class="card-title">N° de Préstamo: 1234</h5>
						<p class="card-text">Próxima Cuota a Pagar: Cuota n°5</p>
						<p class="card-text">Monto: $2000</p>
						<button type="button" class="btn btn-primary"
							data-bs-toggle="modal" data-bs-target="#modal1234">
							Pagar</button>
					</div>
				</div>
			</div>

			<div class="col-md-4">
				<div class="card mb-3">
					<div class="card-body">
						<h5 class="card-title">N° de Préstamo: 5678</h5>
						<p class="card-text">Próxima Cuota a Pagar: Cuota n°3</p>
						<p class="card-text">Monto: $1500</p>
						<button type="button" class="btn btn-primary"
							data-bs-toggle="modal" data-bs-target="#modal1234">
							Pagar</button>
					</div>
				</div>
			</div>

			<div class="col-md-4">
				<div class="card mb-3">
					<div class="card-body">
						<h5 class="card-title">N° de Préstamo: 9012</h5>
						<p class="card-text">Próxima Cuota a Pagar: Cuota n°7</p>
						<p class="card-text">Monto: $3000</p>
						<button type="button" class="btn btn-primary"
							data-bs-toggle="modal" data-bs-target="#modal1234">
							Pagar</button>
					</div>
				</div>
			</div>
		</div>

	</div>


	<!-- Se repite la estructura para los otros préstamos -->
	<!-- Aquí se agregan los otros bloques de tarjetas -->

	<!-- Modal -->
	<div class="modal fade" id="modal1234" tabindex="-1"
		aria-labelledby="modal1234Label" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modal1234Label">Pagar Préstamo</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<h5>N° de Préstamo: 1234</h5>
					<p>Próxima Cuota a Pagar: Cuota n°1</p>
					<p>Monto: $2000</p>

					<label for="cuotas">Selecciona la Cuota:</label> <select
						class="form-select" id="cuotas">
						<option value="cuota1">Cuota n°1 $2000</option>
						<option value="cuota2">Cuota n°2 $2000</option>
						<!-- ... Agregar las otras opciones de cuotas -->
					</select> <label for="cuentas">Selecciona la Cuenta de Pago:</label> <select
						class="form-select" id="cuentas">
						<option value="cuenta1">Cuenta n°1 Saldo $30000</option>
						<!-- ... Agregar las otras opciones de cuentas -->
					</select>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Cerrar</button>
					<button type="button" class="btn btn-primary">Pagar</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Fin del modal -->

	<!-- Se repite la estructura para los otros modales -->
	</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>