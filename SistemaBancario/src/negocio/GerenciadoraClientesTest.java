package negocio;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GerenciadoraClientesTest {

    private GerenciadoraClientes gerenciadoraClientes;
    
    private static GerenciadoraClientes inicializaSistemaBancario() {
        // criando lista vazia de contas e clientes
        List<ContaCorrente> contasDoBanco = new ArrayList<>();
        List<Cliente> clientesDoBanco = new ArrayList<>();
        
        // criando e inserindo três contas na lista de contas correntes do banco
        ContaCorrente conta01 = new ContaCorrente(1, 0, true);
        ContaCorrente conta02 = new ContaCorrente(2, 0, true);
        ContaCorrente conta03 = new ContaCorrente(3, 0, true);
        contasDoBanco.add(conta01);
        contasDoBanco.add(conta02);
        contasDoBanco.add(conta03);
        
        // criando três clientes e associando as contas criadas acima a eles
        Cliente cliente01 = new Cliente(1, "Pedro Silva", "111111-11", 18, "pedro@gmail.com", conta01.getId(), true);
        Cliente cliente02 = new Cliente(2, "Maria Lusardo", "222222-22", 25, "maria@gmail.com", conta02.getId(), true);
        Cliente cliente03 = new Cliente(3, "Carlos Cardoso","333333-33", 25, "carlos@gmail.com", conta03.getId(), true);

        // inserindo os clientes criados na lista de clientes do banco
        clientesDoBanco.add(cliente01);
        clientesDoBanco.add(cliente02);
        clientesDoBanco.add(cliente03);
        
        return new GerenciadoraClientes(clientesDoBanco);
    }

    @BeforeEach
    public void setUp() {
        // Obtém a lista de clientes do sistema bancário já inicializada
        List<Cliente> clientesDoBanco = inicializaSistemaBancario().getClientesDoBanco();

        // Inicializa a lista de clientes antes de cada teste com os clientes existentes
        gerenciadoraClientes = new GerenciadoraClientes(clientesDoBanco);
    }
    
    
    // TESTE 1 - TESTE DE CADASTRO DE CLIENTE
    
    @Test
    public void testAdicionaClienteComIdadeValida() throws IdadeNaoPermitidaException {
        // Dados do novo cliente com idade válida
        int idCliente = 4;
        String nomeCliente = "Fulano de Tal";
        String cpfCliente = "111111-12";
        int idadeCliente = 30;
        String emailCliente = "fulanodetal@gmail.com";
        int idContaCorrente = 5;

        // Verifica se é possível adicionar o cliente
        assertTrue(gerenciadoraClientes.validaIdade(idadeCliente));

        // Cria o novo cliente
        Cliente novoCliente = new Cliente(idCliente, nomeCliente, cpfCliente, idadeCliente, emailCliente, idContaCorrente, true);

        // Adiciona o cliente à lista
        gerenciadoraClientes.adicionaCliente(novoCliente);

        // Obtém a lista de clientes do banco
        List<Cliente> clientesDoBanco = gerenciadoraClientes.getClientesDoBanco();

        // Verifica se o cliente foi adicionado corretamente
        assertTrue(clientesDoBanco.contains(novoCliente));
    }

    @Test
    public void testAdicionaClienteComIdadeInvalidaMenor() throws IdadeNaoPermitidaException {
    	// Dados do novo cliente com idade válida
        int idCliente = 5;
        String nomeCliente = "Fulano de Tal";
        String cpfCliente = "111111-12";
        int idadeCliente = 10;
        String emailCliente = "fulanodetal@gmail.com";
        int idContaCorrente = 5;

        // Verifica se é possível adicionar o cliente
        assertTrue(gerenciadoraClientes.validaIdade(idadeCliente));

        // Cria o novo cliente
        Cliente novoCliente = new Cliente(idCliente, nomeCliente, cpfCliente, idadeCliente, emailCliente, idContaCorrente, true);

        // Adiciona o cliente à lista
        gerenciadoraClientes.adicionaCliente(novoCliente);

        // Obtém a lista de clientes do banco
        List<Cliente> clientesDoBanco = gerenciadoraClientes.getClientesDoBanco();

        // Verifica se o cliente foi adicionado corretamente
        assertTrue(clientesDoBanco.contains(novoCliente));
    }

    @Test
    public void testAdicionaClienteComIdadeInvalidaMaior() throws IdadeNaoPermitidaException {
    	// Dados do novo cliente com idade válida
        int idCliente = 6;
        String nomeCliente = "Fulano de Tal";
        String cpfCliente = "111111-12";
        int idadeCliente = 70;
        String emailCliente = "fulanodetal@gmail.com";
        int idContaCorrente = 5;

        // Verifica se é possível adicionar o cliente
        assertTrue(gerenciadoraClientes.validaIdade(idadeCliente));

        // Cria o novo cliente
        Cliente novoCliente = new Cliente(idCliente, nomeCliente, cpfCliente, idadeCliente, emailCliente, idContaCorrente, true);

        // Adiciona o cliente à lista
        gerenciadoraClientes.adicionaCliente(novoCliente);

        // Obtém a lista de clientes do banco
        List<Cliente> clientesDoBanco = gerenciadoraClientes.getClientesDoBanco();

        // Verifica se o cliente foi adicionado corretamente
        assertTrue(clientesDoBanco.contains(novoCliente));
    }
    
    // TESTE 2 - TESTE DE PESQUISA DE CLIENTE
    
    @Test
    public void testPesquisaClienteExistentePorId() {
        // Dados do cliente existente no sistema bancário
        int idClienteExistente = 3;

        // Obtém o cliente pelo ID
        Cliente clienteEncontrado = gerenciadoraClientes.pesquisaCliente(idClienteExistente);

        // Verifica se o cliente foi encontrado corretamente
        assertNotNull(clienteEncontrado);
        assertEquals(idClienteExistente, clienteEncontrado.getId());
    }

    @Test
    public void testPesquisaClienteNaoExistentePorId() {
        // Tenta encontrar um cliente com ID inexistente
        int idClienteInexistente = 999;

        // Tenta obter o cliente pelo ID
        Cliente clienteEncontrado = gerenciadoraClientes.pesquisaCliente(idClienteInexistente);

        // Verifica se o cliente não foi encontrado (deve ser null)
        if (clienteEncontrado == null) {
            fail("Cliente com ID " + idClienteInexistente + " não encontrado.");
        }
    }
    
    @Test
    public void testPesquisaClienteExistentePorCpf() {
        // Dados do cliente existente no sistema bancário
        String cpfClienteExistente = "111111-11";

        // Obtém o cliente pelo ID
        Cliente clienteEncontrado = gerenciadoraClientes.pesquisaCliente(cpfClienteExistente);
        // DEU ERRO PORQUE O METODO PESQUISACLIENTE NÃO ACEITA STRING (CPF) APENAS NUMEROS (ID)

        // Verifica se o cliente foi encontrado corretamente
        assertNotNull(clienteEncontrado);
        assertEquals(cpfClienteExistente, clienteEncontrado.getCpf());
        
    }
    
    //TESTE 3 - ATUALIZAÇÃO DE DADOS DO CLIENTE
    
    @Test
    public void testAtualizarDadosClienteExistente() {
        // Obtém o cliente pelo ID
        int idCliente = 1;
        Cliente cliente = gerenciadoraClientes.pesquisaCliente(idCliente);

        // Verifica se o cliente existe
        assertNotNull(cliente);

        // Atualiza os dados do cliente
        cliente.setNome("Novo Nome");
        cliente.setCpf("222222-22");
        cliente.setIdade(25);
        cliente.setEmail("novoemail@gmail.com");

        // Verifica se os dados foram atualizados corretamente
        assertEquals("Novo Nome", cliente.getNome());
        assertEquals("222222-22", cliente.getCpf());
        assertEquals(25, cliente.getIdade());
        assertEquals("novoemail@gmail.com", cliente.getEmail());
        
     // Verifica se os dados do cliente são exibidos corretamente em string após a desativação
        String expectedString = "========================="
                + "Id: " + cliente.getId() + "\n"
                + "Nome: " + cliente.getNome() + "\n"
                + "CPF: " + cliente.getCpf() + "\n"
                + "Email: " + cliente.getEmail() + "\n"
                + "Idade: " + cliente.getIdade() + "\n"
                + "Status: " + (cliente.isAtivo() ? "Ativo" : "Inativo") + "\n"
                + "=========================";
        assertEquals(expectedString, cliente.toString());
    }
    
    // TESTE 4 - VERIFICA SE É POSSIVEL DESATIVAR UM CLIENTE
    
    @Test
    public void testDesativarClienteExistente() {
        // Obtém o cliente pelo ID
        int idCliente = 1;
        Cliente cliente = gerenciadoraClientes.pesquisaCliente(idCliente);

        // Verifica se o cliente existe
        assertNotNull(cliente);

        // Desativa o cliente
        cliente.setAtivo(false);

        // Verifica se o cliente está realmente desativado
        assertFalse(cliente.isAtivo());
    }
    




}


    
    
    
  
  

    

