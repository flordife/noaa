package ar.com.ada.api.noaa.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ar.com.ada.api.noaa.entities.Muestra;
import ar.com.ada.api.noaa.models.request.InfoMuestraNueva;
import ar.com.ada.api.noaa.models.response.*;
import ar.com.ada.api.noaa.services.BoyaService;
import ar.com.ada.api.noaa.services.MuestraService;

@RestController
public class MuestraController {

    @Autowired
    MuestraService service;

    @Autowired
    BoyaService boyaService;

    @PostMapping("/muestras")
    public ResponseEntity<MuestraResponse> crear(@RequestBody InfoMuestraNueva infoMuestraNueva) {

        MuestraResponse respuesta = new MuestraResponse();
        Muestra muestra = service.crear(infoMuestraNueva.horario, infoMuestraNueva.latitud, infoMuestraNueva.boyaId,
                infoMuestraNueva.alturaNivelDelMar, infoMuestraNueva.longitud, infoMuestraNueva.matricula);

        respuesta.id = muestra.getMuestraId();
        respuesta.color = muestra.getBoya().getColorLuz();

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/muestras/boyas/{idBoya}")
    public ResponseEntity<List<Muestra>> traerMuestrasPorIdDeBoya(@PathVariable Integer idBoya) {
        List<Muestra> muestras = service.traerMuestrasPorBoya(idBoya);

        return ResponseEntity.ok(muestras);
    }

    @DeleteMapping("/muestras/{id}")
    public ResponseEntity<GenericResponse> bajaMuestra(@PathVariable Integer id) {
        service.bajaMuestraPorId(id);

        GenericResponse respuesta = new GenericResponse();
        respuesta.isOk = true;
        respuesta.message = "La muestra ha sido resetada";

        return ResponseEntity.ok(respuesta);
    }

}
