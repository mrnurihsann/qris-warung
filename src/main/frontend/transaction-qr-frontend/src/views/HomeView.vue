<template>
  <div class="home">
    <h1>Transaction QR Code System</h1>
    <p>
      Welcome to the transaction management system. Use the navigation to
      generate or scan QR codes for transactions.
    </p>
    <div class="buttons">
      <button class="btn btn-primary">Generate QR Code</button>
      <button class="btn btn-secondary">Scan QR Code</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HomeView'
}
</script>

<style scoped>
.home {
  max-width: 700px;
  margin: 60px auto;
  padding: 40px 30px;
  background: linear-gradient(135deg, #6a11cb, #2575fc);
  border-radius: 15px;
  box-shadow: 0 12px 30px rgba(37, 117, 252, 0.3);
  color: #f0f4ff;
  text-align: center;
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  user-select: none;
}

h1 {
  font-size: 3rem;
  font-weight: 700;
  margin-bottom: 20px;
  text-shadow: 2px 2px 10px rgba(0, 0, 0, 0.3);
  letter-spacing: 2px;
}

p {
  font-size: 1.25rem;
  line-height: 1.6;
  margin-bottom: 35px;
  opacity: 0;
  animation: fadeInText 1.5s forwards 0.5s;
}

.buttons {
  display: flex;
  justify-content: center;
  gap: 20px;
}

.btn {
  cursor: pointer;
  padding: 12px 30px;
  border-radius: 30px;
  font-size: 1.1rem;
  font-weight: 600;
  border: none;
  transition: all 0.3s ease;
  box-shadow: 0 6px 15px rgba(0, 0, 0, 0.1);
  user-select: none;
}

.btn-primary {
  background-color: #2575fc;
  color: white;
  box-shadow: 0 8px 20px #2575fcaa;
}

.btn-primary:hover {
  background-color: #1a53c9;
  box-shadow: 0 12px 25px #1a53c9cc;
  transform: translateY(-3px);
}

.btn-secondary {
  background-color: #6a11cb;
  color: white;
  box-shadow: 0 8px 20px #6a11cbaa;
}

.btn-secondary:hover {
  background-color: #4b0899;
  box-shadow: 0 12px 25px #4b0899cc;
  transform: translateY(-3px);
}

@keyframes fadeInText {
  to {
    opacity: 1;
  }
}
</style>
