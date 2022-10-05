package br.com.ifpe.oxefoodnoite.modelo.produto;

import br.com.ifpe.oxefoodnoite.util.entity.EntidadeAuditavel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaProduto extends EntidadeAuditavel {

    private static final long serialVersionUID = 6412339934684071773L;

    private String chaveEmpresa;

    private String descricao;
    
}
