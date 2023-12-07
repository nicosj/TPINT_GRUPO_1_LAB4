package servlets;


import dominio.Cliente;
import dominio.Cuenta;


import java.io.IOException;
import java.sql.Date;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.xml.bind.ParseConversionEvent;

import Negocio_Implementacion.Cliente_NegocioImp;

//import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import Negocio_Implementacion.Cuenta_NegocioImp;


/**
 * Servlet implementation class EditCuentaServlet
 */
@WebServlet("/admin/EditCuentaServlet")
public class EditCuentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

    /**
     * Default constructor. 
     */
    public EditCuentaServlet() {
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Cuenta_NegocioImp cuentaNegocio = new Cuenta_NegocioImp();
	    ArrayList<Cuenta> cuentas = cuentaNegocio.listarCuentas();
	    Cliente_NegocioImp clienteNegocio = new Cliente_NegocioImp();
	    ArrayList<Cliente> clientes = clienteNegocio.listarClientes();
	    // Crear un mapa para asociar clientes por idCliente
	    Map<Integer, Cliente> clientesMap = new HashMap<>();
	    for (Cliente cliente : clientes) {
	        clientesMap.put(cliente.getIdCLiente(), cliente);
	    }

	    // Asociar clientes a cuentas mediante idCliente
	    for (Cuenta cuenta : cuentas) {
	        int idCliente = cuenta.getCliente().getIdCLiente();
	        Cliente clienteAsociado = clientesMap.get(idCliente);
	        cuenta.setCliente(clienteAsociado);
	    }

	    request.setAttribute("cuentas", cuentas);
	    request.getRequestDispatcher("/admin/ListadoCuentas.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		Cuenta cuenta = new Cuenta();
		String idCuenta = request.getParameter("numero_Cuenta");
		Cuenta_NegocioImp accNegocio = new Cuenta_NegocioImp();
		Cuenta cuentaAux= new Cuenta();
    	cuentaAux= accNegocio.obtenerCuenta(idCuenta);
    	boolean banderita= false;
		/*if(request.getParameter("btnTraerid")!=null) {
			try {
            	Cuenta cuenta2= new Cuenta();
            	Cuenta_NegocioImp cuentaN = new Cuenta_NegocioImp();
            	cuenta2= cuentaN.obtenerCuenta(idCuenta);
            	request.setAttribute("cuenta", cuenta2);
            	//request.getRequestDispatcher("/admin/ListadoCuentas.jsp").forward(request, response);
			} catch (Exception e) {
				
				// TODO: handle exception
			}
		}*/
		
	if(request.getParameter("btnEdit")!=null){
		Cliente cliente = new Cliente();
		cliente.setIdCLiente(Integer.parseInt(request.getParameter("idCliente")));
		cuenta.setCliente(cliente);
		cuenta.setNumero_Cuenta(request.getParameter("numero_Cuenta"));
		cuenta.setFecha_Creacion(request.getParameter("FechaCreacion"));
		cuenta.setTipo_Cuenta(request.getParameter("TipoCuenta"));
		cuenta.setCBU(request.getParameter("CBU"));
		cuenta.setSaldo(Double.parseDouble(request.getParameter("Saldo")));
		cuenta.setNumero_Cuenta(request.getParameter("numero_Cuenta"));
		String activo = request.getParameter("Estado");
		boolean estado = true;
		 if(activo.equalsIgnoreCase("activo")) {
			estado = true;
		} else {
			estado=false;
		}
		cuenta.setEstado(estado);
	//	cuenta.setEstado(Boolean.parseBoolean(request.getParameter("Estado")));
			
		

            System.out.println("Servlet");
            System.out.println(cuenta);
            
            int cuentaCount = accNegocio.getCuentaCountByClientId(request.getParameter("idCliente"));
            try{
            	System.out.println("cantidad cuentas "+accNegocio.getCuentaCountByClientId(request.getParameter("idCliente")));
            	System.out.println("idcliente original "+cuentaAux.getCliente().getIdCLiente());
            	System.out.println("nuevo id cliente "+Integer.parseInt(request.getParameter("idCliente")));
                if(cuentaAux.getCliente().getIdCLiente() == Integer.parseInt(request.getParameter("idCliente"))) {
                	
                	banderita= true;
                    boolean filas= accNegocio.update(cuenta);
                    request.setAttribute("filas", filas);
                    request.setAttribute("updateSuccess", true);   
                    
                    //Copia doGet para cargar tablas
                    Cuenta_NegocioImp cuentaNegocio = new Cuenta_NegocioImp();
            	    ArrayList<Cuenta> cuentas = cuentaNegocio.listarCuentas();
            	    Cliente_NegocioImp clienteNegocio = new Cliente_NegocioImp();
            	    ArrayList<Cliente> clientes = clienteNegocio.listarClientes();
            	    // Crear un mapa para asociar clientes por idCliente
            	    Map<Integer, Cliente> clientesMap = new HashMap<>();
            	    for (Cliente clienteget : clientes) {
            	        clientesMap.put(clienteget.getIdCLiente(), clienteget);
            	    }

            	    // Asociar clientes a cuentas mediante idCliente
            	    for (Cuenta cuentaget : cuentas) {
            	        int idCliente = cuentaget.getCliente().getIdCLiente();
            	        Cliente clienteAsociado = clientesMap.get(idCliente);
            	        cuentaget.setCliente(clienteAsociado);
            	    }

            	    request.setAttribute("cuentas", cuentas);
            	    request.getRequestDispatcher("/admin/ListadoCuentas.jsp").forward(request, response);
                    
                }
            	
                if (cuentaCount < 3) {
                	System.out.println("uwu bueno");
            	
            	
                boolean filas= accNegocio.update(cuenta);
                request.setAttribute("filas", filas);
                request.setAttribute("updateSuccess", true);                               
                }
                else {
                	System.out.println("uwu malo");
                	 request.setAttribute("errorMessage", "La cuenta no puede asociarse al nuevo cliente, ya posee 3 cuentas asociadas.");
                }
                
                if(!banderita) {
                Cuenta_NegocioImp cuentaNegocio = new Cuenta_NegocioImp();
        	    ArrayList<Cuenta> cuentas = cuentaNegocio.listarCuentas();
        	    Cliente_NegocioImp clienteNegocio = new Cliente_NegocioImp();
        	    ArrayList<Cliente> clientes = clienteNegocio.listarClientes();
        	    // Crear un mapa para asociar clientes por idCliente
        	    Map<Integer, Cliente> clientesMap = new HashMap<>();
        	    for (Cliente clienteget : clientes) {
        	        clientesMap.put(clienteget.getIdCLiente(), clienteget);
        	    }

        	    // Asociar clientes a cuentas mediante idCliente
        	    for (Cuenta cuentaget : cuentas) {
        	        int idCliente = cuentaget.getCliente().getIdCLiente();
        	        Cliente clienteAsociado = clientesMap.get(idCliente);
        	        cuentaget.setCliente(clienteAsociado);
        	    }

        	    request.setAttribute("cuentas", cuentas);
        	    request.getRequestDispatcher("/admin/ListadoCuentas.jsp").forward(request, response);
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
	
	}
}
