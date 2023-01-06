package br.com.ifpe.oxefoodnoite.util.entity;

import org.springframework.web.bind.annotation.CrossOrigin;

import br.com.ifpe.oxefoodnoite.util.exception.PreenchimentoException;

@CrossOrigin
public class GenericController {

    protected void validarPreenchimentoChave(String chave) {

	if (chave == null || chave.equals("")) {
	    throw new PreenchimentoException(PreenchimentoException.MSG_CHAVE_NAO_INFORMADA);
	}
    }
}

