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
@Table(name = "USUARIO",schema = "FINANCIA")
@Setter
@Getter
public class Usuario   {
    
    @Id
    @Column("ID_USUARIO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;
    @Column("USUARIO")
    private String usuario;
    @Column("CONTRASENIA")
    private String contrasenia;
    @Column("FECHA_CREACION")
    private Timestamp fechaCreacion;

    
    
}
