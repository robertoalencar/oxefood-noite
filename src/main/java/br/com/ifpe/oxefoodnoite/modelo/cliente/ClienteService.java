package br.com.ifpe.oxefoodnoite.modelo.cliente;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.oxefoodnoite.modelo.acesso.UsuarioService;
import br.com.ifpe.oxefoodnoite.modelo.mensagens.EmailService;
import br.com.ifpe.oxefoodnoite.util.entity.GenericService;
import br.com.ifpe.oxefoodnoite.util.exception.EntidadeNaoEncontradaException;

@Service
public class ClienteService extends GenericService {

    @Autowired
    private ClienteRepository repository;
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Transactional
    public Cliente save(Cliente cliente) {
	
	usuarioService.save(cliente.getUsuario());

	super.preencherCamposAuditoria(cliente);
	Cliente clienteSaldo = repository.save(cliente);
	
	//emailService.enviarEmailConfirmacaoCadastroCliente(clienteSaldo);
	
	return clienteSaldo;
    }
    
    @Transactional
    public Cliente findById(Long id) {

	Optional<Cliente> consulta = repository.findById(id);
	
	if (consulta.isPresent()) {
	    return consulta.get();
	} else {
	    throw new EntidadeNaoEncontradaException("Cliente", id);
	}

    }
    
    @Transactional
    public List<Cliente> listarTodos() {

	return repository.findAll();
    }

    @Transactional
    public List<Cliente> consultarPorChaveEmpresa(String chaveEmpresa) {

	return repository.findByChaveEmpresaOrderByNomeAsc(chaveEmpresa);
    }
}
