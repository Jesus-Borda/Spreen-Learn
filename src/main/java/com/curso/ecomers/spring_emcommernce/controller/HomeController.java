package com.curso.ecomers.spring_emcommernce.controller;

import com.curso.ecomers.spring_emcommernce.model.DetalleOrden;
import com.curso.ecomers.spring_emcommernce.model.Orden;
import com.curso.ecomers.spring_emcommernce.model.Producto;
import com.curso.ecomers.spring_emcommernce.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger log= LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private  ProductoService productoService;
    //PARA ALMACENAR LOS DETALLES DE LA ORDEN
    private List<DetalleOrden> detalleOrdens=new ArrayList<DetalleOrden>();
    //DATOS DE LA ORDEN
    Orden orden = new Orden();
    @GetMapping("")
    public String home (Model model){
        model.addAttribute("productos",productoService.findAll() );

        return "usuario/home";
    }
    @GetMapping("productohome/{id}")
    public String productoHome (@PathVariable Integer id, Model model){

         log.info("ID producto enviado como parametro {} ", id);
         Producto producto = new Producto();
         Optional<Producto> productoOptional = productoService.get(id);
         producto = productoOptional.get();
         model.addAttribute("producto",producto);
         return "usuario/productohome";
    }
    @PostMapping("/cart")
    public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad){
        log.info("ID producto envado al carrito {} ", id);
        DetalleOrden detalleOrden = new DetalleOrden();
        Producto producto = new Producto();
        Optional<Producto> productoOptional = productoService.get(id);
        log.info("producto añadido {} ", productoOptional.get());
        log.info("Cantidad {} ",cantidad);
       // producto=producto.setCantidad();

    return "usuario/carrito";
    }
}
