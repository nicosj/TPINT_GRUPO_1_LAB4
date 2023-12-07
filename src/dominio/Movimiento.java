package dominio;

import java.sql.Date;

public class Movimiento {

	private String idMovimiento;
	private Cuenta cuenta; 
	private String fechaMovimiento;
	private String detalleConcepto;
	private double importeMovimiento;
	private String tipoMovimiento;
	
	

  @Override
	public String toString() {
		return "Movimiento [idMovimiento=" + idMovimiento + ", numero_Cuenta=" + cuenta.getNumero_Cuenta() + ", fechaMovimiento="
				+ fechaMovimiento + ", detalleConcepto=" + detalleConcepto + ", importeMovimiento=" + importeMovimiento
				+ ", tipoMovimiento=" + tipoMovimiento + "]";
	}
  
	  public Movimiento() {};
	  
	public Movimiento(String idMovimiento, Cuenta cuenta, String fechaMovimiento, String detalleConcepto,
			double importeMovimiento, String tipoMovimiento) {
		super();
		this.idMovimiento = idMovimiento;
		this.cuenta = cuenta;
		this.fechaMovimiento = fechaMovimiento;
		this.detalleConcepto = detalleConcepto;
		this.importeMovimiento = importeMovimiento;
		this.tipoMovimiento = tipoMovimiento;
	}
	public String getIdMovimiento() {
		return idMovimiento;
	}
	public void setIdMovimiento(String idMovimiento) {
		this.idMovimiento = idMovimiento;
	}
    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

	public String getFechaMovimiento() {
		return fechaMovimiento;
	}
	public void setFechaMovimiento(String fechaMovimiento) {
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
