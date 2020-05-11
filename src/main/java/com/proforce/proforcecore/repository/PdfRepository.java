package com.proforce.proforcecore.repository;

import com.proforce.proforcecore.domain.Pdf;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface PdfRepository extends CrudRepository<Pdf, Long> {

    @Override
    List<Pdf> findAll();

    @Override
    Optional<Pdf> findById(Long id);

}
