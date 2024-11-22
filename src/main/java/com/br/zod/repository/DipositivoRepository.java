package com.br.zod.repository;

import com.br.zod.model.Dispositivo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DipositivoRepository extends JpaRepository<Dispositivo, Long> {

    Page<Dispositivo> findByUsuarioId(Long usuarioId, Pageable pageable);


}
