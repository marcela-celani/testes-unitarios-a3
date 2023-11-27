package negocio;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GerenciadoraContasTest {

    private GerenciadoraContas gerContas;
    private GerenciadoraClientes gerClientes;

    @BeforeEach
    public void setUp() {
        // Inicializa a lista de contas antes de cada teste
        List<ContaCorrente> contas = new ArrayList<>();
        // Não passa a instância de gerClientes no construtor de GerenciadoraContas
        gerContas = new GerenciadoraContas(contas);

        // Inicializa a lista de clientes antes de cada teste
        List<Cliente> clientes = new ArrayList<>();
        gerClientes = new GerenciadoraClientes(clientes);

        // Adiciona um cliente para testar a abertura de conta associada
        int idCliente = 1;
        String nomeCliente = "Pedro Silva";
        String cpfCliente = "111111-11";
        int idadeCliente = 18;
        String emailCliente = "pedro@gmail.com";
        int idContaCorrente = 1; // Id da conta associada
        boolean clienteAtivo = true;

        Cliente cliente = new Cliente(idCliente, nomeCliente, cpfCliente, idadeCliente, emailCliente, idContaCorrente, clienteAtivo);
        gerClientes.adicionaCliente(cliente);
    }

    // Restante do código do teste...




    @Test
    public void testAbrirNovaContaParaClienteExistente() {
        // Tenta abrir uma nova conta para um cliente existente
        int idClienteExistente = 1;
        boolean aberturaConta = gerContas.abrirNovaConta(idClienteExistente);

        // Verifica se a abertura da conta ocorreu corretamente
        assertTrue(aberturaConta);
    }

 
}









