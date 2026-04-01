import { useEffect, useState } from "react";
import axios from "axios";
import UserCard from "./UserCard";

// Importa un archivo CSS para los estilos del contenedor
import "./UserList.css";

function UserList() {
  const [usuarios, setUsuarios] = useState([]);
  const [isLoading, setIsLoading] = useState(true); // Nuevo estado para la carga
  const [error, setError] = useState(null); // Nuevo estado para errores

  useEffect(() => {
    const fetchUsuarios = async () => {
      try {
        const response = await axios.get("/api/usuarios");
        setUsuarios(response.data);
      } catch (err) {
        console.error("Error al obtener usuarios:", err);
        setError(
          "No se pudieron cargar los usuarios. Inténtalo de nuevo más tarde.",
        ); // Mensaje amigable para el usuario
      } finally {
        setIsLoading(false); // Finaliza la carga, independientemente del resultado
      }
    };
    fetchUsuarios();
  }, []);

  if (isLoading) {
    return <div className='user-list-message'>Cargando usuarios...</div>;
  }

  if (error) {
    return <div className='user-list-message error'>{error}</div>;
  }

  // Si no hay usuarios después de la carga y sin error
  if (usuarios.length === 0) {
    return (
      <div className='user-list-message'>No hay usuarios disponibles.</div>
    );
  }

  return (
    <div>
      <h2>Lista de Jugadores</h2>
      {/* Usa la clase CSS para el contenedor */}
      <div className='user-cards-container'>
        {usuarios.map((usuario) => (
          <UserCard key={usuario.id_usuario} usuario={usuario} />
        ))}
      </div>
    </div>
  );
}

export default UserList;
