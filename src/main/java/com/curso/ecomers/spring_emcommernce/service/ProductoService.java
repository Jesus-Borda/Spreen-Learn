package com.curso.ecomers.spring_emcommernce.service;

import com.curso.ecomers.spring_emcommernce.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    public Producto save (Producto producto);
    public Optional<Producto> get(Integer id);
    public List<Producto> findAll();
    //Retornamos un  Optional por qu primero valida si el objeto que llamamos
    //De la base de datos existe o no.
    public void update(Producto producto);
    public void delete(Integer id);
}
