package cocinaAlRescate.cocina.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cocinaAlRescate.cocina.Model.Receta;
import cocinaAlRescate.cocina.service.RecetaService;


@RestController
@RequestMapping("api/v1/receta")

public class RecetaController {

@Autowired
    private RecetaService recetaService;

    @GetMapping
    public ResponseEntity<List<Receta>> listar() {
        List<Receta> recetas = recetaService.list();
        if (recetas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(recetas);
}
    @PostMapping("/guardar")
    public ResponseEntity<Receta> guardarreceta(@RequestBody Receta receta) {
        Receta nuevoreceta = recetaService.guardaReceta(receta);
        return new ResponseEntity<>(nuevoreceta, HttpStatus.CREATED);
    }

    @GetMapping("/buscar/{idreceta}")
    public ResponseEntity<Receta> buscarreceta(@RequestParam("idreceta") int idreceta) {
        Receta receta = recetaService.buscarporidReceta(idreceta);
        if (receta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(receta);
    }
    @PutMapping("/actualizar")
    public ResponseEntity<Receta> actualizarreceta(@RequestBody Receta receta) {
        Receta recetaActualizado = recetaService.guardaReceta(receta);
        if (recetaActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recetaActualizado);
    }
    @DeleteMapping("/eliminar/{idreceta}")
    public ResponseEntity<String> eliminarreceta(@PathVariable("idreceta") int idreceta){
        recetaService.eliminarReceta(idreceta);
        return ResponseEntity.ok("receta eliminado");
    }

}
