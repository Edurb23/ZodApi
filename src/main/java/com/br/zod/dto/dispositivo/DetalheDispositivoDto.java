package com.br.zod.dto.dispositivo;

import com.br.zod.model.Dispositivo;
import com.br.zod.model.Relatorio;

import java.util.List;

public record DetalheDispositivoDto(Long id, String nome, String descricao, String watts, char status, List<Relatorio> relatorios)  {


    public DetalheDispositivoDto(Dispositivo dipositivo){
        this(dipositivo.getId(), dipositivo.getNome(), dipositivo.getDescricao(), dipositivo.getWatts(), dipositivo.getStatus(), dipositivo.getRelatorios());
    }



}
