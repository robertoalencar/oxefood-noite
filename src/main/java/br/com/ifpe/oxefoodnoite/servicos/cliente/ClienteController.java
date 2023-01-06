package br.com.ifpe.oxefoodnoite.servicos.cliente;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.oxefoodnoite.modelo.cliente.Cliente;
import br.com.ifpe.oxefoodnoite.modelo.cliente.ClienteService;
import br.com.ifpe.oxefoodnoite.util.entity.GenericController;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController extends GenericController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody @Valid ClienteRequest request) {
	
	Cliente cliente = clienteService.save(request.buildCliente());
	return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
    }
    
    @ApiOperation(value = "Serviço responsável por listar todos os clientes do sistema.")
    @GetMapping
    public List<Cliente> listarTodos() {

	return clienteService.listarTodos();
    }
    
    @GetMapping("/{id}")
    public Cliente get(@PathVariable Long id) {

	return clienteService.findById(id);
    }
    
    @GetMapping("/porempresa/{chaveEmpresa}")
    public List<Cliente> consultarPorChaveEmpresa(@PathVariable String chaveEmpresa) {
	
	return clienteService.consultarPorChaveEmpresa(chaveEmpresa);
    }
}
