package dominio;

import java.sql.Date;

public class PagoPrestamo {

    private int idPago;
    private String numero_Cuenta;
    private String Fecha_Pago;
    private double importe_cuota;
    private double importe_restante;
    private int cuotas_restantes;
    private int idPrestamo;

    public PagoPrestamo() {
    }

    public PagoPrestamo(int idPago, String numero_Cuenta, String Fecha_Pago, Double importe_cuota, double importe_restante,
                        int cuotas_restantes, int idPrestamo) {
        this.idPago = idPago;
        this.numero_Cuenta = numero_Cuenta;
        this.Fecha_Pago = Fecha_Pago;
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

    public String getFecha_Pago() {
        return Fecha_Pago;
    }

    public void setFecha_Pago(String Fecha_Pago) {
        this.Fecha_Pago = Fecha_Pago;
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
    }

    public Double getImporte_cuota() {
        return importe_cuota;
    }

    public void setImporte_cuota(Double importe_cuota) {
        this.importe_cuota = importe_cuota;
    }

    //metodo to String
    @Override
    public String toString() {
        return "PagoPrestamo{" + "idPago=" + idPago + ", numero_Cuenta=" + numero_Cuenta + ", Fecha_Pago=" + Fecha_Pago + ", importe_cuota=" + importe_cuota + ", importe_restante=" + importe_restante + ", cuotas_restantes=" + cuotas_restantes + ", idPrestamo=" + idPrestamo + '}';
    }

}


