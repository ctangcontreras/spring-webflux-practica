package com.bezkoder.spring.webflux.dto;

import java.math.BigDecimal;
import java.util.List;

public class ProductoDto {
    
    private Integer idProducto;
    private Integer idCliente;
    private String tipoProducto;
    private String nombreProducto;
    private BigDecimal saldo;
    private List<DetalleProductoDto> listaDetalle;
    

    public ProductoDto() {
    }
    public ProductoDto(Integer idProducto, Integer idCliente, String tipoProducto, String nombreProducto,
            BigDecimal saldo, List<DetalleProductoDto> listaDetalle) {
        this.idProducto = idProducto;
        this.idCliente = idCliente;
        this.tipoProducto = tipoProducto;
        this.nombreProducto = nombreProducto;
        this.saldo = saldo;
        this.listaDetalle = listaDetalle;
    }
    public Integer getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }
    public Integer getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }
    public String getTipoProducto() {
        return tipoProducto;
    }
    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }
    public String getNombreProducto() {
        return nombreProducto;
    }
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    public BigDecimal getSaldo() {
        return saldo;
    }
    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public List<DetalleProductoDto> getListaDetalle() {
        return listaDetalle;
    }
    public void setListaDetalle(List<DetalleProductoDto> listaDetalle) {
        this.listaDetalle = listaDetalle;
    }    
}
