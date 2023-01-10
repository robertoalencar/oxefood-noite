package br.com.ifpe.oxefoodnoite.modelo.cliente;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import br.com.ifpe.oxefoodnoite.modelo.acesso.Usuario;
import br.com.ifpe.oxefoodnoite.util.entity.EntidadeAuditavel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Roberto Alencar
 *
 */
@Entity
@Table(name = "Cliente")
@Where(clause = "habilitado = true")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends EntidadeAuditavel {
    
    private static final long serialVersionUID = 7830960733588385306L;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;
    
    @Column(nullable = false, length = 100)
    private String nome;
    
    @Column
    private LocalDate dataNascimento;
    
    @Column(unique = true)
    private String cpf;
    
    @Column
    private String fone;
    
    @Column
    private String foneAlternativo;

}
