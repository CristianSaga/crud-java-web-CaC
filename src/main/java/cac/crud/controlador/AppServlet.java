
package cac.crud.controlador;

import cac.crud.modelo.Alumno;
import cac.crud.modelo.Modelo;
import cac.crud.modelo.ModeloHC;
import cac.crud.modelo.ModeloMySQL;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "AppServlet", urlPatterns = {"/app"})
public class AppServlet extends HttpServlet {
    
    private Modelo model;
    private static final String URI_LIST = "WEB-INF/pages/alumnos/listadoAlumnos.jsp";
    private static final String URI_REMOVE = "WEB-INF/pages/alumnos/borrarAlumno.jsp";
    private static final String URI_EDIT = "WEB-INF/pages/alumnos/editarAlumno.jsp";
    private static final String URI_VER = "WEB-INF/pages/alumnos/verAlumno.jsp";

    @Override
    public void init() throws ServletException {
        this.model = new ModeloMySQL();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String laAccion = req.getParameter("accion");
        String idStr = req.getParameter("id");

        //caso que sea null
        int id = (idStr == null ? -1 : Integer.parseInt(idStr));
        laAccion = (laAccion == null ? "" : laAccion);

        //switch segun el boton que elija
        switch (laAccion) {
            case "remove":
                req.setAttribute("alumnoABorrar", model.getAlumno(id));
                req.getRequestDispatcher(URI_REMOVE).forward(req, resp);
                break;
            case "edit":
                Alumno alu = model.getAlumno(id);
                req.setAttribute("alumnoAEditar", alu);
                req.setAttribute("yaTieneFoto", !alu.getFoto().contains("no-face"));
                req.getRequestDispatcher(URI_EDIT).forward(req, resp);
                break;
            case "ver":
                req.setAttribute("alumnoAVer", model.getAlumno(id));
                req.getRequestDispatcher(URI_VER).forward(req, resp);
                break;   
            default:
                req.setAttribute("listaAlumnos", model.getAlumnos());
                req.getRequestDispatcher(URI_LIST).forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String laAccion = req.getParameter("accion");
        String idStr = req.getParameter("id");

        int id = (idStr == null ? -1 : Integer.parseInt(idStr));
        laAccion = (laAccion == null ? "" : laAccion);

        switch (laAccion) {
            case "delete":                
                model.removeAlumno(id);
                break;
            case "update":
                Alumno aluEditar = model.getAlumno(id);
                cargarAlumnoSegunParams(aluEditar, req);
                model.updateAlumno(aluEditar);
                break;
            case "add":
                Alumno aluAgregar = new Alumno();
                cargarAlumnoSegunParams(aluAgregar, req);
                model.addAlumno(aluAgregar);
                break;          
        }
        //redirecciona a la pagina app
        resp.sendRedirect(getServletContext().getContextPath() + "/app");
    }
    
    //metodo para setear todos los parametros a editar
    private void cargarAlumnoSegunParams(Alumno a, HttpServletRequest request) {
        a.setNombre(request.getParameter("nombre"));
        a.setApellido(request.getParameter("apellido"));
        a.setMail(request.getParameter("mail"));
        a.setFechaNacimiento(request.getParameter("fechaNac"));
        a.setFoto(request.getParameter("fotoBase64"));
    }
    
}
