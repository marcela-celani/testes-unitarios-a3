package negocio;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MainTest {
	
	//TESTE 4 -  DESATIVAÇÃO DE CLIENTE

    private GerenciadoraClientes gerClientes;

    @BeforeEach
    public void setUp() {
        // Inicializa a lista de clientes antes de cada teste
        List<Cliente> clientes = new ArrayList<>();
        gerClientes = new GerenciadoraClientes(clientes);

        // Adiciona um cliente para testar a desativação
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

    @Test
    public void testDesativarClienteExistente() {
        // Obtém o cliente pelo ID
        int idCliente = 1;
        Cliente cliente = gerClientes.pesquisaCliente(idCliente);

        // Verifica se o cliente existe
        assertNotNull(cliente);

        // Desativa o cliente
        cliente.setAtivo(false);

        // Verifica se o cliente está realmente desativado
        assertFalse(cliente.isAtivo());
    }

    
    
    
    //TESTE 3 - ATUALIZAÇÃO DE DADOS (!!)

        @Test
        public void testAtualizarDadosClienteExistente() {
            // Obtém o cliente pelo ID
            int idCliente = 1;
            Cliente cliente = gerClientes.pesquisaCliente(idCliente);

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
        }

     
    }

    
    
    
    



   





