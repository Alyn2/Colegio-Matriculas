/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio.servicios;

import java.sql.Date;

/**
 *
 * @author USER
 */
public class Pago_aniooEscolar implements Pago {
    
    int id;
    double montoAbonado;
    Date fecha;
    Deuda_anioEscolar deuda;
    TipoPago tipo_de_pago;

    public Pago_aniooEscolar() {
    }

    public Pago_aniooEscolar(int id, double montoAbonado, Date fecha, Deuda_anioEscolar deuda, TipoPago tipo_de_pago) {
        this.id = id;
        this.montoAbonado = montoAbonado;
        this.fecha = fecha;
        this.deuda = deuda;
        this.tipo_de_pago = tipo_de_pago;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMontoAbonado() {
        return montoAbonado;
    }

    public void setMontoAbonado(double montoAbonado) {
        this.montoAbonado = montoAbonado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Deuda_anioEscolar getDeuda() {
        return deuda;
    }

    public void setDeuda(Deuda_anioEscolar deuda) {
        this.deuda = deuda;
    }

    public TipoPago getTipo_de_pago() {
        return tipo_de_pago;
    }

    public void setTipo_de_pago(TipoPago tipo_de_pago) {
        this.tipo_de_pago = tipo_de_pago;
    }
    
    
    
    
    
}
