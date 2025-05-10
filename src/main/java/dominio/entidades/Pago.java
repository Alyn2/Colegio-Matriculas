package dominio.entidades;

public class Pago {
    private int id;
    private Alumno alumno = new Alumno();
    private double montoTotal;
    
    public Pago() {
        this.id = alumno.getId();
        this.montoTotal = 4800;
    }

    public Pago(Alumno alumno) { //Ver si no hace falta poner montoTotal
        this.id = alumno.getId();
        this.alumno = alumno;
        this.montoTotal = 4800;
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

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }
    
}
