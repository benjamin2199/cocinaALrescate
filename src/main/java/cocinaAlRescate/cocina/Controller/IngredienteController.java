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

import cocinaAlRescate.cocina.Model.Ingrediente;
import cocinaAlRescate.cocina.service.IngredienteService;

@RestController
@RequestMapping("api/v1/ingrediente")
public class IngredienteController {
@Autowired
    private IngredienteService ingredienteService;

    @GetMapping
    public ResponseEntity<List<Ingrediente>> listar() {
        List<Ingrediente> ingredientes = ingredienteService.list();
        if (ingredientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ingredientes);
}
    @PostMapping("/guardar")
    public ResponseEntity<Ingrediente> guardaringrediente(@RequestBody Ingrediente ingrediente) {
        Ingrediente nuevoingrediente = ingredienteService.guardaIngrediente(ingrediente);
        return new ResponseEntity<>(nuevoingrediente, HttpStatus.CREATED);
    }

    @GetMapping("/buscar/{idingrediente}")
    public ResponseEntity<Ingrediente> buscaringrediente(@RequestParam("idingrediente") int idingrediente) {
        Ingrediente ingrediente = ingredienteService.buscarporidIngrediente(idingrediente);
        if (ingrediente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingrediente);
    }
    @PutMapping("/actualizar")
    public ResponseEntity<Ingrediente> actualizaringrediente(@RequestBody Ingrediente ingrediente) {
        Ingrediente ingredienteActualizado = ingredienteService.guardaIngrediente(ingrediente);
        if (ingredienteActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredienteActualizado);
    }
    @DeleteMapping("/eliminar/{idingrediente}")
    public ResponseEntity<String> eliminaringrediente(@PathVariable("idingrediente") int idingrediente){
        ingredienteService.eliminarIngrediente(idingrediente);
        return ResponseEntity.ok("ingrediente eliminado");
    }
}
