package dominio;
import java.util.Random;

public class Cuenta {
	
	private String numero_Cuenta;
	private String idCliente;
	private String tipo_Cuenta;
	private String fecha_Creacion;
	private String CBU;
	private double saldo;
	private boolean estado; 
	//Creacion de Cuenta
	public Cuenta(String tipo_Cuenta, String fecha_Creacion, String CBU,
			double saldo, boolean estado) {
		super();


		this.tipo_Cuenta = tipo_Cuenta;
		this.fecha_Creacion = fecha_Creacion;
		this.CBU = CBU;
		this.saldo = saldo;
		this.estado = estado;
	}
	//Consulta de cuenta
	public Cuenta( String idCliente, String numero_Cuenta, String tipo_Cuenta, String fecha_Creacion, String CBU,
			double saldo, boolean estado) {
		super();
		this.numero_Cuenta = numero_Cuenta;
		this.idCliente = idCliente;
		this.tipo_Cuenta = tipo_Cuenta;
		this.fecha_Creacion = fecha_Creacion;
		this.CBU = CBU;
		this.saldo = saldo;
		this.estado = estado;
	}
	
	 public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public static String generarCBU() {
	        // Longitud total del CBU

	        
	        // Crear un StringBuilder para construir el CBU
	        StringBuilder cbuBuilder = new StringBuilder();

	        // Agregar los primeros 10 dígitos iguales a 0
	        for (int i = 0; i < 10; i++) {
	            cbuBuilder.append("0");
	        }

	        // Generar aleatoriamente los 12 dígitos restantes
	        Random random = new Random();
	        for (int i = 0; i < 12; i++) {
	            int randomDigit = random.nextInt(10);
	            cbuBuilder.append(randomDigit);
	        }

	        // Convertir el StringBuilder a una cadena de texto
	        String cbu = cbuBuilder.toString();

	        return cbu;
	    }
	public static String generarCuenta() {
        // Longitud total del CBU

        
        // Crear un StringBuilder para construir el CBU
        StringBuilder cbuBuilder = new StringBuilder();

        // Agregar los primeros 10 dígitos iguales a 0
        
        // Generar aleatoriamente los 12 dígitos restantes
        Random random = new Random();
        for (int i = 0; i < 12; i++) {
            int randomDigit = random.nextInt(10);
            cbuBuilder.append(randomDigit);
        }

        // Convertir el StringBuilder a una cadena de texto
        String cuenta= cbuBuilder.toString();

        return cuenta;
    }
	
	
	
	@Override
	public String toString() {
		return "Cuenta [numero_Cuenta=" + numero_Cuenta + ", idCliente=" + idCliente + ", tipo_Cuenta=" + tipo_Cuenta
				+ ", fecha_Creacion=" + fecha_Creacion + ", CBU=" + CBU + ", saldo=" + saldo + "]";
	}

	public String getNumero_Cuenta() {
		return numero_Cuenta;
	}

	public void setNumero_Cuenta(String numero_Cuenta) {
		this.numero_Cuenta = numero_Cuenta;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String string) {
		this.idCliente = string;
	}

	public String getTipo_Cuenta() {
		return tipo_Cuenta;
	}

	public void setTipo_Cuenta(String tipo_Cuenta) {
		this.tipo_Cuenta = tipo_Cuenta;
	}

	public String getFecha_Creacion() {
		return fecha_Creacion;
	}

	public void setFecha_Creacion(String string) {
		this.fecha_Creacion = string;
	}

	public String getCBU() {
		return CBU;
	}

	public void setCBU(String cBU) {
		CBU = cBU;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	
	public Cuenta () {};
	
	

}
