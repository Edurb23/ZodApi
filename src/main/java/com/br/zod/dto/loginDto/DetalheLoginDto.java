package com.br.zod.dto.loginDto;

import com.br.zod.model.Login;

public record DetalheLoginDto(Long id, String email , String senha ) {

    public DetalheLoginDto(Login login){
        this(login.getId(), login.getEmail(),login.getSenha());
    }

}
