package com.warung.resource;

import java.time.LocalDateTime;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.warung.entity.TransactionEntity;
import com.warung.model.QrisWebhookRequest;
import com.warung.response.ApiResponse;
import com.warung.service.TransactionService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/transactions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Transaction API", description = "API untuk mengelola transaksi pembayaran QRIS")
public class TransactionResource {

    @Inject
    TransactionService transactionService;

    @POST
    @Path("/webhook")
    @Operation(
        summary = "Menerima webhook dari payment gateway QRIS",
        description = "Endpoint ini menerima data callback dari payment gateway QRIS dan menyimpannya di database"
    )
    public Response create(
            @RequestBody(
                content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = QrisWebhookRequest.class)
                ),
                description = "Data transaksi dari payment gateway QRIS"
            )
            QrisWebhookRequest webhookRequest) {
        try {
            // Log payload untuk debugging
            System.out.println("Received QRIS webhook payload: " + webhookRequest);

            // Memetakan payload ke objek TransactionEntity
            TransactionEntity transaction = new TransactionEntity();

            // Set nilai dari payload ke entity
            transaction.setReferenceId(webhookRequest.getReferenceId());
            transaction.setCustomerName(webhookRequest.getCustomerName());
            transaction.setAmount(webhookRequest.getAmount());
            transaction.setStatus(webhookRequest.getStatus());

            // Simpan transaksi
            TransactionEntity savedTransaction = transactionService.saveTransaction(transaction);

            // Buat respons sukses
            ApiResponse<TransactionEntity> response = new ApiResponse<>(true,
                    "Transaksi berhasil disimpan", savedTransaction);

            return Response.ok(response).build();

        } catch (Exception e) {
            // Log error
            System.err.println("Error processing QRIS webhook: " + e.getMessage());
            e.printStackTrace();

            // Return error response
            ApiResponse<?> errorResponse = new ApiResponse<>(false,
                    "Gagal memproses webhook: " + e.getMessage(), null);

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(errorResponse)
                    .build();
        }
    }
    
     @POST
@Path("/qr-scan")
@Transactional
public Response createFromQR(TransactionEntity transaction) {
    // Cek apakah referenceId sudah ada
    TransactionEntity existing = TransactionEntity.find("referenceId", transaction.getReferenceId()).firstResult();
    if (existing != null) {
        // Update data yang sudah ada
        existing.setCustomerName(transaction.getCustomerName());
        existing.setAmount(transaction.getAmount());
        existing.setStatus(transaction.getStatus());
        existing.setTimestamp(LocalDateTime.now());
        return Response.ok(existing).build();
    } else {
        // Buat data baru
        transaction.setTimestamp(LocalDateTime.now());
        transaction.persist();
        return Response.ok(transaction).status(201).build();
    }
}


    @GET
    @Operation(
        summary = "Mendapatkan daftar transaksi",
        description = "Endpoint ini mengambil semua daftar transaksi yang tersimpan di database"
    )
    public Response list() {
        List<TransactionEntity> transactions = transactionService.getAllTransactions();
        ApiResponse<List<TransactionEntity>> response = new ApiResponse<>(true,
                "Daftar transaksi berhasil diambil", transactions);
        return Response.ok(response).build();
    }
    
   @POST
    @Path("/submit")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response submit(TransactionEntity entity) {
        try {
            entity.setStatus("paid");
            entity.setTimestamp(LocalDateTime.now());
            transactionService.save(entity);
            return Response.ok(new ApiResponse<>(true, "Transaksi berhasil dikirim", entity)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiResponse<>(false, "Gagal submit: " + e.getMessage(), null))
                    .build();
        }
    }


    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TransactionEntity> getAllTransactions() {
        return transactionService.getAll();
    }

}