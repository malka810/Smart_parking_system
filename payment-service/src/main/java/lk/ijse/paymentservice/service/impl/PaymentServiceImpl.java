package lk.ijse.paymentservice.service.impl;

import lk.ijse.paymentservice.dto.PaymentRequestDTO;
import lk.ijse.paymentservice.dto.PaymentResponseDTO;
import lk.ijse.paymentservice.dto.ReceiptDTO;
import lk.ijse.paymentservice.entity.Payment;
import lk.ijse.paymentservice.repo.PaymentRepository;
import lk.ijse.paymentservice.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, ModelMapper modelMapper) {
        this.paymentRepository = paymentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PaymentResponseDTO processPayment(PaymentRequestDTO paymentRequest) {
        // Validate payment details
        if (!validatePaymentDetails(paymentRequest)) {
            return createFailedResponse(paymentRequest, "Invalid payment details");
        }

        // Simulate payment processing
        boolean isPaymentSuccessful = simulatePaymentProcessing(paymentRequest);

        if (!isPaymentSuccessful) {
            return createFailedResponse(paymentRequest, "Payment processing failed");
        }

        // Create and save payment record
        Payment payment = createPaymentRecord(paymentRequest);
        paymentRepository.save(payment);

        return createSuccessResponse(payment);
    }

    @Override
    public ReceiptDTO generateReceipt(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        return new ReceiptDTO(
                payment.getReceiptNumber(),
                payment.getPaymentId(),
                payment.getPaymentDate(),
                payment.getReservationId(),
                payment.getAmount().doubleValue(),
                payment.getPaymentMethod(),
                payment.getCardLastFourDigits(),
                payment.getTransactionId(),
                payment.getStatus()
        );
    }

    @Override
    public String checkPaymentStatus(Long paymentId) {
        return paymentRepository.findById(paymentId)
                .map(Payment::getStatus)
                .orElse("NOT_FOUND");
    }

    // Helper methods
    private boolean validatePaymentDetails(PaymentRequestDTO paymentRequest) {
        if (paymentRequest.getAmount() == null || paymentRequest.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }
        if (paymentRequest.getPaymentMethod().startsWith("CARD")) {
            return validateCardDetails(paymentRequest);
        }
        return true;
    }

    private boolean validateCardDetails(PaymentRequestDTO paymentRequest) {
        if (paymentRequest.getCardNumber() == null || paymentRequest.getCardNumber().length() != 16) {
            return false;
        }
        if (paymentRequest.getCvv() == null || paymentRequest.getCvv().length() != 3) {
            return false;
        }
        return paymentRequest.getCardExpiry() != null && paymentRequest.getCardExpiry().matches("\\d{2}/\\d{2}");
    }

    private boolean simulatePaymentProcessing(PaymentRequestDTO paymentRequest) {
        return Math.random() >= 0.1; // 90% success rate
    }

    private Payment createPaymentRecord(PaymentRequestDTO paymentRequest) {
        Payment payment = modelMapper.map(paymentRequest, Payment.class);
        payment.setStatus("SUCCESS");
        payment.setTransactionId("TXN-" + UUID.randomUUID().toString().substring(0, 8));
        payment.setReceiptNumber("RCPT-" + System.currentTimeMillis());

        if (paymentRequest.getCardNumber() != null && paymentRequest.getCardNumber().length() >= 4) {
            payment.setCardLastFourDigits(paymentRequest.getCardNumber()
                    .substring(paymentRequest.getCardNumber().length() - 4));
        }

        return payment;
    }

    private PaymentResponseDTO createSuccessResponse(Payment payment) {
        return new PaymentResponseDTO(
                payment.getPaymentId(),
                payment.getReservationId(),
                payment.getUserId(),
                payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getPaymentDate(),
                payment.getStatus(),
                payment.getTransactionId(),
                payment.getReceiptNumber(),
                "Payment processed successfully"
        );
    }

    private PaymentResponseDTO createFailedResponse(PaymentRequestDTO paymentRequest, String message) {
        return new PaymentResponseDTO(
                null,
                paymentRequest.getReservationId(),
                paymentRequest.getUserId(),
                paymentRequest.getAmount(),
                paymentRequest.getPaymentMethod(),
                LocalDateTime.now(),
                "FAILED",
                null,
                null,
                message
        );
    }
}