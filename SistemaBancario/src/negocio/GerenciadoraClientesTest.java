package negocio;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GerenciadoraClientesTest {

    private GerenciadoraClientes gerenciadoraClientes;

    @BeforeEach
    public void setUp() {
        // Inicializa a lista de clientes antes de cada teste
        List<Cliente> clientes = new ArrayList<>();
        gerenciadoraClientes = new GerenciadoraClientes(clientes);
    }

    @Test
    public void testAdicionaCliente() throws IdadeNaoPermitidaException {
        // Dados do novo cliente
        int idCliente = 1;
        String nomeCliente = "Fulano de Tal";
        int idadeCliente = 30;

        // Verifica se é possível adicionar o cliente
        assertTrue(gerenciadoraClientes.validaIdade(idadeCliente));

        // Cria o novo cliente
        Cliente novoCliente = new Cliente(idCliente, nomeCliente, idadeCliente, true);

        // Adiciona o cliente à lista
        gerenciadoraClientes.adicionaCliente(novoCliente);

        // Obtém a lista de clientes do banco
        List<Cliente> clientesDoBanco = gerenciadoraClientes.getClientesDoBanco();

        // Verifica se o cliente foi adicionado corretamente
        assertTrue(clientesDoBanco.contains(novoCliente));
    }

    @Test
    public void testValidaIdade() throws IdadeNaoPermitidaException {
        // Idade válida
        int idadeValida = 30;
        assertTrue(gerenciadoraClientes.validaIdade(idadeValida));

        // Idade inválida (menor que 18)
        int idadeInvalidaMenor = 16;
        assertThrows(IdadeNaoPermitidaException.class, () -> gerenciadoraClientes.validaIdade(idadeInvalidaMenor));

        // Idade inválida (maior que 65)
        int idadeInvalidaMaior = 70;
        assertThrows(IdadeNaoPermitidaException.class, () -> gerenciadoraClientes.validaIdade(idadeInvalidaMaior));
    }
}
