package dominio;

import java.sql.Date;

public class Prestamo {
	
	private int idPrestamo;
	private Cuenta cuenta;
	private String fechaPedido;
	private double importeCuota;
	private double totalImporte;
	private int cuotas;
	private int estado=0;
	


	public Prestamo() { };
	
	
	public int getCuotas() {
		return cuotas;
	}



	public void setCuotas(int cuotas) {
		this.cuotas = cuotas;
	}
	
	
	public double getImporteCuota() {
		return importeCuota;
	}


	public void setImporteCuota(double importeCuota) {
		this.importeCuota = importeCuota;
	}
	
	public int getEstado() {
		return estado;
	}


	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getIdPrestamo() {
		return idPrestamo;
	}
	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}
	public Cuenta getCuenta() {
		return cuenta;
	}
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	public String getFechaPedido() {
		return fechaPedido;
	}
	public void setFechaPedido(String fechaPedido) {
		this.fechaPedido = fechaPedido;
	}
	public double getTotalImporte() {
		return totalImporte;
	}
	public void setTotalImporte(double totalImporte) {
		this.totalImporte = totalImporte;
	}
	
	public Prestamo(int idPrestamo, Cuenta cuenta, String fechaPedido, double importeCuota,double totalImporte, int cuotas,
			int estado) {
		super();
		this.idPrestamo = idPrestamo;
		this.cuenta = cuenta;
		this.fechaPedido = fechaPedido;
		this.totalImporte = totalImporte;
		this.importeCuota = importeCuota;
		this.cuotas = cuotas;
		this.estado = estado;
		
	}

	@Override
	public String toString() {
		return "Prestamo [idPrestamo=" + idPrestamo + ", numero_Cuenta=" + cuenta.getNumero_Cuenta() + ", fechaPedido="
				+ fechaPedido + ", importeCuota=" + importeCuota + ", totalImporte=" + totalImporte + ", cuotas="
				+ cuotas + ", estado=" + estado + "]";
	}
	
	
}
