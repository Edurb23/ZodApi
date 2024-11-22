package com.br.zod.controller;

import com.br.zod.repository.DipositivoRepository;
import com.br.zod.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dispositivo")
public class DispositivosController {


    @Autowired
    private DipositivoRepository dipositivoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        dipositivoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }










}
