package com.nelioalves.cursomcapi;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.nelioalves.cursomcapi.domain.Categoria;
import com.nelioalves.cursomcapi.domain.Cidade;
import com.nelioalves.cursomcapi.domain.Cliente;
import com.nelioalves.cursomcapi.domain.Endereco;
import com.nelioalves.cursomcapi.domain.Estado;
import com.nelioalves.cursomcapi.domain.ItemPedido;
import com.nelioalves.cursomcapi.domain.Pagamento;
import com.nelioalves.cursomcapi.domain.PagamentoComBoleto;
import com.nelioalves.cursomcapi.domain.PagamentoComCartao;
import com.nelioalves.cursomcapi.domain.Pedido;
import com.nelioalves.cursomcapi.domain.Produto;
import com.nelioalves.cursomcapi.domain.enums.EstadoPagamento;
import com.nelioalves.cursomcapi.domain.enums.TipoCliente;
import com.nelioalves.cursomcapi.repositories.CategoriaRepository;
import com.nelioalves.cursomcapi.repositories.CidadeRepository;
import com.nelioalves.cursomcapi.repositories.ClienteRepository;
import com.nelioalves.cursomcapi.repositories.EnderecoRepository;
import com.nelioalves.cursomcapi.repositories.EstadoRepository;
import com.nelioalves.cursomcapi.repositories.ItemPedidoRepository;
import com.nelioalves.cursomcapi.repositories.PagamentoRepository;
import com.nelioalves.cursomcapi.repositories.PedidoRepository;
import com.nelioalves.cursomcapi.repositories.ProdutoRepository;

@SpringBootApplication
public class MyApiApplication implements CommandLineRunner {

    private CategoriaRepository categoriaRepository;
    private ProdutoRepository produtoRepository;
    private CidadeRepository cidadeRepository;
    private EstadoRepository estadoRepository;
    private ClienteRepository clienteRepository;
    private EnderecoRepository enderecoRepository;
    private PedidoRepository pedidoRepository;
    private PagamentoRepository pagamentoRepository;
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    public MyApiApplication(CategoriaRepository categoriaRepository,
                            ProdutoRepository produtoRepository,
                            CidadeRepository cidadeRepository,
                            EstadoRepository estadoRepository,
                            ClienteRepository clienteRepository,
                            EnderecoRepository enderecoRepository,
                            PedidoRepository pedidoRepository,
                            PagamentoRepository pagamentoRepository,
                            ItemPedidoRepository itemPedidoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.produtoRepository = produtoRepository;
        this.cidadeRepository = cidadeRepository;
        this.estadoRepository = estadoRepository;
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
        this.pedidoRepository = pedidoRepository;
        this.pagamentoRepository = pagamentoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(MyApiApplication.class, args);
    }

    /*Executa alguma ação quando a aplicação iniciar*/
    @Override
    public void run(String... arg0) throws Exception {
        Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "Escritório");
        Categoria cat3 = new Categoria(null, "Cama mesa e banho");
        Categoria cat4 = new Categoria(null, "Eletrônicos");
        Categoria cat5 = new Categoria(null, "Jardinagem");
        Categoria cat6 = new Categoria(null, "Decoração");
        Categoria cat7 = new Categoria(null, "Perfumaria");

        Produto p1 = new Produto(null, "Computador", 2000.00);
        Produto p2 = new Produto(null, "Impressora", 800.00);
        Produto p3 = new Produto(null, "Mouse", 80.00);

        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2));

        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "São Paulo");

        Cidade c1 = new Cidade(null, "Uberlândia", est1);
        Cidade c2 = new Cidade(null, "São Paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);

        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2, c3));

        Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);

        cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

        Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
        Endereco e2 = new Endereco(null, "Av Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);

        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
        Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

        Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pagto1);

        Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
        ped2.setPagamento(pagto2);

        cli1.setPedidos(Arrays.asList(ped1, ped2));
        
        ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
        ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
        ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
        
        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().addAll(Arrays.asList(ip3));
        
        p1.getItens().addAll(Arrays.asList(ip1));
        p2.getItens().addAll(Arrays.asList(ip3));
        p3.getItens().addAll(Arrays.asList(ip2));

        categoriaRepository.save(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        produtoRepository.save(Arrays.asList(p1, p2, p3));
        estadoRepository.save(Arrays.asList(est1, est2));
        cidadeRepository.save(Arrays.asList(c1, c2, c3 ));
        clienteRepository.save(cli1);
        enderecoRepository.save(Arrays.asList(e1, e2));
        pedidoRepository.save(Arrays.asList(ped1, ped2));
        pagamentoRepository.save(Arrays.asList(pagto1, pagto2));
        itemPedidoRepository.save(Arrays.asList(ip1, ip2, ip3));
        
    }
}
