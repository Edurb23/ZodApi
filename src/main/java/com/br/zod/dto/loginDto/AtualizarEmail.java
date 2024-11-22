package com.br.zod.dto.loginDto;

import jakarta.validation.constraints.NotBlank;

public record AtualizarEmail(

        @NotBlank(message = "Email é obrigatorio")
        String email
) {
}
