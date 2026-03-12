import { useEffect, useState } from 'react'
import axios from 'axios'
import UserCard from './UserCard'

function UserList() {
  const [usuarios, setUsuarios] = useState([])

  useEffect(() => {
    // Gracias al proxy configurado en vite.config.js, 
    // '/api/usuarios' se redirige automáticamente a 'http://localhost:8080/api/usuarios'
    axios.get('/api/usuarios')
      .then((response) => {
        setUsuarios(response.data)
      })
      .catch((error) => {
        console.error('Error al obtener usuarios:', error)
      })
  }, [])

  return (
    <div>
      <h2>Lista de Jugadores</h2>
      <div style={{ display: 'flex', flexWrap: 'wrap', gap: '20px', justifyContent: 'center', padding: '20px' }}>
        {usuarios.map((usuario) => (
          <UserCard key={usuario.id_usuario} usuario={usuario} />
        ))}
      </div>
    </div>
  )
}

export default UserList
