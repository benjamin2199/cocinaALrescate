package cocinaAlRescate.cocina.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import cocinaAlRescate.cocina.Model.Notificacion;
import cocinaAlRescate.cocina.service.NotIficacionService;

import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("api/v1/notificacion")
public class NotificacionController {
     @Autowired
    private NotIficacionService notificacionService;

    @GetMapping
    public ResponseEntity<List<Notificacion>> listar() {
        List<Notificacion> notificacions = notificacionService.list();
        if (notificacions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(notificacions);
}
    @PostMapping("/guardar")
    public ResponseEntity<Notificacion> guardarnotificacion(@RequestBody Notificacion notificacion) {
        Notificacion nuevonotificacion = notificacionService.guardarNotificacion(notificacion);
        return new ResponseEntity<>(nuevonotificacion, HttpStatus.CREATED);
    }

    @GetMapping("/buscar/{idnotificacion}")
    public ResponseEntity<Notificacion> buscarnotificacion(@RequestParam("idnotificacion") int idnotificacion) {
        Notificacion notificacion = notificacionService.buscarporidNotificacion(idnotificacion);
        if (notificacion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(notificacion);
    }
    @PutMapping("/actualizar")
    public ResponseEntity<Notificacion> actualizarnotificacion(@RequestBody Notificacion notificacion) {
        Notificacion notificacionActualizado = notificacionService.guardarNotificacion(notificacion);
        if (notificacionActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(notificacionActualizado);
    }
    @DeleteMapping("/eliminar/{idnotificacion}")
    public ResponseEntity<String> eliminarnotificacion(@PathVariable("idnotificacion") int idnotificacion){
        notificacionService.eliminarNotificacion(idnotificacion);
        return ResponseEntity.ok("notificacion eliminado");
    }
}
