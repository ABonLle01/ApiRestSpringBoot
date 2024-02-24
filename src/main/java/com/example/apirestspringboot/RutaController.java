package com.example.apirestspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("api/rutas")
public class RutaController {

    @Autowired
    private RutasRepository repository;

    @Autowired
    private SecurityService security;

    @GetMapping("/")
    public List<Ruta> getAll(){
        return repository.findAll();
    }

    @GetMapping("/id/{id}")
    public Ruta getRutaById(@PathVariable Long id){
        return repository.getRutaById(id);
    }

    @GetMapping("/nombre/{nombre}")
    public List<Ruta> getRutaByNombre(@PathVariable String nombre){
        return repository.getRutaByNombre(nombre);
    }

    @GetMapping("/origen/{origen}")
    public List<Ruta> getRutasByOrigen(@PathVariable String origen){
        return repository.getRutaByOrigen(origen);
    }

    @GetMapping("/destino/{destino}")
    public List<Ruta> getRutasByDestino(@PathVariable String destino){
        return repository.getRutaByDestino(destino);
    }

    @GetMapping("/paradas/{paradas}")
    public List<Ruta> getRutaByNumParadas(@PathVariable Integer paradas){
        return repository.getRutaByNumParadas(paradas);
    }

    @GetMapping("/inconveniente/{inconveniente}")
    public List<Ruta> getRutaByInconveniente(@PathVariable Integer inconveniente){
        return repository.getRutaByInconveniente(inconveniente);
    }

    @GetMapping("/rutas")
    public List<String> getAllNombres(){
        return repository.getAllNombres();
    }

    @GetMapping("/total")
    public Integer getCantidadRutas(){
        return repository.getCantidadRutas();
    }


    @PostMapping("/post")
    public ResponseEntity<Ruta> nueva(@RequestBody Ruta ruta, @RequestParam String token){
        if(security.validateToken(token)){
            return new ResponseEntity<>(repository.save(ruta), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

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
