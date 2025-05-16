import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import GenerateQRView from '../views/GenerateQRView.vue'
import ScanQRView from '../views/ScanQRView.vue'
import TransactionsView from '../views/TransactionsView.vue'
import ScanPageView from '@/views/ScanPageView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/generate-qr',
    name: 'generate-qr',
    component: GenerateQRView
  },
  {
    path: '/scan-qr',
    name: 'ScanQR',
    component: ScanQRView
  },
  {
    path: '/scan',
    name: 'ScanPage',
    component: ScanPageView
  },
  {
    path: '/transactions',
    name: 'transactions',
    component: TransactionsView
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
