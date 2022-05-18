package br.com.alura.forum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.forum.domain.Pagamento;
import br.com.alura.forum.domain.Pedido;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
