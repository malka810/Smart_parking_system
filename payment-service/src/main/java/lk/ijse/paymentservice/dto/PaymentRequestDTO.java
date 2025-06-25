// payment-service/src/main/java/lk/ijse/paymentservice/dto/PaymentRequestDTO.java
package lk.ijse.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

public class PaymentRequestDTO {
    private String reservationId;
    private String userId;
    private BigDecimal amount;
    private String paymentMethod;
    private String cardNumber;
    private String cardExpiry;
    private String cvv;
    private String cardHolderName;

    public PaymentRequestDTO() {}

    public PaymentRequestDTO(String reservationId, String userId, BigDecimal amount, String paymentMethod, String cardNumber, String cardExpiry, String cvv, String cardHolderName) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.cardNumber = cardNumber;
        this.cardExpiry = cardExpiry;
        this.cvv = cvv;
        this.cardHolderName = cardHolderName;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardExpiry() {
        return cardExpiry;
    }

    public void setCardExpiry(String cardExpiry) {
        this.cardExpiry = cardExpiry;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    @Override
    public String toString() {
        return "PaymentRequestDTO{" +
                "reservationId='" + reservationId + '\'' +
                ", userId='" + userId + '\'' +
                ", amount=" + amount +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardExpiry='" + cardExpiry + '\'' +
                ", cvv='" + cvv + '\'' +
                ", cardHolderName='" + cardHolderName + '\'' +
                '}';
    }
}