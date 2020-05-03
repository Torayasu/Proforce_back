package com.proforce.proforcecore.repository;

import com.proforce.proforcecore.domain.Part;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface PartRepository extends CrudRepository<Part, Long> {
}
