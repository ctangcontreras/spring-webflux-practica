package com.bezkoder.spring.webflux.dto;

public class DetalleProductoDto {
    
    private Integer idDetalleProducto;
    private Integer idProducto;

    public Integer getIdDetalleProducto() {
        return idDetalleProducto;
    }

    public void setIdDetalleProducto(Integer idDetalleProducto) {
        this.idDetalleProducto = idDetalleProducto;
    }

 
    public DetalleProductoDto(Integer idDetalleProducto, Integer idProducto) {
        this.idDetalleProducto = idDetalleProducto;
        this.idProducto = idProducto;
    }

    public DetalleProductoDto() {
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }
    
      
}
