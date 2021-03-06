package br.com.alura.forum;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.forum.domain.Categoria;
import br.com.alura.forum.domain.Cidade;
import br.com.alura.forum.domain.Cliente;
import br.com.alura.forum.domain.Endereco;
import br.com.alura.forum.domain.Estado;
import br.com.alura.forum.domain.ItemPedido;
import br.com.alura.forum.domain.Pagamento;
import br.com.alura.forum.domain.PagamentoComBoleto;
import br.com.alura.forum.domain.PagamentoComCartao;
import br.com.alura.forum.domain.Pedido;
import br.com.alura.forum.domain.Produto;
import br.com.alura.forum.domain.enums.EstadoPagamento;
import br.com.alura.forum.domain.enums.TipoCliente;
import br.com.alura.forum.repositories.CategoriaRepository;
import br.com.alura.forum.repositories.CidadeRepository;
import br.com.alura.forum.repositories.ClienteRepository;
import br.com.alura.forum.repositories.EnderecoRepository;
import br.com.alura.forum.repositories.EstadoRepository;
import br.com.alura.forum.repositories.ItemPedidoRepository;
import br.com.alura.forum.repositories.PagamentoRepository;
import br.com.alura.forum.repositories.PedidoRepository;
import br.com.alura.forum.repositories.ProdutoRepository;

@SpringBootApplication
public class ForumApplication implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public static void main(String[] args) {
        SpringApplication.run(ForumApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Categoria cat1 = new Categoria(null, "Inform??tica");
        Categoria cat2 = new Categoria(null, "Escrit??rio");

        Produto p1 = new Produto(null, "Computador", 2000.00);
        Produto p2 = new Produto(null, "Impressora", 800.00);
        Produto p3 = new Produto(null, "Mouse", 80.00);

        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2));

        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "S??o Paulo");

        Cidade c1 = new Cidade(null, "Uberl??ndia", est1);
        Cidade c2 = new Cidade(null, "S??o Paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);

        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2, c3));

        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

        Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
        cli1.getTelefones().addAll(Arrays.asList("273789456", "99999999"));

        Endereco e1 = new Endereco(null, "Rua FLores", "300", "Apto 303", "Jardim", "81245785", cli1, c1);
        Endereco e2 = new Endereco(null, "Av Matos", "300", "Sala 800", "Jardim", "81245785", cli1, c2);

        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

        clienteRepository.saveAll(Arrays.asList(cli1));
        enderecoRepository.saveAll(Arrays.asList(e1, e2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1 );
        Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 10:32"), cli1, e2 );

        Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6 );
        ped1.setPagamento(pagto1);

        Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null );
        ped2.setPagamento(pagto2);

        cli1.getPedidos().addAll(Arrays.asList(ped1));

        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
        pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));

        ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
        ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
        ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

        ped1.getItens().addAll(Arrays.asList(ip1, ip3));
        ped2.getItens().addAll(Arrays.asList(ip2));

        p1.getItens().addAll(Arrays.asList(ip1));
        p2.getItens().addAll(Arrays.asList(ip3));
        p3.getItens().addAll(Arrays.asList(ip2));

        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

    }

}
