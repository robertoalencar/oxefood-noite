package br.com.ifpe.oxefoodnoite.modelo.cliente;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.oxefoodnoite.util.entity.GenericService;

@Service
public class ClienteService extends GenericService {

    @Autowired
    private ClienteRepository repository;
    
    @Transactional
    public Cliente save(Cliente cliente) {

	super.preencherCamposAuditoria(cliente);
	return repository.save(cliente);
    }
    
    @Transactional
    public Cliente findById(Long id) {

	return repository.findById(id).get();
    }

    @Transactional
    public List<Cliente> consultarPorChaveEmpresa(String chaveEmpresa) {

	return repository.findByChaveEmpresaOrderByNomeAsc(chaveEmpresa);
    }
}
