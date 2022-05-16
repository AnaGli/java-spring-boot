package br.com.alura.forum.resorces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.domain.Cliente;
import br.com.alura.forum.services.ClienteService;

@RestController()
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @GetMapping("/clientes/{id}")
    public ResponseEntity<?> buscar(@PathVariable Integer id){
      Cliente obj = service.buscar(id);
      return ResponseEntity.ok().body(obj);
   }
}
