package br.com.ifpe.oxefoodnoite.modelo.acesso;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public Usuario save(Usuario user) {

	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	user.setHabilitado(Boolean.TRUE);
	return repository.save(user);
    }
    
    @Transactional
    public Usuario findByUsername(String username) {

	return repository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
	return repository.findByUsername(username);
    }
}
