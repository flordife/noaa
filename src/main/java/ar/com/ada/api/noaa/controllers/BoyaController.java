package ar.com.ada.api.noaa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.noaa.entities.Boya;
import ar.com.ada.api.noaa.models.request.InfoActualizadaBoya;
import ar.com.ada.api.noaa.models.request.InfoBoyaNueva;
import ar.com.ada.api.noaa.models.response.GenericResponse;
import ar.com.ada.api.noaa.services.BoyaService;

@RestController
public class BoyaController {

    @Autowired
    BoyaService service;

    @PostMapping("/boyas")
    public ResponseEntity<GenericResponse> crear(@RequestBody InfoBoyaNueva infoBoyaNueva) {

        GenericResponse respuesta = new GenericResponse();
        Boya boya = new Boya(infoBoyaNueva.latitudInstalacion, infoBoyaNueva.longitudInstalacion);

        service.crear(boya);
        respuesta.isOk = true;
        respuesta.id = boya.getBoyaId();
        respuesta.message = "La boya ha sido creada con exito";

        return ResponseEntity.ok(respuesta);

    }

    @GetMapping("/boyas")
    public ResponseEntity<List<Boya>> traerTodas() {
        return ResponseEntity.ok(service.traerTodas());
    }

    @GetMapping("/boyas/{id}")
    public ResponseEntity<Boya> traerBoyasPorId(@PathVariable Integer id) {
        Boya boya = service.buscarPorId(id);
        if (boya == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(boya);

    }

    @PutMapping("/boyas/{id}")
    public ResponseEntity<GenericResponse> actualizarBoya(@PathVariable Integer id,
            @RequestBody InfoActualizadaBoya infoActualizadaBoya) {

        Boya boya = service.buscarPorId(id);
        boya.setColorLuz(infoActualizadaBoya.color);
        service.guardar(boya);

        GenericResponse respuesta = new GenericResponse();
        respuesta.isOk = true;
        respuesta.message = "El color de la boya ha sido actualizado";

        return ResponseEntity.ok(respuesta);
    }

}
