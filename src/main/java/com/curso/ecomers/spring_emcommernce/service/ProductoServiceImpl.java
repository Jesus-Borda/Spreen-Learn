package com.curso.ecomers.spring_emcommernce.service;

import com.curso.ecomers.spring_emcommernce.model.Producto;
import com.curso.ecomers.spring_emcommernce.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired// Avisa que estamos haciendo una inyeccion a esta clase un objeto
    private ProductoRepository productoRepository;

    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Optional<Producto> get(Integer id) {
        return productoRepository.findById(id);
    }

    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public void update(Producto producto) {
        productoRepository.save(producto);
        //SAVE=== cuando el  bojeto resive un NULL como id lo que hace es crearlo
        //Pero cuando se lo mandamos con un id que ya existe lo que hace es actualizarlo
    }

    @Override
    public void delete(Integer id) {
        productoRepository.deleteById(id);
    }
}
