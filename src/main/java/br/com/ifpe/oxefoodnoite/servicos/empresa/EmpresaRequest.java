package br.com.ifpe.oxefoodnoite.servicos.empresa;

import br.com.ifpe.oxefoodnoite.modelo.acesso.Usuario;
import br.com.ifpe.oxefoodnoite.modelo.empresa.Empresa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaRequest {

    private String chave;

    private String site;

    private String cnpj;

    private String inscricaoEstadual;

    private String nomeEmpresarial;

    private String nomeFantasia;

    private String fone;

    private String foneAlternativo;
    
    private String email;

    private String password;
    
    private String perfil;
    
    public Usuario buildUsuario() {
	
	Usuario usuario = Usuario.builder()
		.username(email)
		.password(password)
		.build();
	
	return usuario;
    }

    
    public Empresa buildEmpresa() {

	Empresa empresa = Empresa.builder()
		.chave(chave)
		.site(site)
		.usuario(buildUsuario())
		.cnpj(cnpj)
		.inscricaoEstadual(inscricaoEstadual)
		.nomeEmpresarial(nomeEmpresarial)
		.nomeFantasia(nomeFantasia)
		.fone(fone)
		.foneAlternativo(foneAlternativo)
		.build();
	
	return empresa;
    }

}
