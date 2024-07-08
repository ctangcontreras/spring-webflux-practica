package com.bezkoder.spring.webflux.service;

import com.bezkoder.spring.webflux.dto.EmpleadoDeleteDto;
import com.bezkoder.spring.webflux.dto.EmpleadoDto;
import com.bezkoder.spring.webflux.dto.EmpleadoOutDto;
import com.bezkoder.spring.webflux.model.Empleado;
import com.bezkoder.spring.webflux.repository.EmpleadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class EmpleadoServiceTest {

    @InjectMocks
    private EmpleadoService empleadoService;

    @Mock
    private EmpleadoRepository empleadoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarEmpleados() {
        Empleado empleado = new Empleado();
        empleado.setEstado("1");
        when(empleadoRepository.findAll()).thenReturn(Flux.just(empleado));

        Flux<EmpleadoOutDto> result = empleadoService.listarEmpleados();

        StepVerifier.create(result)
                .expectNextMatches(e -> "1".equals(e.getEstado()))
                .verifyComplete();
    }

    @Test
    public void testObtenerEmpleado() {
        Empleado empleado = new Empleado();
        when(empleadoRepository.findById(any(Integer.class))).thenReturn(Mono.just(empleado));

        Mono<EmpleadoOutDto> result = empleadoService.obtenerEmpleado(1);

        StepVerifier.create(result)
                .expectNextMatches(e -> e != null)
                .verifyComplete();
    }

    @Test
    public void testDeleteEmpleado() {
        Empleado empleado = new Empleado();
        EmpleadoDeleteDto dto = new EmpleadoDeleteDto();
        when(empleadoRepository.findById(any(Integer.class))).thenReturn(Mono.just(empleado));
        when(empleadoRepository.save(any(Empleado.class))).thenReturn(Mono.just(empleado));

        Mono<Empleado> result = empleadoService.deleteEmpleado(1, dto);

        StepVerifier.create(result)
                .expectNextMatches(e -> "0".equals(e.getEstado()))
                .verifyComplete();
    }

    @Test
    public void testRegistrarEmpleado() {
        EmpleadoDto dto = new EmpleadoDto();
        Empleado empleado = new Empleado();
        when(empleadoRepository.save(any(Empleado.class))).thenReturn(Mono.just(empleado));

        Mono<Empleado> result = empleadoService.registrarEmpleado(dto);

        StepVerifier.create(result)
                .expectNextMatches(e -> e != null)
                .verifyComplete();
    }

    @Test
    public void testActualizarEmpleado() {
        EmpleadoDto dto = new EmpleadoDto();
        Empleado empleado = new Empleado();
        when(empleadoRepository.findById(any(Integer.class))).thenReturn(Mono.just(empleado));
        when(empleadoRepository.save(any(Empleado.class))).thenReturn(Mono.just(empleado));

        Mono<Empleado> result = empleadoService.actualizarEmpleado(1, dto);

        StepVerifier.create(result)
                .expectNextMatches(e -> e != null)
                .verifyComplete();
    }
}
