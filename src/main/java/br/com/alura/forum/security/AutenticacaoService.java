package br.com.alura.forum.config.security;

import br.com.alura.forum.modelo.Usuario;
import br.com.alura.forum.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    //METODO QUE CHAMA QUANDO CLICA EM LOGIN NA PAGINA, recebendo o username
    //Busca o usuario no banco e checa a senha por memória, por isso nao passa a senha
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(username);

        if(usuario.isPresent()) return usuario.get();

        throw new UsernameNotFoundException("Dados inválidos!");
    }
}
