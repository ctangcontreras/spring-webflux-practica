package com.bezkoder.spring.webflux.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PRODUCTO",schema = "FINANCIA")
@Setter
@Getter
public class Producto {
    
    @Id
    @Column("ID_PRODUCTO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducto;

    @Column("ID_CLIENTE")
    private Integer idCliente;

    @Column("TIPO_PRODUCTO")
    private String tipoProducto;

    @Column("NOMBRE_PRODUCTO")
    private String nombreProducto;

    @Column("SALDO")
    private BigDecimal saldo;

    @Column("ACTIVO")
    private Integer activo;

    @Column("FECHA_CREACION")
    private Timestamp fechaCreacion;

    public Producto() {
    }

    public Producto(Integer idProducto, Integer idCliente, String tipoProducto, String nombreProducto, BigDecimal saldo,
            Integer activo, Timestamp fechaCreacion) {
        this.idProducto = idProducto;
        this.idCliente = idCliente;
        this.tipoProducto = tipoProducto;
        this.nombreProducto = nombreProducto;
        this.saldo = saldo;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
    }
    
}