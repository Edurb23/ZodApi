package com.br.zod.dto.loginDto;

import jakarta.validation.constraints.NotBlank;

public record NovoLogin(


        @NotBlank(message = "Email é obrigatorio")
        String email,
        @NotBlank(message = "Senha é obrigatorio")
       String senha
) {
}
