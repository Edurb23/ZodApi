package com.br.zod.dto.dispositivo;

import jakarta.validation.constraints.NotBlank;

public record NovoDispositivoDto(

        @NotBlank(message = "nome de dispositivo é obrigatorio")
        String nome ,
        @NotBlank(message = "Descrição de dispositivo é obrigatorio")
        String descricao,
        @NotBlank(message = "Watts de dispositivo é obrigatorio")
        String watts,

        char status


) {
}
