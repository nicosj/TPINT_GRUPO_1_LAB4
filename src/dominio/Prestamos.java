package dominio;

import java.sql.Date;

public class Prestamos {
	
	private int idPrestamo;
	private String numero_Cuenta;
	private Date fechaPedido;
	private double totalImporte;
	private int cuota;
	private int idIntereses;
	
	public Prestamos() { };
	
	
	public int getIdPrestamo() {
		return idPrestamo;
	}
	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}
	public String getNumero_Cuenta() {
		return numero_Cuenta;
	}
	public void setNumero_Cuenta(String numero_Cuenta) {
		this.numero_Cuenta = numero_Cuenta;
	}
	public Date getFechaPedido() {
		return fechaPedido;
	}
	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}
	public double getTotalImporte() {
		return totalImporte;
	}
	public void setTotalImporte(double totalImporte) {
		this.totalImporte = totalImporte;
	}
	public int getCuota() {
		return cuota;
	}
	public void setCuota(int cuota) {
		this.cuota = cuota;
	}
	public int getIdIntereses() {
		return idIntereses;
	}
	public void setIdIntereses(int idIntereses) {
		this.idIntereses = idIntereses;
	}
	
	public Prestamos(int idPrestamo, String numero_Cuenta, Date fechaPedido, double totalImporte, int cuota,
			int idIntereses) {
		super();
		this.idPrestamo = idPrestamo;
		this.numero_Cuenta = numero_Cuenta;
		this.fechaPedido = fechaPedido;
		this.totalImporte = totalImporte;
		this.cuota = cuota;
		this.idIntereses = idIntereses;
	}
	
	
}
