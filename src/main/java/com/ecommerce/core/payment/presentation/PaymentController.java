package com.ecommerce.core.payment.presentation;

import com.ecommerce.core.common.ResponseEntity;
import com.ecommerce.core.payment.application.PaymentService;
import com.ecommerce.core.payment.application.dto.PaymentInfo;
import com.ecommerce.core.payment.presentation.dto.PaymentRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.v1}/payment")
@RequiredArgsConstructor
public class PaymentController {
    final private PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<PaymentInfo>> findAll(Pageable pageable){
        return paymentService.findAll(pageable);
    }
    @Operation(summary = "토스 결제 승인", description = "토스 결제 완료 후 paymentKey/orderId/amount를 전달받아 결제를 승인한다.")
    @PostMapping("/confirm")
    public ResponseEntity<PaymentInfo> confirm(@RequestBody PaymentRequest request) {
        return paymentService.confirm(request.toCommand());
    }
}
