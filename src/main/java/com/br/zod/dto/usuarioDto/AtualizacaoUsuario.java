package com.br.zod.dto.usuarioDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record AtualizacaoUsuario(

        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "CPF é obrigatório")
        String cpf,

        @NotBlank(message = "RG é obrigatório")
        String rg,

        @Past
        LocalDate dataDeNascimento


) {
}
