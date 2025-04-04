package com.example.projetoSpringExccoes.controller;

import com.example.projetoSpringExccoes.model.Usuario;
import com.example.projetoSpringExccoes.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
public class Usuariocontroller {
    private UsuarioService usuarioService;

    public Usuariocontroller(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }



    @GetMapping
    public List<Usuario> listartodos(){
        return usuarioService.listarUsuarios();
    }


    @PostMapping
    public ResponseEntity<Map<String, Object>> salvar(@Valid @RequestBody Usuario usuario){
        usuarioService.salvar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("Mensagem","Usuario cadastrado com sucesso"));

    }


    @PutMapping
    public ResponseEntity<Map<String, Object>> atualizar(@Valid @RequestBody Usuario usuario){
        usuarioService.atualizar(usuario);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("mensagem","usuario atualidao "));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> excluir(@PathVariable Long id){
        usuarioService.excluir(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("mensagem","Usuario deletado com sucesso"));

    }

}
