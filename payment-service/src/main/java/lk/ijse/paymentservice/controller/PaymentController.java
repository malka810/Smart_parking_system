
package lk.ijse.paymentservice.controller;

import lk.ijse.paymentservice.dto.PaymentRequestDTO;
import lk.ijse.paymentservice.dto.PaymentResponseDTO;
import lk.ijse.paymentservice.dto.ReceiptDTO;
import lk.ijse.paymentservice.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> processPayment(@RequestBody PaymentRequestDTO paymentRequest) {
        PaymentResponseDTO response = paymentService.processPayment(paymentRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{paymentId}/receipt")
    public ResponseEntity<ReceiptDTO> generateReceipt(@PathVariable Long paymentId) {
        ReceiptDTO receipt = paymentService.generateReceipt((paymentId));
        return ResponseEntity.ok(receipt);
    }

    @GetMapping("/{paymentId}/status")
    public ResponseEntity<String> checkPaymentStatus(@PathVariable Long paymentId) {
        String status = paymentService.checkPaymentStatus((paymentId));
        return ResponseEntity.ok(status);
    }
}