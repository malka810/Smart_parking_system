package lk.ijse.paymentservice.dto;

import java.time.LocalDateTime;


public class ReceiptDTO {
    private String receiptNumber;
    private String paymentId;
    private LocalDateTime paymentDate;
    private String reservationId;
    private double amount;
    private String paymentMethod;
    private String cardLastFourDigits;
    private String transactionId;
    private String status;

    public ReceiptDTO(String receiptNumber, Long paymentId, LocalDateTime paymentDate, String reservationId, double amount, String paymentMethod, String cardLastFourDigits, String transactionId, String status) {
    }

    public ReceiptDTO(String receiptNumber, String paymentId, LocalDateTime paymentDate, String reservationId, double amount, String paymentMethod, String cardLastFourDigits, String transactionId, String status) {
        this.receiptNumber = receiptNumber;
        this.paymentId = paymentId;
        this.paymentDate = paymentDate;
        this.reservationId = reservationId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.cardLastFourDigits = cardLastFourDigits;
        this.transactionId = transactionId;
        this.status = status;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCardLastFourDigits() {
        return cardLastFourDigits;
    }

    public void setCardLastFourDigits(String cardLastFourDigits) {
        this.cardLastFourDigits = cardLastFourDigits;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ReceiptDTO{" +
                "receiptNumber='" + receiptNumber + '\'' +
                ", paymentId='" + paymentId + '\'' +
                ", paymentDate=" + paymentDate +
                ", reservationId='" + reservationId + '\'' +
                ", amount=" + amount +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", cardLastFourDigits='" + cardLastFourDigits + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}