package br.com.alura.forum.resorces;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.domain.Categoria;
import br.com.alura.forum.services.CategoriaService;

@RestController()
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    @GetMapping("/categorias/{id}")
    public ResponseEntity<?> buscar(@PathVariable Integer id){
      Categoria obj = service.buscar(id);
      return ResponseEntity.ok().body(obj);
   }
}
