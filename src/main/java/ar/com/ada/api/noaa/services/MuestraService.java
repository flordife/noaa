package ar.com.ada.api.noaa.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.noaa.entities.Boya;
import ar.com.ada.api.noaa.entities.Muestra;

import ar.com.ada.api.noaa.repos.MuestraRepository;

@Service
public class MuestraService {

    @Autowired
    MuestraRepository repo;

    @Autowired
    BoyaService boyaService;

    public void crear(Muestra muestra) {
        repo.save(muestra);
    }

    public Muestra buscarPorBoyaId(Integer idBoya) {
        return repo.findById(idBoya);
    }

    public void bajaMuestraPorId(Integer id) {
        Muestra muestra = this.buscarMuestraPorId(id);

        muestra.setColorLuz("AZUL");
        repo.save(muestra);
    }

    private Muestra buscarMuestraPorId(Integer id) {
        return repo.findByMuestraId(id);
    }

    public List<Muestra> traerMuestrasPorBoya(Integer idBoya) {
        Boya boya = boyaService.buscarPorId(idBoya);
        return boya.getMuestras();
    }
    
    
}
