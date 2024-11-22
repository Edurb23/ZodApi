package com.br.zod.dto.usuarioDto;

import java.time.LocalDate;

import com.br.zod.model.Login;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record CadastroUsuarioDto(



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
