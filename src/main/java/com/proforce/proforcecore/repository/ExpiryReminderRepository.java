package com.proforce.proforcecore.repository;

import com.proforce.proforcecore.domain.ExpiryReminder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ExpiryReminderRepository extends CrudRepository<ExpiryReminder, Long> {

    @Override
    List<ExpiryReminder> findAll();

    @Override
    long count();
}
