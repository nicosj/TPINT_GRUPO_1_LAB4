package dominio;

public class Usuario {
	private int IdUsuario;
	private String usuario;
	private String Clave;
	private int tipoUsuario;

	Cliente cliente;


	public Usuario() {
	}
	public Usuario(int idUsuario, String usuario, String Clave, int tipoUsuario, Cliente cliente){
		this.IdUsuario = idUsuario;
		this.usuario = usuario;
		this.Clave = Clave;
		this.tipoUsuario = tipoUsuario;
		this.cliente = cliente;
	}
	public Usuario(String usuario, String Clave) {
		this.usuario = usuario;
		this.Clave = Clave;
	}

	public int getIdUsuario() {
		return IdUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		IdUsuario = idUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return Clave;
	}

	public void setClave(String clave) {
		Clave = clave;
	}

	public int getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public Cliente getCliente() {

        return cliente;
    }
	public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


}
