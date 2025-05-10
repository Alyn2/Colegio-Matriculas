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
public class PagoMatricula implements Pago{
    
    int id;
    TipoPago tipo_de_pago;
    Alumno alumno;
    double Monto_Abonado;

    public PagoMatricula() {
    }

    public PagoMatricula(int id, TipoPago tipo_de_pago, Alumno alumno, double Monto_Abonado) {
        this.id = id;
        this.tipo_de_pago = tipo_de_pago;
        this.alumno = alumno;
        this.Monto_Abonado = Monto_Abonado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoPago getTipo_de_pago() {
        return tipo_de_pago;
    }

    public void setTipo_de_pago(TipoPago tipo_de_pago) {
        this.tipo_de_pago = tipo_de_pago;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public double getMonto_Abonado() {
        return Monto_Abonado;
    }

    public void setMonto_Abonado(double Monto_Abonado) {
        this.Monto_Abonado = Monto_Abonado;
    }
    
    
    
    
}
