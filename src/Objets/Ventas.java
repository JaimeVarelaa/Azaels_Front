/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objets;

/**
 *
 * @author draks
 */
public class Ventas {
    private String id;

    /**
     * Nombre del cliente
     */
   
    private Producto producto;
    /**
     * Edad del cliente
     */
    private Empleado empleado;

    /**
     * Sexo del cliente
     */
    private String tipo_pago;
    
    private int cant_venta;
    
    private int precio_venta;
    
    private Cliente cliente;

    public Ventas(String id, Producto producto, Empleado empleado, String tipo_pago, int cant_venta, int precio_venta, Cliente cliente) {
        this.id = id;
        this.producto = producto;
        this.empleado = empleado;
        this.tipo_pago = tipo_pago;
        this.cant_venta = cant_venta;
        this.precio_venta = precio_venta;
        this.cliente = cliente;
    }

    public String getId() {
        return id;
    }

    public Producto getProducto() {
        return producto;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
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

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    
    
    
}
