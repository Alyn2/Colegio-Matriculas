package dominio.entidades;

import java.util.ArrayList;

public class Grado {
    private int id;
    private String grado; //Aquí va "primer grado de secundaria"...
    private String seccion; //Aquí va A, B, C...

    
    /*VER VIDEOS, ANALIZAR E IMPLEMENTAR*/

    public Grado() {
       
    }

    public Grado(int id, String grado,  String seccion) {
        this.id = id;
        this.grado = grado;
        this.seccion = seccion;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    @Override
    public String toString() {
        return  this.getGrado()+" "+this.getSeccion();
    }

    
    
    
    
}
