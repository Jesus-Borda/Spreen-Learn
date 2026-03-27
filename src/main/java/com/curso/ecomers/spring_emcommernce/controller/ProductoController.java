    package com.curso.ecomers.spring_emcommernce.controller;

    import com.curso.ecomers.spring_emcommernce.model.Producto;

    import com.curso.ecomers.spring_emcommernce.model.Usuario;
    import com.curso.ecomers.spring_emcommernce.service.ProductoService;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestMapping;

    @Controller
    @RequestMapping("/productos")
    //Mapea el controllador como /productos
    public class ProductoController {
        private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
        @Autowired
        private ProductoService productoService;
        //NOS TRAE LA VISTA
        @GetMapping("")
        public String show( Model model){
            model.addAttribute("productos", productoService.findAll());
            //Lo que hace es redireccionar al archivo

            return "productos/show";
        }
        @GetMapping("/create")
        public  String create (){
            return "productos/create";
        }

        //------------------------------------------------------METODOS ----------------
        @PostMapping("/save")
        public String save ( Producto producto){
            LOGGER.info("Este es el objeto del producto {}",producto);
            Usuario usuario = new Usuario();
            usuario.setId(1);
            producto.setUsuario(usuario);
            productoService.save(producto);
            return "redirect:/productos";
        }
    }
