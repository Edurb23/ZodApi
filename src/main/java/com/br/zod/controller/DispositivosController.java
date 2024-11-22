package com.br.zod.controller;

import com.br.zod.dto.dispositivo.ListagemDispositivo;
import com.br.zod.dto.relatorioDto.RelatorioDetalhadoDto;
import com.br.zod.repository.DipositivoRepository;
import com.br.zod.repository.RelatorioRepository;
import com.br.zod.repository.UsuarioRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("dispositivo")
public class DispositivosController {


    @Autowired
    private DipositivoRepository dipositivoRepository;

    @Autowired
    private RelatorioRepository relatorioRepository;

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        dipositivoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }














}
