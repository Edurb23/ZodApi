package com.br.zod.controller;

import com.br.zod.dto.dispositivo.ListagemDispositivo;
import com.br.zod.dto.relatorioDto.RelatorioDetalhadoDto;
import com.br.zod.repository.DipositivoRepository;
import com.br.zod.repository.RelatorioRepository;
import com.br.zod.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/relatorio")
public class RelatorioController {


    @Autowired
    private RelatorioRepository relatorioRepository;


    @GetMapping
    @Transactional
    public ResponseEntity<List<RelatorioDetalhadoDto>> get(Pageable pageable){
        var consumo = relatorioRepository.findAll().stream().map(RelatorioDetalhadoDto::new).toList();
        return ok(consumo);
    }











}
