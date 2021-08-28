package ar.com.ada.api.noaa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.noaa.entities.Boya;
import ar.com.ada.api.noaa.repos.BoyaRepository;

@Service
public class BoyaService {

    @Autowired
    BoyaRepository repo;

    public Boya crear(double longitudInstalacion, double latitudInstalacion){
        Boya boya = new Boya();
        boya.setColorLuz("");
        boya.setLatitudInstalacion(latitudInstalacion);
        boya.setLongitudInstalacion(longitudInstalacion);
        return repo.save(boya);
    }

    public List<Boya> traerTodas() {
        return repo.findAll();
    }

    public Boya buscarPorId(Integer id) {
        return repo.findByBoyaId(id);
    }

    public void guardar(Boya boya) {
        repo.save(boya);
    }

    public Boya calcularColor(Boya boya, Double alturaNivelDelMar){
        
        if (alturaNivelDelMar <= -50 || alturaNivelDelMar >= 50){
            boya.setColorLuz("AMARILLO");
        }
        else if(alturaNivelDelMar <=-100 || alturaNivelDelMar >= 100){
            boya.setColorLuz("ROJO");
        }
        else boya.setColorLuz("VERDE");

        return boya;
    }
    
}
