package com.proforce.proforcecore.repository;

import com.proforce.proforcecore.scheduler.EventLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface EventLogRepository extends CrudRepository<EventLog, Long> {

    @Override
    List<EventLog> findAll();

    @Override
    Optional<EventLog> findById(Long id);

}
