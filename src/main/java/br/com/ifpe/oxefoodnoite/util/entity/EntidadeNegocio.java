package br.com.ifpe.oxefoodnoite.util.entity;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = { "id" })
public abstract class EntidadeNegocio implements Serializable {

    private static final long serialVersionUID = -8164525212135821909L;

    private Long id;

    private Boolean habilitado;

}
