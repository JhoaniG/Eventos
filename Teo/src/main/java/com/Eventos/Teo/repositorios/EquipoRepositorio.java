package com.Eventos.Teo.repositorios;

import com.Eventos.Teo.Model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRepositorio extends JpaRepository<Equipo, Long> {



}
