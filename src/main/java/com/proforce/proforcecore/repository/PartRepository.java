package com.proforce.proforcecore.repository;

import com.proforce.proforcecore.domain.Part;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface PartRepository extends CrudRepository<Part, Long> {

    @Override
    List<Part> findAll();

    @Override
    Optional<Part> findById(Long id);

    @Override
    Part save(Part part);

    @Override
    void deleteById(Long id);

    List<Part> findAllByType(String type);

}
