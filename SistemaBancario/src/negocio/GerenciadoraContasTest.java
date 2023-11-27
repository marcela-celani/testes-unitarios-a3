package negocio;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GerenciadoraContasTest {

    private GerenciadoraContas gerContas;
    private GerenciadoraClientes gerClientes;

    private void inicializaSistemaBancario() {
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
        Cliente cliente03 = new Cliente(3, "Carlos Cardoso", "333333-33", 25, "carlos@gmail.com", conta03.getId(), true);

        // inserindo os clientes criados na lista de clientes do banco
        clientesDoBanco.add(cliente01);
        clientesDoBanco.add(cliente02);
        clientesDoBanco.add(cliente03);

        gerClientes = new GerenciadoraClientes(clientesDoBanco);
        gerContas = new GerenciadoraContas(contasDoBanco);
    }
    
    @BeforeEach
    public void setUp() {
        // Inicializa a lista de contas e clientes antes de cada teste
        inicializaSistemaBancario();
    }

    // TESTE 1 - TESTE DE ABERTURA DE CONTA

    @Test
    public void testAbrirNovaContaParaClienteExistente() {
    	// Tenta abrir uma nova conta para um cliente existente
        ContaCorrente conta04 = new ContaCorrente(4, 0, true);
        
        // Adiciona a nova conta corrente ao cliente existente
        gerContas.adicionaConta(conta04);
        
        // Verifica se a conta foi adicionada corretamente
        ContaCorrente contaAdicionada = gerContas.pesquisaConta(4);
        assertNotNull(contaAdicionada);
        assertEquals(4, contaAdicionada.getId()); // Verifica se o ID da conta está correto
        assertTrue(contaAdicionada.isAtiva()); // Verifica se a conta está ativa    
        
        // VERIFICA SE O SALDO DA CONTA CRIADA É 0
        assertEquals(0, contaAdicionada.getSaldo());
        
        // Cria um novo cliente associado à conta criada
        Cliente cliente04 = new Cliente(4, "Carlos Cardoso", "333333-33", 25, "carlos@gmail.com", conta04.getId(), true);
        
        // Adiciona o novo cliente à lista de clientes do banco
        gerClientes.adicionaCliente(cliente04);
        
        // Verifica se o penúltimo parâmetro do cliente (id da conta corrente) é igual a 4
        assertEquals(4, cliente04.getIdContaCorrente());        
    }
    
    // TESTE 2 - TESTE DE CONSULTA DE CONTA
    @Test
    public void testConsultarContaExistentePeloNumero() {
        // ID de uma conta existente no sistema
        int idContaExistente = 1;

        // Tenta consultar a conta existente pelo número da conta na instância atualizada
        ContaCorrente contaConsultada = gerContas.pesquisaConta(idContaExistente);

        // Verifica se a consulta retornou a conta correta
        assertNotNull(contaConsultada);
    }

    @Test
    public void testConsultarContaInexistentePeloNumero() {
        // Tenta consultar uma conta que não existe
        ContaCorrente contaConsultada = gerContas.pesquisaConta(999);

        // Verifica se a consulta retorna null para conta inexistente
        assertNull(contaConsultada);
    }
    
    // TESTE 2 - VISUALIZAÇÃO DOS DADOS DA CONTA
    @Test
    public void testExibirDadosDaContaCorrente() {
        // Cria uma nova conta corrente para teste
        ContaCorrente contaTeste = new ContaCorrente(10, 100.0, true);
        
        Cliente cliente04 = new Cliente(4, "Carlos Cardoso", "333333-33", 25, "carlos@gmail.com", contaTeste.getId(), true);
        
        // Adiciona o novo cliente à lista de clientes do banco
        gerClientes.adicionaCliente(cliente04);

        // Converte a conta para uma representação de string
        String dadosConta = contaTeste.toString();

        // Verifica se a string gerada contém as informações esperadas
        assertTrue(dadosConta.contains("Id: 10"));
        assertTrue(dadosConta.contains("Saldo: 100.0"));
        assertTrue(dadosConta.contains("Status: Ativa"));
    }
    
    @Test
    public void testExibirDadosDaContaCorrenteComCPF() {
        // Cria uma nova conta corrente para teste
        ContaCorrente contaTeste = new ContaCorrente(10, 100.0, true);
        
        Cliente cliente04 = new Cliente(4, "Carlos Cardoso", "333333-33", 25, "carlos@gmail.com", contaTeste.getId(), true);
        
        // Adiciona o novo cliente à lista de clientes do banco
        gerClientes.adicionaCliente(cliente04);

        // Converte a conta para uma representação de string
        String dadosConta = contaTeste.toString();

        // Verifica se a string gerada contém as informações esperadas
        assertTrue(dadosConta.contains("Id: 10"));
        assertTrue(dadosConta.contains("Saldo: 100.0"));
        assertTrue(dadosConta.contains("Status: Ativa"));
        
        // A CONTA CORRENTE NAO POSSUI O DADO DO CPF DO CLIENTE
        assertTrue(dadosConta.contains("CPF: 333333-33"));
    }

    // TESTE 3 - DESATIVAÇAO DE CONTA
    @Test
    
    public void testDesativarConta() {
        // Cria uma nova conta com saldo inicial de 100.0 e ativa
        ContaCorrente contaTeste = new ContaCorrente(10, 100.0, true);

        // Adiciona a nova conta corrente ao gerenciador de contas
        gerContas.adicionaConta(contaTeste);

        // Tenta consultar a conta recém-criada pelo número da conta
        ContaCorrente contaConsultada = gerContas.pesquisaConta(contaTeste.getId());
        
        assertNotNull(contaConsultada);
        contaConsultada.setAtiva(false);
        assertFalse(contaConsultada.isAtiva());
    }
    
    @Test
    public void testDesativarContaEVerificarDados() {
        // Cria uma nova conta com saldo inicial de 100.0 e ativa
        ContaCorrente contaTeste = new ContaCorrente(10, 100.0, true);

        // Adiciona a nova conta corrente ao gerenciador de contas
        gerContas.adicionaConta(contaTeste);

        // Tenta consultar a conta recém-criada pelo número da conta
        ContaCorrente contaConsultada = gerContas.pesquisaConta(contaTeste.getId());

        assertNotNull(contaConsultada);

        // Desativa a conta
        contaConsultada.setAtiva(false);
        
        assertFalse(contaConsultada.isAtiva());

        // Verifica se a representação textual da conta está correta após a desativação
        String expectedRepresentation = "========================="
                + "Id: " + contaConsultada.getId() + "\n"
                + "Saldo: " + contaConsultada.getSaldo() + "\n"
                + "Status: Inativa" + "\n"
                + "=========================";

        assertEquals(expectedRepresentation, contaConsultada.toString());
    }
 
}









