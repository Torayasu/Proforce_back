package com.proforce.proforcecore.repository;

import com.proforce.proforcecore.domain.Document;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface DocumentRepository extends CrudRepository<Document, Long> {

    @Override
    Document save(Document document);

    @Override
    List<Document> findAll();

    @Override
    Optional<Document> findById(Long id);

    @Override
    void deleteById(Long id);

    List<Document> findAllByType(String type);
    List<Document> findAllByManufacturer(String type);
    List<Document> findAllByName(String name);

}
