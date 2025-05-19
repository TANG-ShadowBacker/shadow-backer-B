package com.tang.shadowbacker.repository.order;

import com.tang.shadowbacker.model.order.PaymentRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRecordRepository extends JpaRepository<PaymentRecord, String> {
}
