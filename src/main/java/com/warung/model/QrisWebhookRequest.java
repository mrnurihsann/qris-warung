//Merepresentasikan struktur data untuk pertukaran informasi antar lapisan aplikasi (DTO).
/* Dalam aplikasi toko buku, ketika pengguna ingin mencari buku, mereka mungkin memasukkan 
    "kata kunci" dan "kategori". Informasi ini akan dikemas dalam sebuah objek di folder model 
    (misalnya, SearchRequest). Kemudian, ketika sistem menemukan daftar buku yang sesuai, informasi 
    buku-buku ini (judul, penulis, harga) akan dikemas dalam objek lain di folder model 
    (misalnya, BookResponse) untuk ditampilkan kepada pengguna. */

package com.warung.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QrisWebhookRequest {

    @JsonProperty("referenceId")
    private String referenceId;

    @JsonProperty("merchantId")
    private String merchantId;

    @JsonProperty("customerName")
    private String customerName;

    @JsonProperty("amount")
    private Long amount;

    @JsonProperty("status")
    private String status;

    @JsonProperty("transactionDate")
    private String transactionDate;

    @JsonProperty("qrisIssuer")
    private String qrisIssuer;

    @JsonProperty("paymentMethod")
    private String paymentMethod;

    // Getter dan Setter
    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getQrisIssuer() {
        return qrisIssuer;
    }

    public void setQrisIssuer(String qrisIssuer) {
        this.qrisIssuer = qrisIssuer;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "QrisWebhookRequest{" +
                "referenceId='" + referenceId + '\'' +
                ", merchantId='" + merchantId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", transactionDate='" + transactionDate + '\'' +
                ", qrisIssuer='" + qrisIssuer + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                '}';
    }
}
