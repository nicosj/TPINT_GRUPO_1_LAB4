package dominio;

import java.sql.Date;

public class Movimientos {

  private int idMovimiento;
  private String numero_Cuenta;
  private Date fechaMovimiento;
  private String detalleConcepto;
  private double importeMovimiento;
  private String tipoMovimiento;
  
  
  
	  public Movimientos() {};
	  
	public Movimientos(int idMovimiento, String numero_Cuenta, Date fechaMovimiento, String detalleConcepto,
			double importeMovimiento, String tipoMovimiento) {
		super();
		this.idMovimiento = idMovimiento;
		this.numero_Cuenta = numero_Cuenta;
		this.fechaMovimiento = fechaMovimiento;
		this.detalleConcepto = detalleConcepto;
		this.importeMovimiento = importeMovimiento;
		this.tipoMovimiento = tipoMovimiento;
	}
	public int getIdMovimiento() {
		return idMovimiento;
	}
	public void setIdMovimiento(int idMovimiento) {
		this.idMovimiento = idMovimiento;
	}
	public String getNumero_Cuenta() {
		return numero_Cuenta;
	}
	public void setNumero_Cuenta(String numero_Cuenta) {
		this.numero_Cuenta = numero_Cuenta;
	}
	public Date getFechaMovimiento() {
		return fechaMovimiento;
	}
	public void setFechaMovimiento(Date fechaMovimiento) {
		this.fechaMovimiento = fechaMovimiento;
	}
	public String getDetalleConcepto() {
		return detalleConcepto;
	}
	public void setDetalleConcepto(String detalleConcepto) {
		this.detalleConcepto = detalleConcepto;
	}
	public double getImporteMovimiento() {
		return importeMovimiento;
	}
	public void setImporteMovimiento(double importeMovimiento) {
		this.importeMovimiento = importeMovimiento;
	}
	public String getTipoMovimiento() {
		return tipoMovimiento;
	}
	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}
}