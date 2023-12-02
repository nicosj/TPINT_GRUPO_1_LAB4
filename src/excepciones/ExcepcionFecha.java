package excepciones;

public class ExcepcionFecha extends RuntimeException  {
		
		public ExcepcionFecha() {
		}

		@Override
		public String getMessage() {
			return "El año elegido es inconsistente.";
		}
}
