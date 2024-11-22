package com.br.zod.repository;

import com.br.zod.model.Relatorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelatorioRepository extends JpaRepository<Relatorio, Long> {

}
