/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objets;

import java.time.ZonedDateTime;

/**
 *
 * @author jaime
 */
public class Credito {

    private String id;
    private Cliente cliente;
    private int prestamo;
    private ZonedDateTime fechaInicio;
    private ZonedDateTime fechaFinal;
    private TipoCredito tipoCredito;

    public void setId(String id) {
        this.id = id;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setPrestamo(int prestamo) {
        this.prestamo = prestamo;
    }

    public void setFechaInicio(ZonedDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFinal(ZonedDateTime fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public void setTipoCredito(TipoCredito tipoCredito) {
        this.tipoCredito = tipoCredito;
    }

    public String getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getPrestamo() {
        return prestamo;
    }

    public ZonedDateTime getFechaInicio() {
        return fechaInicio;
    }

    public ZonedDateTime getFechaFinal() {
        return fechaFinal;
    }

    public TipoCredito getTipoCredito() {
        return tipoCredito;
    }

    public Credito(String id, Cliente cliente, int prestamo, ZonedDateTime fechaInicio, ZonedDateTime fechaFinal, TipoCredito tipoCredito) {
        this.id = id;
        this.cliente = cliente;
        this.prestamo = prestamo;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.tipoCredito = tipoCredito;
    }
}
