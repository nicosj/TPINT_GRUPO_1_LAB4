package dominio;

public class Cliente {

	private int idCliente;
    private String dni;
    private String cuil;
    private String nombre;
    private String apellido;
    private String sexo;
    private String nacionalidad;
    private String fechaNacimiento;
    private String direccion;
    private String localidad;
    private String provincia;
    private String correo;
    private String telefono;
    private boolean estado;




	public Cliente() {

    }

    //Este constructor es para traer un cliente de mysql
    public Cliente( int id, String dni, String cuil, String nombre, String apellido, String sexo, String nacionalidad, String fechaNacimiento, String direccion, String localidad, String provincia, String correo, String telefono, boolean estado) {
    	this.idCliente = id;
    	this.dni = dni;
        this.cuil = cuil;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.nacionalidad = nacionalidad;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.localidad = localidad;
        this.provincia = provincia;
        this.correo = correo;
        this.telefono = telefono;
        this.estado = estado;
    }
    //Este constructor es para crear un nuevo Cliente, se diferencian en recibir como parametro idCliente


    public boolean isEstado() {
    	return estado;
    }
    
    
    public void setEstado(boolean estado) {
    	this.estado = estado;
    }
    
    
    public int getIdCLiente() {
    	return idCliente;
    }
    public void setIdCLiente(int idCliente) {
        this.idCliente = idCliente;
    }



	public String getDNI() {
        return dni;
    }


    public void setDNI(String dni) {
        this.dni = dni;
    }

    public String getCUIL() {
        return cuil;
    }

    public void setCUIL(String cuil) {
        this.cuil = cuil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", dni='" + dni + '\'' +
                ", cuil='" + cuil + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", sexo='" + sexo + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", direccion='" + direccion + '\'' +
                ", localidad='" + localidad + '\'' +
                ", provincia='" + provincia + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
