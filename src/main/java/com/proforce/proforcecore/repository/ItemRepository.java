package com.proforce.proforcecore.repository;

import com.proforce.proforcecore.domain.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
}
