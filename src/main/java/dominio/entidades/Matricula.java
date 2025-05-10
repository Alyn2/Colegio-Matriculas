package dominio.entidades;

import persistencia.AlumnoDAO;
import persistencia.GradoDAO;

public class Matricula {
    private int id;
    private Alumno alumno;
    private Grado grado;
    private String anioAcademico;

    public Matricula() {
    }


    public Matricula(int idAlumno, int idGrado, String anioAcademico) {
        AlumnoDAO alumnoDAO = new AlumnoDAO();
        this.alumno = alumnoDAO.buscarPorId(idAlumno);

        GradoDAO gradoDAO = new GradoDAO();
        this.grado = gradoDAO.buscarPorId(idGrado);

        this.anioAcademico = anioAcademico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }

    public String getAnioAcademico() {
        return anioAcademico;
    }

    public void setAnioAcademico(String anioAcademico) {
        this.anioAcademico = anioAcademico;
    }

        
}
