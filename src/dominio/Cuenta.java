package dominio;

import java.sql.Date;
import java.text.DecimalFormat;

public class Cuenta {
	
	String numero_Cuenta;
	int idCliente;
	String tipo_Cuenta;
	Date fecha_Creacion;
	String CBU;
	double saldo;
	
	public Cuenta(String numero_Cuenta, int idCliente, String tipo_Cuenta, Date fecha_Creacion, String CBU,
			double saldo) {
		super();
		this.numero_Cuenta = numero_Cuenta;
		this.idCliente = idCliente;
		this.tipo_Cuenta = tipo_Cuenta;
		this.fecha_Creacion = fecha_Creacion;
		this.CBU = CBU;
		this.saldo = saldo;
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

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getTipo_Cuenta() {
		return tipo_Cuenta;
	}

	public void setTipo_Cuenta(String tipo_Cuenta) {
		this.tipo_Cuenta = tipo_Cuenta;
	}

	public Date getFecha_Creacion() {
		return fecha_Creacion;
	}

	public void setFecha_Creacion(Date fecha_Creacion) {
		this.fecha_Creacion = fecha_Creacion;
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
