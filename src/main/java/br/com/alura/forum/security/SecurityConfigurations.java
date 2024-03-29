package br.com.alura.forum.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    @Autowired
    private AutenticacaoService autenticacaoService;

    //CONFIGURA POR AUTENTICAÇAO -> CONTROLE ACESSO, LOGIN, ETC
    //Usado para configurar a página de login do Spring Security
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    //CONFIGURA POR AUTORIZACAO -> URL, PERFIL DE ACESSO....
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Permite apenas os endereços listados com permit conforme abaixo
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/topicos").permitAll()
                .antMatchers(HttpMethod.GET, "/topicos/*").permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
        ;
    }

    //CONFIGURACOES DE RECURSOS ESTATICOS - FRONTEND, ACESSO A ARQUIVOS, IMAGENS...
    @Override
    public void configure(WebSecurity web) throws Exception {
    }

    /*
    //Metodo para usar o bcrypt para gerar uma senha encripitada e colocar no banco
    public static void main(String[] args){
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }
    */
}
