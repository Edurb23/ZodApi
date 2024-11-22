package com.br.zod.dto.usuarioDto;

import com.br.zod.dto.loginDto.DetalheLoginDto;
import com.br.zod.model.Usuario;

import java.time.LocalDate;

public record DetalheUsuario(Long id, String nome, String cpf , String rg , LocalDate dataDeNascimento, DetalheLoginDto login) {

    public DetalheUsuario(Usuario usuario){
        this(usuario.getId(),usuario.getNome(),usuario.getCpf(),usuario.getRg(),usuario.getDataDeNascimento(),new DetalheLoginDto(usuario.getLogin()));
    }
}
