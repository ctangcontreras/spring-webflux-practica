package com.bezkoder.spring.webflux.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "EMPLEADO",schema = "FINANCIA")
@Setter
@Getter
public class Empleado {
    
    @Id
    @Column("ID_EMPLEADO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmpleado;

    @Column("NOMBRE")
    private String nombres;

    @Column("SALARIO")
    private BigDecimal salario;

    @Column("APELLIDO_PATERNO")
    private String apellidoPaterno; 

    @Column("APELLIDO_MATERNO")
    private String apellidoMaterno;

    @Column("EDAD")
    private Integer edad;

    @Column("FECHA_NACIMIENTO")
    private LocalDate fechaNacimiento;

    @Column("ESTADO")
    private String estado;
     
    @Column("FECHA_CREACION")
    private LocalDate fechaCreacion;

    @Column("FECHA_MODIFICA")
    private LocalDate fechaModifica;

    @Column("FECHA_ELIMINA")
    private LocalDate fechaElimina;

    @Column("USUARIO_CREACION")
    private Integer usuarioCreacion;
    
    @Column("USUARIO_MODIFICA")
    private Integer usuarioModifica;

    @Column("USUARIO_ELIMINA")
    private Integer usuarioElimina;
}