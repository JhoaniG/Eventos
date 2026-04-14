package com.Eventos.Teo.repositorios;

import com.Eventos.Teo.Model.Jugadores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorRepositorio extends JpaRepository<Jugadores, Long> {


}
