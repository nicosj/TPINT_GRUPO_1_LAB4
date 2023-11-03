package dominio;

import java.sql.Date;

public class PagoPrestamo {
	  
	  private int idPago;
	  private String numero_Cuenta;
	  private Date importe_cuota;
	  private double importe_restante;
	  private int cuotas_restantes;
	  private int idPrestamo;	  

	  public PagoPrestamo() {}

	  public PagoPrestamo(int idPago, String numero_Cuenta, Date importe_cuota, double importe_restante,
				int cuotas_restantes, int idPrestamo) {
			super();
			this.idPago = idPago;
			this.numero_Cuenta = numero_Cuenta;
			this.importe_cuota = importe_cuota;
			this.importe_restante = importe_restante;
			this.cuotas_restantes = cuotas_restantes;
			this.idPrestamo = idPrestamo;
		}

	public int getIdPago() {
		return idPago;
	}

	public void setIdPago(int idPago) {
		this.idPago = idPago;
	}

	public String getNumero_Cuenta() {
		return numero_Cuenta;
	}

	public void setNumero_Cuenta(String numero_Cuenta) {
		this.numero_Cuenta = numero_Cuenta;
	}

	public Date getImporte_cuota() {
		return importe_cuota;
	}

	public void setImporte_cuota(Date importe_cuota) {
		this.importe_cuota = importe_cuota;
	}

	public double getImporte_restante() {
		return importe_restante;
	}

	public void setImporte_restante(double importe_restante) {
		this.importe_restante = importe_restante;
	}

	public int getCuotas_restantes() {
		return cuotas_restantes;
	}

	public void setCuotas_restantes(int cuotas_restantes) {
		this.cuotas_restantes = cuotas_restantes;
	}

	public int getIdPrestamo() {
		return idPrestamo;
	}

	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	};
	  
	  
}


