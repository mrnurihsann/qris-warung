//Menampung format jawaban/response API yang dikirim ke klien.
/*Ketika aplikasi toko buku berhasil menambahkan buku baru, BookResource 
mungkin mengembalikan objek ApiResponse yang berisi status keberhasilan 
(misalnya, success: true) dan pesan (misalnya, "Buku berhasil ditambahkan"). 
Jika terjadi kesalahan, ApiResponse mungkin berisi success: false dan pesan error yang sesuai. */

package com.warung.response;

import java.io.Serializable;

public class ApiResponse<T> implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private boolean success;
    private String message;
    private T data;
    
    public ApiResponse() {
    }
    
    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
    
    // Factory method untuk response sukses
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, message, data);
    }
    
    // Factory method untuk response error
    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, message, null);
    }
    
    // Factory method untuk response error dengan data
    public static <T> ApiResponse<T> error(String message, T data) {
        return new ApiResponse<>(false, message, data);
    }
    
    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
}