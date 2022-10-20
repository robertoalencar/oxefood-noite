package br.com.ifpe.oxefoodnoite.servicos.produto;

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

import br.com.ifpe.oxefoodnoite.modelo.produto.CategoriaProduto;
import br.com.ifpe.oxefoodnoite.modelo.produto.CategoriaProdutoService;
import br.com.ifpe.oxefoodnoite.util.entity.GenericController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Roberto Alencar
 *
 */
@RestController
@RequestMapping("/api/categoriaproduto")
public class CategoriaProdutoController extends GenericController {

    @Autowired
    private CategoriaProdutoService categoriaProdutoService;

    @ApiOperation(value = "Serviço responsável por salvar uma categoria de produto no sistema.")
    @PostMapping
    public ResponseEntity<CategoriaProduto> save(@RequestBody @Valid CategoriaProdutoRequest request) {
	
	super.validarPreenchimentoChave(request.getChaveEmpresa());
	CategoriaProduto categoriaProduto = categoriaProdutoService.save(request.buildCategoriaProduto());
	return new ResponseEntity<CategoriaProduto>(categoriaProduto, HttpStatus.CREATED);
    }
    
    @ApiOperation(value = "Serviço responsável por obter uma categoria de produto referente ao Id passado na URL.")
    @ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna a categoria de produto."),
	    @ApiResponse(code = 401, message = "Acesso não autorizado."),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
	    @ApiResponse(code = 404, message = "Não foi encontrado um registro para o Id informado."),
	    @ApiResponse(code = 500, message = "Foi gerado um erro no servidor."),
    })

    @GetMapping("/{id}")
    public CategoriaProduto get(@PathVariable Long id) {

	return categoriaProdutoService.findById(id);
    }
    
    @GetMapping("/porempresa/{chaveEmpresa}")
    public List<CategoriaProduto> consultarPorChaveEmpresa(@PathVariable String chaveEmpresa) {
	
	return categoriaProdutoService.consultarPorChaveEmpresa(chaveEmpresa);
    }


}
