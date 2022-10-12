package br.com.ifpe.oxefoodnoite.servicos.produto;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.oxefoodnoite.modelo.produto.CategoriaProduto;
import br.com.ifpe.oxefoodnoite.modelo.produto.CategoriaProdutoService;
import br.com.ifpe.oxefoodnoite.util.entity.GenericController;

/**
 * @author Roberto Alencar
 *
 */
@RestController
@RequestMapping("/api/categoriaproduto")
public class CategoriaProdutoController extends GenericController {

    @Autowired
    private CategoriaProdutoService categoriaProdutoService;

    @PostMapping
    public ResponseEntity<CategoriaProduto> save(@RequestBody @Valid CategoriaProdutoRequest request) {
	
	super.validarPreenchimentoChave(request.getChaveEmpresa());
	CategoriaProduto categoriaProduto = categoriaProdutoService.save(request.buildCategoriaProduto());
	return new ResponseEntity<CategoriaProduto>(categoriaProduto, HttpStatus.CREATED);
    }

}
