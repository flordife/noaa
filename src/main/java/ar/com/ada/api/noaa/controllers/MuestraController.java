package ar.com.ada.api.noaa.controllers;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.noaa.entities.Boya;
import ar.com.ada.api.noaa.entities.Muestra;
import ar.com.ada.api.noaa.models.request.InfoMuestraNueva;
import ar.com.ada.api.noaa.models.response.GenericResponse;
import ar.com.ada.api.noaa.models.response.MuestraResponse;
import ar.com.ada.api.noaa.services.BoyaService;
import ar.com.ada.api.noaa.services.MuestraService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class MuestraController {

    @Autowired 
    MuestraService service;

    @Autowired
    BoyaService boyaService;

    @PostMapping("/muestras")
    public ResponseEntity<MuestraResponse> crear(@RequestBody InfoMuestraNueva infoMuestraNueva){
        
        MuestraResponse respuesta = new MuestraResponse();
        Muestra muestra = new Muestra(infoMuestraNueva.horario, infoMuestraNueva.latitud, infoMuestraNueva.alturaNivelDelMar, infoMuestraNueva.longitud, infoMuestraNueva.matricula);

        Boya boya = boyaService.buscarPorId(infoMuestraNueva.boyaId);
        muestra.setBoya(boya);

        service.crear(muestra);

        respuesta.id = boya.getBoyaId();
        respuesta.color = "AMARILLO";
        
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/muestras/boyas{idBoya}")
    public ResponseEntity<Muestra> traerMuestrasPorIdDeBoya(@PathVariable Integer idBoya){
        Muestra muestra = service.buscarPorBoyaId(idBoya);

        return ResponseEntity.ok(muestra);
    }

    @DeleteMapping("/muestras/{id}")
    public ResponseEntity<GenericResponse> bajaMuestra(@PathVariable Integer id){
        service.bajaMuestraPorId(id);

        GenericResponse respuesta = new GenericResponse();
        respuesta.isOk = true;
        respuesta.message = "La muestra ha sido resetada";

        return ResponseEntity.ok(respuesta);
    }    
    
}
