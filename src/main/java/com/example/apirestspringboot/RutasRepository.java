package com.example.apirestspringboot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Interfaz de repositorio para acceder a los datos de las rutas de autobus
 */
public interface RutasRepository extends JpaRepository<Ruta, Long> {

    /**
     * Obtiene una ruta de autobus por su ID
     * @param id El ID de la ruta
     * @return La ruta de autobus con el ID especificado
     */
    public Ruta getRutaById(Long id);

    /**
     * Obtiene todas las rutas de autobus con un nombre especifico
     * @param nombre El nombre de la ruta
     * @return Lista de rutas de autobus con el nombre especificado
     */
    public List<Ruta> getRutaByNombre(String nombre);

    /**
     * Obtiene todas las rutas de autobus con un origen especifico
     * @param origen La ciudad de origen de las rutas
     * @return Lista de rutas de autobus con el origen especificado
     */
    public List<Ruta> getRutaByOrigen(String origen);

    /**
     * Obtiene todas las rutas de autobus con un destino especifico
     * @param destino La ciudad de destino de las rutas
     * @return Lista de rutas de autobus con el destino especificado
     */
    public List<Ruta> getRutaByDestino(String destino);

    /**
     * Obtiene todas las rutas de autobus con un número especifico de paradas
     * @param numParadas El número de paradas en las rutas
     * @return Lista de rutas de autobus con el número de paradas especificado
     */
    public List<Ruta> getRutaByNumParadas(int numParadas);

    /**
     * Obtiene todas las rutas de autobus con un nivel de inconveniente especifico
     * @param inconveniente El nivel de inconveniente de las rutas
     * @return Lista de rutas de autobus con el nivel de inconveniente especificado
     */
    public List<Ruta> getRutaByInconveniente(int inconveniente);


    /**
     * Obtiene todos los nombres de las rutas de autobus
     * @return Lista de nombres de las rutas de autobus
     */
    @Query("Select r.nombre from Ruta r")
    public List<String> getAllNombres();

    /**
     * Obtiene la cantidad total de rutas de autobus
     * @return La cantidad total de rutas de autobus
     */
    @Query("Select count(r.id) from Ruta r")
    public Integer getCantidadRutas();

}
