import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";
import path from "path"; // Importa el módulo 'path'
import { fileURLToPath } from "url";

const __dirname = path.dirname(fileURLToPath(import.meta.url));

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  resolve: {
    alias: {
      "@": path.resolve(__dirname, "./src/assets"), // Alias '@' para la carpeta 'src/assets'
    },
  },
  server: {
    proxy: {
      "/api": {
        target: "http://localhost:8080", // <--- ¡IMPORTANTE! Cambia esto a la URL de tu backend
        changeOrigin: true,
      },
    },
  },
});
