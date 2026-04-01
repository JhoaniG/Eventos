import "./UserCard.css";
// Importa la imagen local directamente usando el alias configurado en vite.config.js
import playerImage from "../assets/player-placeholder.png";

function UserCard({ usuario }) {
  const { id_usuario, apellido, cedula, edad, nombre } = usuario;
  const nombreCompleto = `${nombre} ${apellido}`;

  // URLs de referencia
  const flagImage =
    "https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/Flag_of_Colombia.svg/960px-Flag_of_Colombia.svg.png";
  const clubImage = "https://a.espncdn.com/i/teamlogos/soccer/500/83.png";

  return (
    <div className='fut-card-wrapper'>
      <div className='fut-card-base'>
        <div className='fut-top'>
          <div className='fut-info-col'>
            <div className='fut-rating'>{id_usuario}</div>
            <div className='fut-position'>USR</div>
            <img src={flagImage} alt='nacionalidad' className='fut-icon' />
            <img src={clubImage} alt='club' className='fut-icon' />
          </div>
          <img
            src={playerImage}
            alt={nombreCompleto}
            className='fut-player-img'
          />
        </div>

        <div className='fut-bottom'>
          <div className='fut-name'>{nombreCompleto}</div>
          <div className='fut-detail'>DOC {cedula}</div>
          <div className='fut-detail'>EDAD {edad}</div>
        </div>
      </div>
    </div>
  );
}

export default UserCard;
