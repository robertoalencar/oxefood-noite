package br.com.ifpe.oxefoodnoite.servicos.empresa;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	Empresa empresa = empresaService.save(request.buildEmpresa());
	return new ResponseEntity<Empresa>(empresa, HttpStatus.CREATED);
    }

}
