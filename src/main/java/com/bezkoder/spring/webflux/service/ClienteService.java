package com.bezkoder.spring.webflux.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.webflux.dto.ClienteDto;
import com.bezkoder.spring.webflux.dto.DetalleProductoDto;
import com.bezkoder.spring.webflux.dto.ProductoDto;
import com.bezkoder.spring.webflux.model.Producto;
import com.bezkoder.spring.webflux.repository.ClienteRepository;
import com.bezkoder.spring.webflux.repository.ProductoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClienteService {
    
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ProductoRepository productoRepository;

    public Mono<ClienteDto> listarClientePorId(Integer idCliente) {
        return clienteRepository.findById(idCliente)
                .map(cliente -> {
                    ClienteDto dto = new ClienteDto();
                    dto.setIdCliente(cliente.getIdCliente());
                    dto.setCodigoUnico(cliente.getCodigoUnico());
                    dto.setNombres(cliente.getNombres());
                    dto.setApellidoPaterno(cliente.getApellidoPaterno());
                    dto.setApellidoMaterno(cliente.getApellidoMaterno());
                    dto.setTipoDocumento(cliente.getTipoDocumento());
                    dto.setNumeroDocumento(cliente.getNumeroDocumento());
                    return dto;
                })
                .defaultIfEmpty(new ClienteDto());
    }

    public Mono<Producto> listarProductoPorId(Integer idProducto){
        return productoRepository.findById(idProducto);
    }

    public Flux<Producto> listarProductosPorIdCliente(Integer idCliente){
        return productoRepository.findByIdCliente(idCliente);
    }

    public Mono<ClienteDto> getInfo(String codigoUnico) {
        return getInfoCliente(codigoUnico)
                .flatMap(clienteDto -> getInfoProductos(clienteDto.getIdCliente())
                        .collectList()
                        .map(productoDtos -> {
                            clienteDto.setListaProductos(productoDtos);
                            return clienteDto;
                        })
                );
    }
 


/*     public static void main(String[] args) {
        Mono<ClienteDto> clienteDto = Mono.fromCallable(()-> 
        new ClienteDto(1, "0001", "Carlos", 
        "Tang","Contreras", "DNI", "47153325"));
 
 
        List<ProductoDto> listaProducto = new ArrayList<>();
        listaProducto.add(new ProductoDto(1, 1, "tarjeta", "tarjeta credito", new BigDecimal("10"), null)); 
        listaProducto.add(new ProductoDto(2, 1, "tarjeta", "tarjeta credito2", new BigDecimal("14"), null)); 

        List<DetalleProductoDto> listaDetalle = new ArrayList<>();
        listaDetalle.add(new DetalleProductoDto(1,1)); 
        listaDetalle.add(new DetalleProductoDto(2,1)); 
        listaDetalle.add(new DetalleProductoDto(3,2)); 
        

        Flux<ProductoDto> productoFlux = Flux.fromIterable(listaProducto);
        Flux<DetalleProductoDto> detalleFlux = Flux.fromIterable(listaDetalle);



        clienteDto.flatMap( cliente-> 
              
           productoFlux.filter(p -> p.getIdCliente().equals(cliente.getIdCliente()))
                .flatMap(
                    p->
                    { 
                        return detalleFlux.filter(d->d.getIdProducto().equals(p.getIdProducto()))
                            .collectList()
                            .map(d->
                                {
                                    p.setListaDetalle(d);
                                    return p;
                                }
                            );
                    } 
                    
            ).collectList()
            .map(productos->{
                cliente.setListaProductos(productos);
                return cliente;
            })
  
        ).subscribe(cliente ->
        System.out.println("here"+cliente.toString()) );


        clienteDto.flatMap(cliente ->
            productoFlux.filter(p -> p.getIdCliente() == cliente.getIdCliente())
                    .flatMap(p ->
                            detalleFlux.filter(d -> d.getIdProducto() == p.getIdProducto())
                                    .collectList()
                                    .map(d -> {
                                        p.setListaDetalle(d);
                                        return p;
                                    })
                    )
                    .collectList()
                    .map(productos -> { 
                        cliente.setListaProductos(productos);
                        return cliente;
                    })
        ).subscribe(cliente -> System.out.println("here " + cliente));

        clienteDto.subscribe(cliente->
        System.out.println(cliente.toString()) );


        List<ClienteDto> listaCliente = new ArrayList<>();
        listaCliente.add(new ClienteDto(1, "0001", "Carlos", "Tang","Contreras", "DNI", "47153325"));
        listaCliente.add(new ClienteDto(2, "0002", "Pepe", "Tang","Contreras", "DNI", "47153325"));


        Flux<ClienteDto> clienteDtoFlux = Flux.fromIterable(listaCliente);
        

        clienteDtoFlux
        .subscribe(cliente->
        System.out.println("here"+cliente.toString()) );
    } */

    private Mono<ClienteDto> getInfoCliente(String codigoUnico) {
        return clienteRepository.findByCodigoUnico(codigoUnico)
                .map(cliente -> {
                    ClienteDto dto = new ClienteDto();
                    dto.setIdCliente(cliente.getIdCliente());
                    dto.setCodigoUnico(cliente.getCodigoUnico());
                    dto.setNombres(cliente.getNombres());
                    dto.setApellidoPaterno(cliente.getApellidoPaterno());
                    dto.setApellidoMaterno(cliente.getApellidoMaterno());
                    dto.setTipoDocumento(cliente.getTipoDocumento());
                    dto.setNumeroDocumento(cliente.getNumeroDocumento());
                    return dto;
                })
                .defaultIfEmpty(new ClienteDto());
    }

    private Flux<ProductoDto> getInfoProductos(Integer idCliente) {
        return productoRepository.findByIdCliente(idCliente)
                .map(producto -> {
                    ProductoDto dto = new ProductoDto();
                    dto.setIdProducto(producto.getIdProducto());
                    dto.setIdCliente(producto.getIdCliente());
                    dto.setTipoProducto(producto.getTipoProducto());
                    dto.setNombreProducto(producto.getNombreProducto());
                    dto.setSaldo(producto.getSaldo());
                    return dto;
                });
    }
    
}
