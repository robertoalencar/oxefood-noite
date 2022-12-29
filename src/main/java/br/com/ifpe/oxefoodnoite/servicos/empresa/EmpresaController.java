package br.com.ifpe.oxefoodnoite.servicos.empresa;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.oxefoodnoite.modelo.acesso.Usuario;
import br.com.ifpe.oxefoodnoite.modelo.empresa.Empresa;
import br.com.ifpe.oxefoodnoite.modelo.empresa.EmpresaService;
import br.com.ifpe.oxefoodnoite.util.entity.GenericController;

@RestController
@RequestMapping("/api/empresa")
public class EmpresaController extends GenericController {

    @Autowired
    private EmpresaService empresaService;

    @PostMapping
    public ResponseEntity<Empresa> save(@RequestBody @Valid EmpresaRequest request) {

	Empresa empresa = request.buildEmpresa();
	
	if (request.getPerfil() != null && !"".equals(request.getPerfil())) {
	    if (request.getPerfil().equals("Usuario")) {
		empresa.getUsuario().getRoles().add(Usuario.ROLE_EMPRESA_USER);
	    } else if (request.getPerfil().equals("Admin")) {
		empresa.getUsuario().getRoles().add(Usuario.ROLE_EMPRESA_ADMIN);
	    }
	}
	
	Empresa empresaCriada = empresaService.save(empresa);
	return new ResponseEntity<Empresa>(empresaCriada, HttpStatus.CREATED);
    }

}
