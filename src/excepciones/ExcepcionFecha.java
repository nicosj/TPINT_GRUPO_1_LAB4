package excepciones;

public class ExcepcionFecha extends RuntimeException  {
		
		public ExcepcionFecha() {
		}

		@Override
		public String getMessage() {
			return "El a�o elegido es inconsistente.";
		}
}
