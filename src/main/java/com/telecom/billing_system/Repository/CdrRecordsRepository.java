package com.telecom.billing_system.Repository;

import com.telecom.billing_system.Entity.CdrRecords;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CdrRecordsRepository extends JpaRepository<CdrRecords, Long> {
}
