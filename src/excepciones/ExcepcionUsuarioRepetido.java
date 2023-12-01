package excepciones;

public class ExcepcionUsuarioRepetido extends RuntimeException  {
		
		public ExcepcionUsuarioRepetido() {
		}

		@Override
		public String getMessage() {
			return "El Usuario ingresado ya existe";
		}
}
