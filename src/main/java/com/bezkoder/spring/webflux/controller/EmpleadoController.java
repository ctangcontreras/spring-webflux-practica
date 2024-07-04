package com.bezkoder.spring.webflux.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.webflux.dto.EmpleadoDeleteDto;
import com.bezkoder.spring.webflux.dto.EmpleadoDto;
import com.bezkoder.spring.webflux.model.HttpResult;
import com.bezkoder.spring.webflux.service.EmpleadoService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {
    
    private static final Logger log = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    EmpleadoService empleadoService;

 
    @RequestMapping(value = "/getEmpleados", method = RequestMethod.GET)
    public Mono<HttpResult> getEmpleados() {
            return empleadoService.listarEmpleados().collectList()
            .flatMap(e->
                Mono.just(new HttpResult(200, "ok",e))

            ).onErrorResume(e -> {
                log.error(e.getMessage(), e);
                return Mono.just(new HttpResult(500, "error",e));

            }); 
    }


    @RequestMapping(value = "/getEmplado/{idEmpleado}", method = RequestMethod.GET)
    public Mono<HttpResult> getCliente(@PathVariable("idEmpleado") Integer idEmpleado) {
        return empleadoService.obtenerEmpleado(idEmpleado).map(
            e-> {
                HttpResult result = new HttpResult();
                result.setCode(1);
                result.setData(e);
                result.setMsg("ok");
                return result;
            }
        ).onErrorResume(e -> {
            log.error(e.getMessage(), e);
            return Mono.just(new HttpResult(500, "error",e));

        });
    }

    @RequestMapping(value = "/delete/{idEmpleado}", method = RequestMethod.DELETE)
    public Mono<HttpResult> deleteEmpleado(@PathVariable("idEmpleado") Integer idEmpleado, @RequestBody EmpleadoDeleteDto dto) {
        return empleadoService.deleteEmpleado(idEmpleado, dto).map(
            e-> {
                HttpResult result = new HttpResult();
                result.setCode(1);
                result.setData(e);
                result.setMsg("ok");
                return result;
            }
        ).onErrorResume(e -> {
            log.error(e.getMessage(), e);
            return Mono.just(new HttpResult(500, "error",e));

        });
    }

    @RequestMapping(value = "/registrar", method = RequestMethod.POST)
    public Mono<HttpResult> registrarEmpleado(@RequestBody EmpleadoDto dto) {
        return empleadoService.registrarEmpleado(dto).map(
            e-> {
                HttpResult result = new HttpResult();
                result.setCode(1);
                result.setData(e);
                result.setMsg("ok");
                return result;
            }
        ).onErrorResume(e -> {
            log.error(e.getMessage(), e);
            return Mono.just(new HttpResult(500, "error",e));

        });
    }

    @RequestMapping(value = "/actualizar/{idEmpleado}", method = RequestMethod.PUT)
    public Mono<HttpResult> actualizar(@PathVariable("idEmpleado") Integer idEmpleado, @RequestBody EmpleadoDto dto) {
        return empleadoService.actualizarEmpleado(idEmpleado, dto).map(
            e-> {
                HttpResult result = new HttpResult();
                result.setCode(1);
                result.setData(e);
                result.setMsg("ok");
                return result;
            }
        ).onErrorResume(e -> {
            log.error(e.getMessage(), e);
            return Mono.just(new HttpResult(500, "error",e));

        });
    }
}
