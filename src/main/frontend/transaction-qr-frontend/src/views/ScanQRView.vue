<template>
  <div class="container">
    <h2>Form Pembayaran</h2>
    <form @submit.prevent="submit">
      <input v-model="form.referenceId" placeholder="referenceId" required />
      <input v-model="form.customerName" placeholder="Nama" required />
      <input
        v-model.number="form.amount"
        type="number"
        placeholder="Nominal"
        required
      />
      <button type="submit">Bayar</button>
    </form>
    <p v-if="message">{{ message }}</p>
  </div>
</template>

<script>
export default {
  data () {
    return {
      form: {
        referenceId: '',
        customerName: '',
        amount: 0
      },
      message: ''
    }
  },
  mounted () {
    this.form.referenceId = this.$route.query.id
  },
  methods: {
    async submit () {
      const res = await fetch('http://localhost:9081/transactions/submit', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(this.form)
      })
      if (res.ok) {
        this.message = 'Pembayaran berhasil!'
      }
    }
  }
}
</script>

<style scoped>
.container {
  max-width: 500px;
  margin: 80px auto;
  padding: 40px 30px;
  background: linear-gradient(135deg, #1d2b64, #f8cdda);
  border-radius: 20px;
  box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
  color: #fff;
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  text-align: center;
  animation: fadeIn 1s ease;
}

h2 {
  font-size: 2rem;
  margin-bottom: 30px;
  font-weight: bold;
  text-shadow: 1px 1px 4px rgba(0, 0, 0, 0.3);
}

form {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

input {
  padding: 12px 18px;
  font-size: 1rem;
  border-radius: 10px;
  border: none;
  outline: none;
  box-shadow: inset 0 0 10px rgba(255, 255, 255, 0.2);
  transition: 0.3s;
}

input:focus {
  box-shadow: 0 0 8px rgba(255, 255, 255, 0.5);
  background-color: rgba(255, 255, 255, 0.1);
}

button {
  padding: 14px;
  font-size: 1.1rem;
  font-weight: bold;
  background-color: #ffffff;
  color: #1d2b64;
  border: none;
  border-radius: 50px;
  cursor: pointer;
  transition: all 0.3s ease;
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
}

button:hover {
  background-color: #ffe5ec;
  color: #1d2b64;
  transform: translateY(-2px);
  box-shadow: 0 12px 25px rgba(0, 0, 0, 0.3);
}

p {
  margin-top: 20px;
  font-size: 1rem;
  font-weight: 600;
  color: #fffb;
  text-shadow: 0 0 6px rgba(0, 0, 0, 0.2);
}

/* Animation */
@keyframes fadeIn {
  0% {
    opacity: 0;
    transform: translateY(30px);
  }
  100% {
    opacity: 1;
    transform: translateY(0);
  }
}

/* Responsive */
@media (max-width: 480px) {
  .container {
    margin: 40px 20px;
    padding: 30px 20px;
  }

  h2 {
    font-size: 1.6rem;
  }

  input,
  button {
    font-size: 0.95rem;
  }
}
</style>
