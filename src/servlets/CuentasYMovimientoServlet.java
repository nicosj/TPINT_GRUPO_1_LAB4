package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao_Implement.CuentaDao_Implement;
import dao_Implement.MovimientoDao;
import dao_Implement.UsuarioDao_Implement;
import dominio.Cuenta;
import dominio.Movimiento;
import dominio.Usuario;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/client/CuentasYMovimientoServlet")
public class CuentasYMovimientoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CuentasYMovimientoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session;
        session = request.getSession();
        Usuario client = (Usuario)session.getAttribute("client");
            if (client == null) {
                request.getRequestDispatcher("/LoginServlet").forward(request, response);
            }

        CuentaDao_Implement cuentaDao = new CuentaDao_Implement();
        ArrayList<Cuenta> cuentas = cuentaDao.readAllByID(client.getIdCliente());

        session.setAttribute("cuentas", cuentas);
        session.setAttribute("movimientos", null);
        request.getRequestDispatcher("/client/CuentaMovimiento.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session;
        session = request.getSession();
        if(request.getParameter("ncuenta") != null){

            String idCuenta = request.getParameter("ncuenta");
            System.out.println("entro mov "+idCuenta);
            MovimientoDao movimientoDao = new MovimientoDao();
            ArrayList<Movimiento> movimientos = movimientoDao.readAllMovimientos(idCuenta);
            session.setAttribute("movimientos", movimientos);
            request.getRequestDispatcher("/client/CuentaMovimiento.jsp").forward(request, response);
        }
    }

}
