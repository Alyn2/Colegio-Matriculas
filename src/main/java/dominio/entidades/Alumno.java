package dominio.entidades;

import java.sql.Date;

public class Alumno extends Persona{
    private int id;
    java.sql.Date fechaNacimiento;
    private String departamento;
    private String provincia;
    private String distrito;
    private String direccion;
    private Apoderado apoderado = new Apoderado();

    public Alumno() {
    }

    public Alumno(int id, Date fechaNacimiento, String departamento, String provincia, String distrito, String direccion, String primerNombre, String segundoNombre, String apellidoPaterno, String apellidoMaterno, String genero, DocumentoIdentidad documentoIdentidad) {
        super(primerNombre, segundoNombre, apellidoPaterno, apellidoMaterno, genero, documentoIdentidad);
        this.id = id;
        this.fechaNacimiento = fechaNacimiento;
        this.departamento = departamento;
        this.provincia = provincia;
        this.distrito = distrito;
        this.direccion = direccion;
    }
      
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }


    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Apoderado getApoderado() {
        return apoderado;
    }

    public void setApoderado(Apoderado apoderado) {
        this.apoderado = apoderado;
    }
    
    @Override
    public String toString(){
        return this.getPrimerNombre()+" "+this.getApellidoPaterno()+
                " "+this.getApellidoMaterno();
    }
}
