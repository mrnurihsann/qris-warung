// Merepresentasikan struktur data dari basis data (tabel). Setiap kelas di sini memetakan ke sebuah tabel.
/*Bayangkan Anda sedang membuat aplikasi toko buku. Folder entity akan berisi deskripsi tentang "Buku" 
    (dengan informasi seperti judul, penulis, ISBN), "Pelanggan" (dengan informasi seperti nama, alamat, email), 
    dan "Pesanan" (dengan informasi seperti tanggal pemesanan, total harga). Setiap kelas di dalam folder entity 
    adalah representasi dari salah satu "benda" ini. */

package com.warung.entity;

import java.time.LocalDateTime;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transaction")
public class TransactionEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "referenceId")
    private String referenceId;

    @Column(name = "customerName")
    private String customerName;

    @Column(name = "amount")
    private Long amount;

    @Column(nullable = false)
    private String status = "pending";

    @Column(name = "timestamp")
    private LocalDateTime timestamp = LocalDateTime.now();

    // Constructors
    public TransactionEntity() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}