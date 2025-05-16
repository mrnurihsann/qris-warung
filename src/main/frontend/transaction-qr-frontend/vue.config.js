const { defineConfig } = require('@vue/cli-service')

module.exports = defineConfig({
  transpileDependencies: true,

  // Output direktori ke META-INF/resources agar Quarkus bisa menemukannya
  outputDir: '../../resources/META-INF/resources',

  // Tambahkan ini jika Vue.js akan dijalankan terpisah saat development
  devServer: {
    port: 5173,
    proxy: {
      '/api': {
        target: 'http://localhost:9081',
        ws: true,
        changeOrigin: true
      }
    }
  }
})
