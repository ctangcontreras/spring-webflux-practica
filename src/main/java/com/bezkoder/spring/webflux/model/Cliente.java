package com.bezkoder.spring.webflux.model;

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
@Table(name = "CLIENTE",schema = "FINANCIA")
@Setter
@Getter
public class Cliente {
    
    @Id
    @Column("ID_CLIENTE")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;

    @Column("CODIGO_UNICO")
    private String codigoUnico;

    @Column("NOMBRES")
    private String nombres;

    @Column("APELLIDO_PATERNO")
    private String apellidoPaterno;

    @Column("APELLIDO_MATERNO")
    private String apellidoMaterno;

    @Column("TIPO_DOCUMENTO")
    private String tipoDocumento;

    @Column("NUMERO_DOCUMENTO")
    private String numeroDocumento;

    @Column("ACTIVO")
    private Integer activo;

    @Column("USUARIO_CREACION")
    private Integer usuarioCreacion;

    @Column("FECHA_CREACION")
    private Timestamp fechaCreacion;

    public Cliente() {
    }

    public Cliente(Integer idCliente, String codigoUnico, String nombres, String apellidoPaterno,
            String apellidoMaterno, String tipoDocumento, String numeroDocumento, Integer activo,
            Integer usuarioCreacion, Timestamp fechaCreacion) {
        this.idCliente = idCliente;
        this.codigoUnico = codigoUnico;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.activo = activo;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
    }
    
}