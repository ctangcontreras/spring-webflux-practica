package com.bezkoder.spring.webflux.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.webflux.dto.EmpleadoDeleteDto;
import com.bezkoder.spring.webflux.dto.EmpleadoDto;
import com.bezkoder.spring.webflux.dto.EmpleadoOutDto;
import com.bezkoder.spring.webflux.model.Empleado;
import com.bezkoder.spring.webflux.repository.EmpleadoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmpleadoService {
    
    @Autowired
    EmpleadoRepository empleadoRepository;    

    public Flux<EmpleadoOutDto> listarEmpleados() {
        return empleadoRepository.findAll().filter(
                e-> e.getEstado()!=null && e.getEstado().equals("1")
                )
                .map(e -> {
                    EmpleadoOutDto dto = new EmpleadoOutDto();
                    dto.setIdEmpleado(e.getIdEmpleado());
                    dto.setNombres(e.getNombres()); 
                    dto.setApellidoPaterno(e.getApellidoPaterno());
                    dto.setApellidoMaterno(e.getApellidoMaterno());
                    dto.setEdad(e.getEdad());
                    dto.setEstado(e.getEstado());
                    dto.setFechaNacimiento(e.getFechaNacimiento());
                    dto.setSalario(e.getSalario());
                    return dto;
                });
    }
    

    public Mono<EmpleadoOutDto> obtenerEmpleado(Integer idEmpleado) {
        return empleadoRepository.findById(idEmpleado)
                .map(e -> {
                    EmpleadoOutDto dto = new EmpleadoOutDto();
                    dto.setIdEmpleado(e.getIdEmpleado());
                    dto.setNombres(e.getNombres()); 
                    dto.setApellidoPaterno(e.getApellidoPaterno());
                    dto.setApellidoMaterno(e.getApellidoMaterno());
                    dto.setEdad(e.getEdad());
                    dto.setEstado(e.getEstado());
                    dto.setFechaNacimiento(e.getFechaNacimiento());
                    dto.setSalario(e.getSalario());
                    return dto;
                });
    }

    public Mono<Empleado> deleteEmpleado(Integer idEmpleado, EmpleadoDeleteDto dto) {        
        return empleadoRepository.findById(idEmpleado)
                .flatMap(e -> {
                    e.setEstado("0");
                    e.setUsuarioElimina(dto.getUsuarioElimina());
                    e.setFechaElimina(LocalDate.now());                    
                    return empleadoRepository.save(e);
                });
    }
 
    public Mono<Empleado> registrarEmpleado(EmpleadoDto empleadoDto) { 
        Empleado empleado = new Empleado(); 
        empleado.setNombres(empleadoDto.getNombres());
        empleado.setApellidoPaterno(empleadoDto.getApellidoPaterno());
        empleado.setApellidoMaterno(empleadoDto.getApellidoMaterno());
        empleado.setEdad(empleadoDto.getEdad());
        empleado.setEstado("1");
        empleado.setFechaCreacion(LocalDate.now());
        empleado.setUsuarioCreacion(empleadoDto.getIdUsuario());
        empleado.setFechaNacimiento(empleadoDto.getFechaNacimiento());
        empleado.setSalario(empleadoDto.getSalario());

        return empleadoRepository.save(empleado);
    }

    public Mono<Empleado> actualizarEmpleado(Integer idEmpleado, EmpleadoDto empleadoDto) { 
        return empleadoRepository.findById(idEmpleado)
                .flatMap(empleado -> {                   
                    empleado.setNombres(empleadoDto.getNombres());
                    empleado.setApellidoPaterno(empleadoDto.getApellidoPaterno());
                    empleado.setApellidoMaterno(empleadoDto.getApellidoMaterno());
                    empleado.setEdad(empleadoDto.getEdad());
                    empleado.setEstado("1");
                    empleado.setFechaModifica(LocalDate.now());
                    empleado.setUsuarioModifica(empleadoDto.getIdUsuario());
                    empleado.setFechaNacimiento(empleadoDto.getFechaNacimiento());
                    empleado.setSalario(empleadoDto.getSalario());
                    return empleadoRepository.save(empleado);
                });
    }
}
