import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

const API_DOMAIN = "http://localhost:8080"

export default defineConfig({
  plugins: [react()],
  server: {
    port: 3000,
    open: "/",
    proxy: {
      "/weather": API_DOMAIN,
    }
  }
})
