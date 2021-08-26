package ar.com.ada.api.noaa.controllers;

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
    
}
