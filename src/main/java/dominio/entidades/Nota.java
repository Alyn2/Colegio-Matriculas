package dominio.entidades;

public class Nota {
    private int id;
    private Curso curso;
    private Alumno alumno;
    private double b1;
    private double b2;
    private double b3;
    private double b4;
    private double promedio;
    private String condicion;

    public Nota() {
    }

    public Nota(int id, Curso curso, Alumno alumno, double b1, double b2, double b3, double b4, double promedio, String condicion) {
        this.id = id;
        this.curso = curso;
        this.alumno = alumno;
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
        this.b4 = b4;
        this.promedio = promedio;
        this.condicion = condicion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public double getB1() {
        return b1;
    }

    public void setB1(double b1) {
        this.b1 = b1;
    }

    public double getB2() {
        return b2;
    }

    public void setB2(double b2) {
        this.b2 = b2;
    }

    public double getB3() {
        return b3;
    }

    public void setB3(double b3) {
        this.b3 = b3;
    }

    public double getB4() {
        return b4;
    }

    public void setB4(double b4) {
        this.b4 = b4;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio() {
        double promedioSinRedondear = (this.b1 + this.b2 + this.b3 + this.b4) / 4;
        this.promedio = Math.round(promedioSinRedondear);
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion() {
        if (this.promedio >= 11.0) {
            this.condicion = "aprobado";
        } else {
            this.condicion = "desaprobado";
        }
    }
    
}