package br.com.ifpe.oxefoodnoite.util.entity;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class EntidadeAuditavel extends EntidadeNegocio {

    private static final long serialVersionUID = 6904895862301655231L;

    private Long versao;

    private LocalDate dataCriacao;

    private LocalDate dataUltimaModificacao;

    private Long criadoPor; // Id do usuário que o criou

    private Long ultimaModificacaoPor; // Id do usuário que fez a última alteração

}
