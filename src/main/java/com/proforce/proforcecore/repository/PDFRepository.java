package com.proforce.proforcecore.repository;

import com.proforce.proforcecore.domain.Pdf;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface PDFRepository extends CrudRepository<Pdf, Long> {
}
