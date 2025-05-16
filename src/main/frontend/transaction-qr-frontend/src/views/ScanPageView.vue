<template>
  <div class="scan-page">
    <h2>Masukkan Nominal Pembayaran</h2>

    <form @submit.prevent="submitPayment">
      <div>
        <label>Nominal:</label>
        <input type="number" v-model="amount" required />
      </div>

      <button type="submit">Kirim Pembayaran</button>
    </form>
  </div>
</template>

<script>
export default {
  data () {
    return {
      amount: '',
      referenceId: ''
    }
  },
  mounted () {
    const urlParams = new URLSearchParams(window.location.search)
    this.referenceId = urlParams.get('id') // Dapatkan id dari URL

    if (!this.referenceId) {
      alert('ID transaksi tidak ditemukan!')
    }
  },
  methods: {
    async submitPayment () {
      const payload = {
        referenceId: this.referenceId,
        amount: this.amount,
        customerName: 'Anonim', // bisa juga diisi dari input
        status: 'pending'
      }

      try {
        await fetch('http://localhost:9081/transactions/submit', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(payload)
        })
        alert('Pembayaran berhasil dikirim!')
      } catch (err) {
        alert('Terjadi kesalahan saat mengirim pembayaran.')
      }
    }
  }
}
</script>

<style scoped>
.scan-page {
  max-width: 400px;
  margin: 50px auto;
  padding: 30px;
  background: #fff;
  border-radius: 10px;
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
  text-align: center;
  font-family: "Poppins", sans-serif;
}

label {
  display: block;
  margin-bottom: 6px;
  font-weight: 600;
}

input {
  width: 100%;
  padding: 10px;
  margin-bottom: 16px;
  border-radius: 6px;
  border: 1px solid #ccc;
}

button {
  background-color: #4f46e5;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  font-weight: 600;
}

button:hover {
  background-color: #4338ca;
}
</style>
