package br.com.ifpe.oxefoodnoite.servicos.acesso;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.oxefoodnoite.modelo.acesso.Usuario;
import br.com.ifpe.oxefoodnoite.modelo.acesso.UsuarioService;
import br.com.ifpe.oxefoodnoite.security.jwt.JwtTokenProvider;
import br.com.ifpe.oxefoodnoite.util.entity.GenericController;

@RestController
@RequestMapping("/api/login")
public class AuthenticationController extends GenericController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.OK)
    public Map<Object, Object> signin(@RequestBody AuthenticationRequest data) {

	try {
	    return doLoginAppCliente(data);
	} catch (AuthenticationException e) {
	    throw new BadCredentialsException("Invalid username/password supplied");
	}
    }
    
    private Map<Object, Object> doLoginAppCliente(AuthenticationRequest data) {
	
	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword()));
	return doLogin(this.usuarioService.findByUsername(data.getUsername()));
    }
    
    private Map<Object, Object> doLogin(Usuario usuario) {
	
	String token = jwtTokenProvider.createToken(usuario.getUsername(), usuario.getRoles());

	String refreshToken = jwtTokenProvider.createRefreshToken(usuario.getUsername());

	Map<Object, Object> model = new HashMap<>();
	model.put("username", usuario.getUsername());
	model.put("token", token);
	model.put("refresh", refreshToken);

	return model;
    }
    
}