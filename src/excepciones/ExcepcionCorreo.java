package excepciones;

public class ExcepcionCorreo extends RuntimeException {
	
	public ExcepcionCorreo() {
		
	}
	@Override
	public String getMessage() {
		return "El Correo Electronico ingresado ya existe. No puede repetirse";
	}
}
