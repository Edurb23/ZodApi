package com.br.zod.dto.dispositivo;

import com.br.zod.model.Dispositivo;

public record ListagemDispositivo(Long id, String nome, String descricao, String watts, char status) {

    public ListagemDispositivo(Dispositivo dispositivo){
        this(dispositivo.getId(),dispositivo.getNome(),dispositivo.getDescricao(),dispositivo.getWatts(), dispositivo.getStatus());
    }

}
