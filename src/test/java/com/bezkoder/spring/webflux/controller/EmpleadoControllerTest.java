package com.bezkoder.spring.webflux.controller;

import com.bezkoder.spring.webflux.dto.EmpleadoDeleteDto;
import com.bezkoder.spring.webflux.dto.EmpleadoDto;
import com.bezkoder.spring.webflux.dto.EmpleadoOutDto;
import com.bezkoder.spring.webflux.model.Empleado;
import com.bezkoder.spring.webflux.model.HttpResult;
import com.bezkoder.spring.webflux.service.EmpleadoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@WebFluxTest(controllers = EmpleadoController.class)
public class EmpleadoControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private EmpleadoService empleadoService;

    @BeforeEach
    public void setUp() {
        // Configurar mocks si es necesario
    }

    @Test
    public void testListarEmpleados() {
        EmpleadoOutDto empleadoOutDto = new EmpleadoOutDto();
        HttpResult httpResult = new HttpResult(200, "ok", Collections.singletonList(empleadoOutDto));
        when(empleadoService.listarEmpleados()).thenReturn(Flux.just(empleadoOutDto));

        webTestClient.get().uri("/empleado/getEmpleados")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(HttpResult.class)
                .consumeWith(response -> {
                    HttpResult result = response.getResponseBody();
                    assert result != null;
                    assert result.getCode() == 200;
                    assert result.getMsg().equals("ok");
                    assert result.getData() != null;
                });
    }

    @Test
    public void testObtenerEmpleado() {
        int id = 1;
        EmpleadoOutDto empleadoOutDto = new EmpleadoOutDto();
        HttpResult httpResult = new HttpResult(1, "ok", empleadoOutDto);
        when(empleadoService.obtenerEmpleado(id)).thenReturn(Mono.just(empleadoOutDto));

        webTestClient.get().uri("/empleado/getEmplado/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(HttpResult.class)
                .consumeWith(response -> {
                    HttpResult result = response.getResponseBody();
                    assert result != null;
                    assert result.getCode() == 1;
                    assert result.getMsg().equals("ok");
                    assert result.getData().equals(empleadoOutDto);
                });
    }

    @Test
    public void testRegistrarEmpleado() {
        EmpleadoDto empleadoDto = new EmpleadoDto();
        HttpResult httpResult = new HttpResult(1, "ok", empleadoDto);
        when(empleadoService.registrarEmpleado(any(EmpleadoDto.class))).thenReturn(Mono.just(new Empleado()));

        webTestClient.post().uri("/empleado/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(empleadoDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(HttpResult.class)
                .consumeWith(response -> {
                    HttpResult result = response.getResponseBody();
                    assert result != null;
                    assert result.getCode() == 1;
                    assert result.getMsg().equals("ok");
                    assert result.getData().equals(empleadoDto);
                });
    }

    @Test
    public void testActualizarEmpleado() {
        int id = 1;
        EmpleadoDto empleadoDto = new EmpleadoDto();
        HttpResult httpResult = new HttpResult(1, "ok", empleadoDto);
        when(empleadoService.actualizarEmpleado(any(Integer.class), any(EmpleadoDto.class))).thenReturn(Mono.just(new Empleado()));

        webTestClient.put().uri("/empleado/actualizar/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(empleadoDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(HttpResult.class)
                .consumeWith(response -> {
                    HttpResult result = response.getResponseBody();
                    assert result != null;
                    assert result.getCode() == 1;
                    assert result.getMsg().equals("ok");
                    assert result.getData().equals(empleadoDto);
                });
    }

    @Test
    public void testDeleteEmpleado() throws JsonProcessingException {
        int id = 1;
        EmpleadoDeleteDto dto = new EmpleadoDeleteDto();
        dto.setUsuarioElimina(123);

        HttpResult httpResult = new HttpResult(1, "ok", null);

        when(empleadoService.deleteEmpleado(id, dto)).thenReturn(Mono.just(new Empleado()));

        ObjectMapper objectMapper = new ObjectMapper();
        String dtoJson = objectMapper.writeValueAsString(dto);

        webTestClient.method(HttpMethod.DELETE)
                .uri("/empleado/delete/{idEmpleado}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dtoJson)
                .exchange()
                .expectStatus().isOk()
                .expectBody(HttpResult.class)
                .consumeWith(response -> {
                    HttpResult result = response.getResponseBody();
                    assert result != null;
                    assert result.getCode() == 1;
                    assert result.getMsg().equals("ok");
                    assert result.getData() == Mono.just(new Empleado());
                });
    }

}
