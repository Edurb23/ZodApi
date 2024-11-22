package com.br.zod.dto.telefoneDto;

import com.br.zod.dto.usuarioDto.DetalheUsuario;
import com.br.zod.model.Telefone;

public record DetalheTefoneDto(Long id, String numero, String ddd, String descricao, DetalheUsuario usuario) {

    public DetalheTefoneDto(Telefone telefone){
        this(telefone.getId(), telefone.getNumero(),telefone.getDdd(),telefone.getDescricao(), new DetalheUsuario(telefone.getUsuario()));
    }

}
