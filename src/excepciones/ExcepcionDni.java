package excepciones;

public class ExcepcionDni extends RuntimeException {
	
	public ExcepcionDni() {
		
	}
	
	public String getMessage() {
		return "El DNI ingresado ya existe";
	}
}
