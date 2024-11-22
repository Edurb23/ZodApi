package com.br.zod.controller;

import com.br.zod.dto.dispositivo.DetalheDispositivoDto;
import com.br.zod.dto.dispositivo.ListagemDispositivo;
import com.br.zod.dto.dispositivo.NovoDispositivoDto;
import com.br.zod.dto.loginDto.DetalheLoginDto;
import com.br.zod.dto.usuarioDto.AtualizacaoUsuario;
import com.br.zod.dto.loginDto.AtualizarEmail;
import com.br.zod.dto.usuarioDto.DetalheUsuario;
import com.br.zod.model.Dispositivo;
import com.br.zod.model.Relatorio;
import com.br.zod.repository.DipositivoRepository;
import com.br.zod.repository.LoginRepository;
import com.br.zod.repository.RelatorioRepository;
import com.br.zod.repository.UsuarioRepository;
import com.br.zod.service.CalculoEnergia;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LoginRepository loginRepository;


    @Autowired
    private DipositivoRepository dipositivoRepository;

    @Autowired
    private CalculoEnergia calculoEnergia;


    @Autowired
    private RelatorioRepository relatorioRepository;


    @GetMapping("{id}")
    public ResponseEntity<DetalheUsuario> get(@PathVariable("id") Long id){
        var user = usuarioRepository.getReferenceById(id);
        return ok(new DetalheUsuario(user));
    }

    @GetMapping("/email/{id}")
    public ResponseEntity<DetalheLoginDto> getEmail(@PathVariable("id")Long id){
        var userEmail =  loginRepository.getReferenceById(id);
        return  ok(new DetalheLoginDto(userEmail));
    }

    @PutMapping("{id}")
    public  ResponseEntity<DetalheUsuario> put(@PathVariable("id")Long id, @Valid  @RequestBody AtualizacaoUsuario dto){
        var usuario = usuarioRepository.getReferenceById(id);
        usuario.atualizarUsuario(dto);
        return ResponseEntity.ok(new DetalheUsuario(usuario));
    }

    @PutMapping("/email/{id}")
    public ResponseEntity<DetalheLoginDto> putEmail(@PathVariable("id") @Valid Long id, @RequestBody AtualizarEmail dto){
        var email =  loginRepository.getReferenceById(id);
        email.atualizarEmail(dto);
        return ResponseEntity.ok(new DetalheLoginDto(email));

    }


    ///  DISPOSITIVOS


        @GetMapping("/{id}/dispostivos")
        @Transactional
        public ResponseEntity<List<ListagemDispositivo>> getDevice(@PathVariable("id") Long id, Pageable pageable ){
            var usuario = usuarioRepository.getReferenceById(id);
            var listDevice = dipositivoRepository.findByUsuarioId(id, pageable).stream().map(ListagemDispositivo::new).toList();
            return ok(listDevice);

        }


    @PostMapping("{id}/dispositivo")
    public ResponseEntity<DetalheDispositivoDto> postDispositivo(
            @PathVariable("id") Long id,
            @RequestBody @Valid NovoDispositivoDto dto,
            UriComponentsBuilder uriComponentsBuilder
    ) {

        var usuario = usuarioRepository.getReferenceById(id);


        var dispositivo = new Dispositivo(dto, usuario);
        dipositivoRepository.save(dispositivo);


        var resultadoCalculo = calculoEnergia.calcularGastoEnergia(
                Double.parseDouble(dto.watts()),
                24,
                30,
                0.6
        );

        var relatorio = new Relatorio();
        relatorio.setDataRerencia(LocalDate.now());
        relatorio.setTempo("24 horas/dia");
        relatorio.setConsumo(resultadoCalculo.getConsumoMensalKWh());
        relatorio.setDataRegistro(LocalDateTime.now());
        relatorio.setDipositivo(dispositivo);


        relatorioRepository.save(relatorio);
        var detalheDispositivoDto = new DetalheDispositivoDto(dispositivo);
        var uri = uriComponentsBuilder.path("/dispositivo/{id}").buildAndExpand(dispositivo.getId()).toUri();
        return ResponseEntity.created(uri).body(detalheDispositivoDto);
    }













}
