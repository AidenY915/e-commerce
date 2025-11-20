package com.ecommerce.core.payment.application;

import com.ecommerce.core.common.ResponseEntity;
import com.ecommerce.core.payment.application.dto.PaymentCommand;
import com.ecommerce.core.payment.application.dto.PaymentInfo;
import com.ecommerce.core.payment.client.TossPaymentClient;
import com.ecommerce.core.payment.client.dto.TossPaymentResponse;
import com.ecommerce.core.payment.domain.Payment;
import com.ecommerce.core.payment.domain.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PaymentService {
    final private PaymentRepository paymentRepository;
    final private TossPaymentClient tossPaymentClient;
    public ResponseEntity<List<PaymentInfo>> findAll(Pageable pageable) {
        Page<Payment> page = paymentRepository.findAll(pageable);
        List<PaymentInfo> paymentInfoList = page.stream().map(PaymentInfo::from).toList();
        return new ResponseEntity<>(HttpStatus.OK.value(), paymentInfoList, page.getTotalElements());
    }

        public ResponseEntity<PaymentInfo> confirm(PaymentCommand command){
            TossPaymentResponse tossPayment = tossPaymentClient.confirm(command);
            Payment payment = Payment.create(
                    tossPayment.paymentKey(),
                    tossPayment.orderId(),
                    tossPayment.totalAmount()
            );
            LocalDateTime approvedAt = tossPayment.approvedAt() != null ? tossPayment.approvedAt().toLocalDateTime() : null;
            LocalDateTime requestedAt = tossPayment.requestedAt() != null ? tossPayment.requestedAt().toLocalDateTime() : null;

            payment.markConfirmed(tossPayment.method(), approvedAt, requestedAt);

            Payment saved = paymentRepository.save(payment);

            return new ResponseEntity<>(HttpStatus.CREATED.value(), PaymentInfo.from(saved), 1L);
        }
}
