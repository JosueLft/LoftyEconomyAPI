package com.reign.lofty.LoftyEconomy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.reign.lofty.LoftyEconomy.entities.Record;

public interface RecordRepository extends JpaRepository<Record, Long> {}