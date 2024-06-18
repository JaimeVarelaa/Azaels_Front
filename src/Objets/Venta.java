/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objets;

/**
 *
 * @author draks
 */
public class Venta {

    private String id;
    private Producto producto;
    private Cliente cliente;
    private Empleado empleado;
    private String tipo_pago;
    private int cant_venta;
    private int precio_venta;

    public void setId(String id) {
        this.id = id;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public void setTipo_pago(String tipo_pago) {
        this.tipo_pago = tipo_pago;
    }

    public void setCant_venta(int cant_venta) {
        this.cant_venta = cant_venta;
    }

    public void setPrecio_venta(int precio_venta) {
        this.precio_venta = precio_venta;
    }

    public String getId() {
        return id;
    }

    public Producto getProducto() {
        return producto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public String getTipo_pago() {
        return tipo_pago;
    }

    public int getCant_venta() {
        return cant_venta;
    }

    public int getPrecio_venta() {
        return precio_venta;
    }

    public Venta(String id, Producto producto, Cliente cliente, Empleado empleado, String tipo_pago, int cant_venta, int precio_venta) {
        this.id = id;
        this.producto = producto;
        this.cliente = cliente;
        this.empleado = empleado;
        this.tipo_pago = tipo_pago;
        this.cant_venta = cant_venta;
        this.precio_venta = precio_venta;
    }
}
