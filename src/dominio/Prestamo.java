package dominio;

import java.sql.Date;

public class Prestamo {
	
	private int idPrestamo;
	private String numero_Cuenta;
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
	public String getNumero_Cuenta() {
		return numero_Cuenta;
	}
	public void setNumero_Cuenta(String numero_Cuenta) {
		this.numero_Cuenta = numero_Cuenta;
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
	
	public Prestamo(int idPrestamo, String numero_Cuenta, String fechaPedido, double importeCuota,double totalImporte, int cuotas,
			int estado) {
		super();
		this.idPrestamo = idPrestamo;
		this.numero_Cuenta = numero_Cuenta;
		this.fechaPedido = fechaPedido;
		this.totalImporte = totalImporte;
		this.importeCuota = importeCuota;
		this.cuotas = cuotas;
		this.estado = estado;
		
	}

	@Override
	public String toString() {
		return "Prestamo [idPrestamo=" + idPrestamo + ", numero_Cuenta=" + numero_Cuenta + ", fechaPedido="
				+ fechaPedido + ", importeCuota=" + importeCuota + ", totalImporte=" + totalImporte + ", cuotas="
				+ cuotas + ", estado=" + estado + "]";
	}
	
	
}
