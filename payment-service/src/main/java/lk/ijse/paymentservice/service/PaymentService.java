package lk.ijse.paymentservice.service;

import lk.ijse.paymentservice.dto.PaymentRequestDTO;
import lk.ijse.paymentservice.dto.PaymentResponseDTO;
import lk.ijse.paymentservice.dto.ReceiptDTO;

public interface PaymentService {
    PaymentResponseDTO processPayment(PaymentRequestDTO paymentRequest);

    ReceiptDTO generateReceipt(Long paymentId);

    String checkPaymentStatus(Long paymentId);
}
