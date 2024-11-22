package com.br.zod.controller;

import com.br.zod.dto.authDto.DadosAuthDto;
import com.br.zod.dto.authDto.DadosTokenJwtDto;
import com.br.zod.model.Login;
import com.br.zod.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager manager;


    @PostMapping("/login")
    public ResponseEntity<DadosTokenJwtDto> login(@RequestBody DadosAuthDto dadosAuthDto) {
        System.out.println("Iniciando autenticação para: " + dadosAuthDto.email());
        var token = new UsernamePasswordAuthenticationToken(dadosAuthDto.email(), dadosAuthDto.senha());
        var authentication = manager.authenticate(token);
        System.out.println("Autenticação bem-sucedida para: " + dadosAuthDto.email());
        var tokenJwt = tokenService.generateToken((Login) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJwtDto(tokenJwt));
    }



}
