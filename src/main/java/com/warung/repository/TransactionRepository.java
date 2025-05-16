//Bertanggung jawab untuk interaksi dengan basis data (melakukan operasi CRUD dan query).
/*Dalam aplikasi toko buku, BookRepository akan bertanggung jawab untuk semua interaksi 
    yang berkaitan dengan data buku di basis data. Ketika layanan ingin mencari buku 
    berdasarkan judul, ia akan menggunakan metode yang disediakan oleh BookRepository tanpa 
    perlu tahu apakah data buku disimpan di MySQL, PostgreSQL, atau sistem penyimpanan lainnya. */

package com.warung.repository;

import java.util.List;

import com.warung.entity.TransactionEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TransactionRepository implements PanacheRepository<TransactionEntity> {
    
    // Metode-metode dasar seperti persist(), listAll(), findById(), dll
    // sudah tersedia dari PanacheRepository
    
    // Tambahkan metode kustom jika diperlukan
    public List<TransactionEntity> findByStatus(String status) {
        return list("status", status);
    }
    
    public List<TransactionEntity> findByCustomerName(String customerName) {
        return list("customerName", customerName);
    }
    
    public TransactionEntity findByReferenceId(String referenceId) {
        return find("referenceId", referenceId).firstResult(); 
    }
}