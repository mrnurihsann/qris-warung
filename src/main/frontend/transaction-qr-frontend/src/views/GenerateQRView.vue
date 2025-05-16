<template>
  <div class="container">
    <h2>Scan QR untuk Membayar</h2>

    <div class="qrcode-wrapper" ref="qrWrapper">
      <qrcode-vue :value="paymentUrl" :size="200" />
    </div>

    <p>ID Transaksi: {{ referenceId }}</p>

    <div class="button-group">
      <button class="btn" @click="copyToClipboard">
        Salin Link Pembayaran
      </button>
      <button class="btn" @click="downloadQR">Unduh QR Code</button>
    </div>
  </div>
</template>

<script>
import QrcodeVue from 'qrcode.vue'
import { v4 as uuidv4 } from 'uuid'

export default {
  components: { QrcodeVue },
  data () {
    return {
      referenceId: uuidv4()
    }
  },
  computed: {
    paymentUrl () {
      return `http://localhost:5173/scan?id=${this.referenceId}`
    }
  },
  methods: {
    copyToClipboard () {
      navigator.clipboard.writeText(this.paymentUrl).then(() => {
        alert('Link pembayaran disalin!')
      })
    },
    downloadQR () {
      const qrEl = this.$refs.qrWrapper.querySelector('canvas')
      const link = document.createElement('a')
      link.download = `qr-${this.referenceId}.png`
      link.href = qrEl.toDataURL('image/png')
      link.click()
    }
  }
}
</script>

<style scoped>
.container {
  max-width: 480px;
  margin: 60px auto;
  padding: 40px 30px;
  background: linear-gradient(135deg, #4facfe, #00f2fe);
  border-radius: 20px;
  box-shadow: 0 12px 40px rgba(0, 0, 0, 0.2);
  color: #fff;
  font-family: "Poppins", sans-serif;
  text-align: center;
  animation: fadeIn 0.7s ease-in-out;
}

h2 {
  font-size: 2rem;
  font-weight: 700;
  margin-bottom: 25px;
}

p {
  margin-top: 25px;
  font-size: 1.1rem;
  background: rgba(255, 255, 255, 0.1);
  padding: 12px 18px;
  border-radius: 12px;
  display: inline-block;
  color: #e6faff;
}

.qrcode-wrapper {
  background-color: #fff;
  padding: 20px;
  border-radius: 16px;
  display: inline-block;
  box-shadow: 0 8px 25px rgba(0, 0, 0, 0.1);
}

.button-group {
  display: flex;
  justify-content: center;
  gap: 16px;
  margin-top: 30px;
  flex-wrap: wrap;
}

.btn {
  background: #ffffff;
  color: #2575fc;
  font-weight: 600;
  padding: 12px 20px;
  border-radius: 30px;
  border: none;
  cursor: pointer;
  transition: all 0.3s ease;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.btn:hover {
  background-color: #2575fc;
  color: #ffffff;
  box-shadow: 0 6px 18px rgba(0, 0, 0, 0.15);
  transform: translateY(-2px);
}

@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(10px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

@media (max-width: 500px) {
  .container {
    padding: 30px 20px;
    margin: 40px 16px;
  }

  .button-group {
    flex-direction: column;
    gap: 12px;
  }

  .btn {
    width: 100%;
  }
}
</style>
