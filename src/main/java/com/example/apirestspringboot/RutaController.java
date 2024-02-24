package com.example.apirestspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para gestionar las rutas de autobus
 */
@Controller
@RestController
@RequestMapping("api/rutas")
public class RutaController {

    @Autowired
    private RutasRepository repository;

    @Autowired
    private SecurityService security;

    /**
     * Obtiene todas las rutas de autobus
     */
    @GetMapping("/")
    public List<Ruta> getAll(){
        return repository.findAll();
    }

    /**
     * Obtiene una ruta de autobus por su ID
     * @param id El ID de la ruta
     * @return La ruta de autobús
     */
    @GetMapping("/id/{id}")
    public Ruta getRutaById(@PathVariable Long id){
        return repository.getRutaById(id);
    }

    /**
     * Obtiene todas las rutas de autobus con un nombre especifico
     * @param nombre El nombre de la ruta
     * @return Lista de rutas de autobús con el nombre especificado
     */
    @GetMapping("/nombre/{nombre}")
    public List<Ruta> getRutaByNombre(@PathVariable String nombre){
        return repository.getRutaByNombre(nombre);
    }

    /**
     * Obtiene todas las rutas de autobus con un origen especifico
     * @param origen El lugar de origen de la ruta
     * @return Lista de rutas de autobús con el origen especificado
     */
    @GetMapping("/origen/{origen}")
    public List<Ruta> getRutasByOrigen(@PathVariable String origen){
        return repository.getRutaByOrigen(origen);
    }

    /**
     * Obtiene todas las rutas de autobus con un destino especifico
     * @param destino El lugar de destino de la ruta
     * @return Lista de rutas de autobús con el destino especificado
     */
    @GetMapping("/destino/{destino}")
    public List<Ruta> getRutasByDestino(@PathVariable String destino){
        return repository.getRutaByDestino(destino);
    }

    /**
     * Obtiene todas las rutas de autobus con un paradas especifico
     * @param paradas El numero de paradas de la ruta
     * @return Lista de rutas de autobús con el paradas especificado
     */
    @GetMapping("/paradas/{paradas}")
    public List<Ruta> getRutaByNumParadas(@PathVariable Integer paradas){
        return repository.getRutaByNumParadas(paradas);
    }

    /**
     * Obtiene todas las rutas de inconveniente con un inconveniente especifico
     * @param inconveniente El nivel de inconveniente de la ruta (0 si no hay inconveniente y 1 si existe algun problema)
     * @return Lista de rutas de autobús con el inconveniente especificado
     */
    @GetMapping("/inconveniente/{inconveniente}")
    public List<Ruta> getRutaByInconveniente(@PathVariable Integer inconveniente){
        return repository.getRutaByInconveniente(inconveniente);
    }

    /**
     * Obtiene todos los nombres de las rutas de autobus
     * @return Lista de nombres de las rutas de autobus
     */
    @GetMapping("/rutas")
    public List<String> getAllNombres(){
        return repository.getAllNombres();
    }

    /**
     * Obtiene la cantidad total de rutas de autobus
     * @return La cantidad total de rutas de autobus
     */
    @GetMapping("/total")
    public Integer getCantidadRutas(){
        return repository.getCantidadRutas();
    }


    /**
     * Crea una nueva ruta de autobus
     * @param ruta La nueva ruta de autobús a crear
     * @param token El token de autenticacion
     * @return La respuesta HTTP que indica el exito o fracaso de la creacion de la ruta
     */
    @PostMapping("/post")
    public ResponseEntity<Ruta> nueva(@RequestBody Ruta ruta, @RequestParam String token){
        if(security.validateToken(token)){
            return new ResponseEntity<>(repository.save(ruta), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Actualiza una ruta de autobús existente
     * @param id El ID de la ruta de autobus a actualizar
     * @param r Los datos actualizados de la ruta de autobús
     * @param token El token de autenticacion
     * @return La respuesta HTTP que indica el exito o fracaso de la actualizacion de la ruta
     */
    @PutMapping("/{id}")
    public ResponseEntity<Ruta> put(@PathVariable Long id, @RequestBody Ruta r, @RequestParam String token) {
        if (!security.validateToken(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            var ruta = new Ruta();
            var rutaSeleccionada = repository.findById(id);

            if (rutaSeleccionada.isEmpty()) {
                ruta = r;
            } else {
                ruta = rutaSeleccionada.get();
                ruta.setNombre(r.getNombre());
                ruta.setOrigen(r.getOrigen());
                ruta.setDestino(r.getDestino());
                ruta.setNumParadas(r.getNumParadas());
                ruta.setInconveniente(r.getInconveniente());

            }
            return new ResponseEntity<>(repository.save(ruta), HttpStatus.OK);
        }
    }

    /**
     * Elimina una ruta de autobus existente
     * @param id El ID de la ruta de autobus a eliminar
     * @param token El token de autenticacion
     * @return La respuesta HTTP que indica el exito o fracaso de la eliminacion de la ruta
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Ruta> delete(@PathVariable Long id, @RequestParam String token) {
        ResponseEntity<Ruta> response = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        if (security.validateToken(token)) {
            Ruta salida = new Ruta();
            if (repository.existsById(id)) {
                salida = repository.findById(id).get();
                repository.deleteById(id);
                response = new ResponseEntity<>(salida, HttpStatus.OK);
            } else {
                response = new ResponseEntity<>(salida, HttpStatus.NOT_FOUND);
            }
        }

        return response;
    }


}
