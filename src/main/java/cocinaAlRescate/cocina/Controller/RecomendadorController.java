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

import cocinaAlRescate.cocina.Model.Recomendador;
import cocinaAlRescate.cocina.service.RecomendadorService;

@RestController
@RequestMapping("api/v1/recomendador")
public class RecomendadorController {

      @Autowired
    private RecomendadorService recomendadorService;

    @GetMapping
    public ResponseEntity<List<Recomendador>> listar() {
        List<Recomendador> recomendado = recomendadorService.list();
        if (recomendado.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(recomendado);
}
    @PostMapping("/guardar")
    public ResponseEntity<Recomendador> guardarrecomendador(@RequestBody Recomendador recomendador) {
        Recomendador nuevorecomendador = recomendadorService.guardaRecomendador(recomendador);
        return new ResponseEntity<>(nuevorecomendador, HttpStatus.CREATED);
    }

    @GetMapping("/buscar/{idrecomendador}")
    public ResponseEntity<Recomendador> buscarrecomendador(@RequestParam("idrecomendador") int idrecomendador) {
        Recomendador recomendador = recomendadorService.buscarporidRecomendador(idrecomendador);
        if (recomendador == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recomendador);
    }
    @PutMapping("/actualizar")
    public ResponseEntity<Recomendador> actualizarecomendador(@RequestBody Recomendador recomendador) {
        Recomendador recomendadorActualizado = recomendadorService.guardaRecomendador(recomendador);
        if (recomendadorActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recomendadorActualizado);
    }
    @DeleteMapping("/eliminar/{idrecomendador}")
    public ResponseEntity<String> eliminarrecomendador(@PathVariable("idrecomendador") int idrecomendador){
        recomendadorService.eliminarRecomendador(idrecomendador);
        return ResponseEntity.ok("recomendador eliminado");
    
}
}
