package com.br.zod.controller;


import com.br.zod.dto.loginDto.DetalheLoginDto;
import com.br.zod.dto.loginDto.NovoLogin;
import com.br.zod.dto.telefoneDto.CadastroTelefoneDto;
import com.br.zod.dto.telefoneDto.DetalheTefoneDto;
import com.br.zod.dto.usuarioDto.CadastroUsuarioDto;
import com.br.zod.dto.usuarioDto.DetalheUsuario;
import com.br.zod.model.Login;
import com.br.zod.model.Telefone;
import com.br.zod.model.Usuario;
import com.br.zod.repository.LoginRepository;
import com.br.zod.repository.TelefoneRepository;
import com.br.zod.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("register")
public class RegisterController {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TelefoneRepository telefoneRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    @Transactional
    public ResponseEntity<DetalheLoginDto> create(@RequestBody @Valid NovoLogin dto , UriComponentsBuilder builder){
        var login = new Login(dto.email(), passwordEncoder.encode(dto.senha()));
        loginRepository.save(login);
        var uri = builder.path("/login/id").buildAndExpand(login.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalheLoginDto(login));
    }

    @PostMapping("login/{id}/userinfo")
    @Transactional
    public ResponseEntity<DetalheUsuario> create(@PathVariable("id") Long id,
                                                 @RequestBody @Valid CadastroUsuarioDto dto, UriComponentsBuilder builder){
        var login = loginRepository.getReferenceById(id);
        var usuario = new Usuario(dto, login);
        usuarioRepository.save(usuario);
        var uri = builder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalheUsuario(usuario));
    }

    @PostMapping("usuario/{id}/telefone")
    @Transactional
    public ResponseEntity<DetalheTefoneDto> create(@PathVariable("id") Long id,
                                                   @RequestBody @Valid CadastroTelefoneDto dto, UriComponentsBuilder builder ){
        var usuario = usuarioRepository.getReferenceById(id);
        var telefone = new Telefone(dto, usuario);
        telefoneRepository.save(telefone);
        var uri = builder.path("/telefone/{id}").buildAndExpand(telefone.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalheTefoneDto(telefone));
    }






}
