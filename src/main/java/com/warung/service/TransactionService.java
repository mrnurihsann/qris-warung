//Berisi logika bisnis utama dari aplikasi.
/*Dalam aplikasi toko buku, BookService akan berisi logika untuk menambahkan buku baru 
    (misalnya, memastikan ISBN unik), mencari buku berdasarkan kriteria tertentu, 
    menghitung diskon, atau memproses pemesanan buku. BookResource akan memanggil 
    metode-metode di BookService untuk menjalankan tugas-tugas ini. */

package com.warung.service;

import java.util.List;
import java.util.Map;

import com.warung.entity.TransactionEntity;
import com.warung.repository.TransactionRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TransactionService {

    @Inject
    TransactionRepository transactionRepository;
    
    @Transactional
    public TransactionEntity saveTransaction(TransactionEntity transaction) {
        // Simpan entity menggunakan entity manager
        transactionRepository.persist(transaction);
        return transaction;
    }
    
    // Metode ini sudah tidak dipakai lagi, diganti dengan yang menggunakan TransactionEntity langsung
    @Transactional
    public TransactionEntity saveTransaction(Map<String, Object> payload) {
        // Ini adalah implementasi lama yang sebaiknya dihapus
        // dan diganti dengan metode saveTransaction(TransactionEntity) di atas
        throw new UnsupportedOperationException("Metode ini sudah tidak digunakan lagi");
    }
    
    public List<TransactionEntity> getAllTransactions() {
        return transactionRepository.listAll();
    }

    public void save(TransactionEntity entity) {
    transactionRepository.persist(entity);
    }

    public List<TransactionEntity> getAll() {
        return transactionRepository.listAll();
    }

}