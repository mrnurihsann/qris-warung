import axios from 'axios'

const API_URL = '/transactions'

export default {
  // Ambil semua transaksi
  getAllTransactions () {
    return axios.get(API_URL)
  },

  // Ambil transaksi berdasarkan ID
  getTransactionById (id) {
    return axios.get(`${API_URL}/${id}`)
  },

  // Ambil transaksi berdasarkan referenceId
  getTransactionByReferenceId (referenceId) {
    return axios.get(`${API_URL}/reference/${referenceId}`)
  },

  // Buat transaksi baru
  createTransaction (transaction) {
    return axios.post(API_URL, transaction)
  },

  // Update transaksi
  updateTransaction (id, transaction) {
    return axios.put(`${API_URL}/${id}`, transaction)
  },

  // Hapus transaksi
  deleteTransaction (id) {
    return axios.delete(`${API_URL}/${id}`)
  },

  // Proses data dari QR code
  processQRTransaction (transaction) {
    return axios.post(`${API_URL}/qr-scan`, transaction)
  }
}
