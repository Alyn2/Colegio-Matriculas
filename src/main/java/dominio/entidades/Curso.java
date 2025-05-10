package dominio.entidades;

public class Curso {
    private int id;
    private String nomcurso;
    private Docente docente = new Docente();
    private int creditos;
    private int horas;
    private Grado grado = new Grado();

    public Curso() {
    }

    public Curso(int id, String nomcurso, int creditos, int horas) {
        this.id = id;
        this.nomcurso = nomcurso;
        this.creditos = creditos;
        this.horas = horas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomcurso() {
        return nomcurso;
    }

    public void setNomcurso(String nomcurso) {
        this.nomcurso = nomcurso;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }
    @Override
    public String toString(){
        return this.getNomcurso();
    }    
    
}