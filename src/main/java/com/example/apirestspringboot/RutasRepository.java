package com.example.apirestspringboot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RutasRepository extends JpaRepository<Ruta, Long> {

    public Ruta getRutaById(Long id);

    public List<Ruta> getRutaByNombre(String nombre);

    public List<Ruta> getRutaByOrigen(String origen);

    public List<Ruta> getRutaByDestino(String destino);

    public List<Ruta> getRutaByNumParadas(int numParadas);

    public List<Ruta> getRutaByInconveniente(int inconveniente);

    @Query("Select r.nombre from Ruta r")
    public List<String> getAllNombres();

    @Query("Select count(r.id) from Ruta r")
    public Integer getCantidadRutas();

}
