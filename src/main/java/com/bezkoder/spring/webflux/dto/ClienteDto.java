package com.bezkoder.spring.webflux.dto;

import java.util.List;

public class ClienteDto {
    
    private Integer idCliente;
    private String codigoUnico;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String tipoDocumento;
    private String numeroDocumento;
    private List<ProductoDto> listaProductos;

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }

    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public List<ProductoDto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<ProductoDto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public ClienteDto() {
    }

    public ClienteDto(Integer idCliente, String codigoUnico, String nombres, String apellidoPaterno,
            String apellidoMaterno, String tipoDocumento, String numeroDocumento) {
        this.idCliente = idCliente;
        this.codigoUnico = codigoUnico;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
    }

    @Override
    public String toString() {
        return "ClienteDto [idCliente=" + idCliente + ", codigoUnico=" + codigoUnico + ", nombres=" + nombres
                + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", tipoDocumento="
                + tipoDocumento + ", numeroDocumento=" + numeroDocumento + ", listaProductos=" + listaProductos + "]";
    }

    
    




}
