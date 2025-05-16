<template>
  <div class="dashboard-layout">
    <aside class="sidebar">
      <h1 class="logo">Pagamento</h1>
      <nav class="nav-links">
        <a href="#" class="nav-button active">
          <span class="icon">📊</span> Dashboard
        </a>
        <a href="#" class="nav-button">
          <span class="icon">💸</span> Transaksi
        </a>
        <a href="#" class="nav-button">
          <span class="icon">⚙️</span> Pengaturan
        </a>
      </nav>
    </aside>

    <main class="main-content">
      <header class="dashboard-header">
        <h2>Daftar Transaksi</h2>
      </header>

      <section class="table-container">
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Nama</th>
              <th>Nominal</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="t in list" :key="t.id">
              <td>{{ t.referenceId }}</td>
              <td>{{ t.customerName }}</td>
              <td>Rp {{ Number(t.amount).toLocaleString() }}</td>
              <td>
                <span :class="['badge', t.status]">{{ t.status }}</span>
              </td>
            </tr>
          </tbody>
        </table>
      </section>
    </main>
  </div>
</template>

<script>
export default {
  data () {
    return {
      list: []
    }
  },
  async mounted () {
    const res = await fetch('http://localhost:9081/transactions/all')
    this.list = await res.json()
  }
}
</script>

<style scoped>
/* Layout Dashboard */
.dashboard-layout {
  display: flex;
  min-height: 100vh;
  font-family: "Inter", sans-serif;
  background: #f0f4f8;
  color: #333;
}

/* Sidebar */
.sidebar {
  width: 240px;
  background: #1f2937;
  color: white;
  padding: 20px 24px;
  display: flex;
  flex-direction: column;
  gap: 30px;
}

.logo {
  font-size: 1.8rem;
  font-weight: bold;
  text-align: center;
}

.nav-links {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.nav-button {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 12px 16px;
  background: #374151;
  color: #e5e7eb;
  border-radius: 8px;
  font-size: 1rem;
  font-weight: 500;
  text-decoration: none;
  transition: all 0.3s ease;
}

.nav-button:hover,
.nav-button.active {
  background: #4b5563;
  color: #ffffff;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.15);
}

.nav-button .icon {
  font-size: 1.2rem;
}

@media (max-width: 768px) {
  .nav-button {
    padding: 10px 12px;
    font-size: 0.95rem;
  }

  .nav-button .icon {
    font-size: 1rem;
  }
}

/* Main Area */
.main-content {
  flex: 1;
  padding: 30px 40px;
}

/* Header */
.dashboard-header {
  margin-bottom: 30px;
}

.dashboard-header h2 {
  font-size: 1.6rem;
  font-weight: 700;
  color: #1e293b;
}

/* Table */
.table-container {
  background: white;
  border-radius: 12px;
  box-shadow: 0 6px 18px rgba(0, 0, 0, 0.06);
  overflow-x: auto;
}

table {
  width: 100%;
  border-collapse: collapse;
  min-width: 700px;
}

thead {
  background-color: #f1f5f9;
}

th,
td {
  padding: 16px 20px;
  text-align: left;
  font-size: 0.95rem;
  border-bottom: 1px solid #e2e8f0;
}

th {
  font-weight: 600;
  color: #475569;
  text-transform: uppercase;
}

td {
  color: #334155;
}

/* Badge status */
.badge {
  padding: 6px 12px;
  border-radius: 9999px;
  font-weight: 600;
  text-transform: capitalize;
  font-size: 0.85rem;
  display: inline-block;
}

.badge.success {
  background: #d1fae5;
  color: #047857;
}

.badge.pending {
  background: #fef9c3;
  color: #b45309;
}

.badge.failed {
  background: #fee2e2;
  color: #b91c1c;
}

/* Responsive */
@media (max-width: 768px) {
  .dashboard-layout {
    flex-direction: column;
  }

  .sidebar {
    width: 100%;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    padding: 16px;
  }

  .nav-links {
    flex-direction: row;
    gap: 20px;
  }

  .main-content {
    padding: 20px;
  }

  table {
    min-width: 100%;
  }
}
</style>
