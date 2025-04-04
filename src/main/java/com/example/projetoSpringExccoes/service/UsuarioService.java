package com.example.projetoSpringExccoes.service;

import com.example.projetoSpringExccoes.model.Usuario;
import com.example.projetoSpringExccoes.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class UsuarioService {
    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario salvar(@Valid Usuario usuario){
        if(usuarioRepository.findByEmail(usuario.getEmail()).isPresent()){
            throw new RuntimeException("Usuario jÃ¡ cadastrado");
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizar(@Valid Usuario usuario){
        Usuario usuarioAtualizar = usuarioRepository.findById(usuario.getId())
                .orElseThrow(()-> new RuntimeException("Usuario nÃ£o encontrado"));

        usuarioAtualizar.setNome(usuario.getNome());
        usuarioAtualizar.setEmail(usuario.getEmail());
        usuarioAtualizar.setSenha(usuario.getSenha());

        return usuarioRepository.save(usuarioAtualizar);
    }

    public void  excluir(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Usuario jÃ¡ excluido ou inexistente "));

        usuarioRepository.deleteById(usuario.getId());
    }


}
