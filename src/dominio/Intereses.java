package dominio;

public class Intereses {
	
	private int idInteres;
	private int cuotas;
	private float porcentaje;
	
	public Intereses() {
		
	}

	public Intereses(int intereses, int cuotas, float porcentaje) {
		super();
		this.idInteres = intereses;
		this.cuotas = cuotas;
		this.porcentaje = porcentaje;
	}

	public int getidInteres() {
		return idInteres;
	}

	public void setidInteres(int intereses) {
		this.idInteres = intereses;
	}

	public int getCuotas() {
		return cuotas;
	}

	public void setCuotas(int cuotas) {
		this.cuotas = cuotas;
	}

	public float getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(float porcentaje) {
		this.porcentaje = porcentaje;
	}
	
	
}
