package com.br.zod.dto.relatorioDto;

import com.br.zod.dto.dispositivo.DetalheDispositivoDto;
import com.br.zod.model.Dispositivo;
import com.br.zod.model.Relatorio;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;

public record RelatorioDetalhadoDto(DetalheDispositivoDto dispositivo, String consumo , String  tempo) {

        public RelatorioDetalhadoDto(Relatorio relatorio){
            this(new DetalheDispositivoDto(relatorio.getDipositivo()), relatorio.getConsumo(), relatorio.getTempo());
        }

}
