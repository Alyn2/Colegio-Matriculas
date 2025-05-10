package dominio.entidades;

public class DetallePago {
    private int id;
    private Pago pago = new Pago();
    private TipoPago tipoPago = new TipoPago();
    private double montoAbonado;
    
    public DetallePago() {
    }

    public DetallePago(int id, Pago pago, TipoPago tipoPago, double montoAbonado) {
        this.id = id;
        this.pago = pago;
        this.tipoPago = tipoPago;
        this.montoAbonado = montoAbonado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoPago getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(TipoPago tipoPago) {
        this.tipoPago = tipoPago;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public double getMontoAbonado() {
        return montoAbonado;
    }

    public void setMontoAbonado(double montoAbonado) {
        this.montoAbonado = montoAbonado;
    }

    public boolean realizarPago() {
        if (montoAbonado <= this.pago.getMontoTotal()) {
            this.pago.setMontoTotal(this.pago.getMontoTotal() - montoAbonado);
            return true;
        } else {
            return false;
        }
    }
}
