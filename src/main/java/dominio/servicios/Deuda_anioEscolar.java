/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio.servicios;

import dominio.entidades.Alumno;

/**
 *
 * @author USER
 */
public class Deuda_anioEscolar {
    int id;
    Alumno alumno;
    double deudaTotal;

    public Deuda_anioEscolar() {
    }

    public Deuda_anioEscolar(int id, Alumno alumno, double deudaTotal) {
        this.id = id;
        this.alumno = alumno;
        this.deudaTotal = deudaTotal;
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

    public double getDeudaTotal() {
        return deudaTotal;
    }

    public void setDeudaTotal(double deudaTotal) {
        this.deudaTotal = deudaTotal;
    }
    
}
