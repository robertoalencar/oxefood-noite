package br.com.ifpe.oxefoodnoite.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.ifpe.oxefoodnoite.modelo.acesso.Usuario;
import br.com.ifpe.oxefoodnoite.modelo.acesso.UsuarioService;
import br.com.ifpe.oxefoodnoite.security.jwt.JwtAuthenticationEntryPoint;
import br.com.ifpe.oxefoodnoite.security.jwt.JwtTokenAuthenticationFilter;
import br.com.ifpe.oxefoodnoite.security.jwt.JwtTokenProvider;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    private static final String[] AUTH_WHITELIST = {
		"/swagger-resources/**",
		"/swagger-ui.html",
		"/swagger*/**",
		"/v2/api-docs",
		"/webjars/**",
		"/routes/**",
		"/favicon.ico",
		"/ws/**"
    };
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private JwtAuthenticationEntryPoint authenticationEntryPoint;
    
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder,
	    UsuarioService userDetailService) throws Exception {
	
	return http.getSharedObject(AuthenticationManagerBuilder.class)
		.userDetailsService(userDetailService)
		.passwordEncoder(bCryptPasswordEncoder)
		.and()
		.build();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	
	 http
	 	.httpBasic().disable().csrf().disable().cors().and().sessionManagement()
	 	.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling()
	 	.authenticationEntryPoint(authenticationEntryPoint).and().authorizeRequests()

	 	.antMatchers(AUTH_WHITELIST).permitAll()
	 	.antMatchers("/api/cliente").permitAll()
	 	.antMatchers(HttpMethod.POST, "/api/empresa").permitAll()
	 	.antMatchers(HttpMethod.POST, "/api/login/signin").permitAll()
	 	
	 	//Configuração de autorizações de acesso para Categoria de Produto
	 	
	 	.antMatchers(HttpMethod.POST, "/api/categoriaproduto").hasAnyAuthority(Usuario.ROLE_EMPRESA_ADMIN, Usuario.ROLE_EMPRESA_USER) //Cadastro de produto
	 	.antMatchers(HttpMethod.PUT, "/api/categoriaproduto/*").hasAnyAuthority(Usuario.ROLE_EMPRESA_ADMIN) //Alteração de produto
	 	.antMatchers(HttpMethod.DELETE, "/api/categoriaproduto").hasAnyAuthority(Usuario.ROLE_EMPRESA_ADMIN) //Exclusão de produto
	 	.antMatchers(HttpMethod.GET, "/api/categoriaproduto/").hasAnyAuthority(Usuario.ROLE_CLIENTE, Usuario.ROLE_EMPRESA_ADMIN, Usuario.ROLE_EMPRESA_USER) //Consulta de produto
        
        	.anyRequest().hasAnyAuthority(Usuario.ROLE_CLIENTE, Usuario.ROLE_EMPRESA_ADMIN, Usuario.ROLE_EMPRESA_USER)
        	.and().addFilterBefore(
        		new JwtTokenAuthenticationFilter(jwtTokenProvider),
        		UsernamePasswordAuthenticationFilter.class);
	 
	 return http.build();
    }
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {

	return new WebMvcConfigurer() {
	    @Override
	    public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*");
	    }
	};
    }
    
}