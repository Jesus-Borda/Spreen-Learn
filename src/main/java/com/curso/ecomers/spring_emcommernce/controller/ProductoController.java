    package com.curso.ecomers.spring_emcommernce.controller;

    import com.curso.ecomers.spring_emcommernce.model.Producto;

    import com.curso.ecomers.spring_emcommernce.model.Usuario;
    import com.curso.ecomers.spring_emcommernce.service.ProductoService;
    import com.curso.ecomers.spring_emcommernce.service.UploadFileService;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.multipart.MultipartFile;

    import java.io.IOException;
    import java.util.Optional;

    @Controller
    @RequestMapping("/productos")
    //Mapea el controllador como /productos
    public class ProductoController {
        private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
        @Autowired
        private ProductoService productoService;
        @Autowired
        private UploadFileService upload;


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
        //ACTUALIZAR
        @GetMapping("/edit/{id}")
        public String edit (@PathVariable Integer id, Model model){
            Producto producto = new Producto();
            Optional <Producto> optionalProducto=productoService.get(id);
            producto = optionalProducto.get();
            LOGGER.info("producto buscado: {}", producto );
            model.addAttribute("producto",producto);

            return "productos/edit";
        }
        @GetMapping("/delete/{id}")
        public String delete (@PathVariable Integer id){
            Producto p = new Producto();
            p=productoService.get(id).get();
            //Eliminar imagen cuando no sea la de default
            if (!p.getImagen().equals(  "default.jpg")){
                upload.deleteImage(p.getImagen());
            }


            productoService.delete(id);
            return "redirect:/productos";

        }


        //------------------------------------------------------METODOS ----------------
        @PostMapping("/save")
        public String save (Producto producto, @RequestParam("img") MultipartFile file) throws IOException {
            LOGGER.info("Este es el objeto del producto {}",producto);

            Usuario usuario = new Usuario();
            usuario.setId(1);
            producto.setUsuario(usuario);

            //GUARDAR IMAGEN

            if (producto.getId()==null){
                String nombreImagen = upload.saveImage(file);
                producto.setImagen(nombreImagen);
            } else {

            }

            productoService.save(producto);
            return "redirect:/productos";
        }
        @PostMapping ("/update")
        public String updateForId (Producto producto,  @RequestParam("img") MultipartFile file) throws IOException {
            LOGGER.info("Producto Actualizado {}",producto);
            Producto p = new Producto();
            p=productoService.get(producto.getId()).get();
            if (file.isEmpty()){//EDITAMOS PERO N QUITAMOS LA IMAGEN

                producto.setImagen(p.getImagen());
            } else{//Cuando se edita tambien la imagen

                //Eliminar imagen cuando no sea la de default
                if (!p.getImagen().equals("default.jpg")){
                    upload.deleteImage(p.getImagen());
                }

                String nombreImagen = upload.saveImage(file);
                producto.setImagen(nombreImagen);

            }
            producto.setUsuario(p.getUsuario());
            productoService.update(producto);
            return "redirect:/productos";
        }


    }
