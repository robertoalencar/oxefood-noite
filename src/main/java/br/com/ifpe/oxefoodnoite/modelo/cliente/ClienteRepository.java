package br.com.ifpe.oxefoodnoite.modelo.cliente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClienteRepository extends JpaRepository<Cliente, Long>, JpaSpecificationExecutor<Cliente> {

    /*
    	@Query(value = "SELECT c FROM Cliente c WHERE c.chaveEmpresa = :chaveEmpresa ORDER BY c.nome ASC")
    	List<Cliente> listarCliente(String chaveEmpresa);
    */
    
    List<Cliente> findByChaveEmpresaOrderByNomeAsc(String chaveEmpresa);
    
}
