package ar.com.ada.api.noaa.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.ada.api.noaa.entities.Boya;
import ar.com.ada.api.noaa.entities.Muestra;
import ar.com.ada.api.noaa.models.response.MuestraPorColor;
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

    public Muestra crear(Date horario, double latitud, Integer boyaId, double alturaNivelDelMar, double longitud,
            String matricula) {
        Muestra muestra = new Muestra();
        Boya boya = boyaService.buscarPorId(boyaId);
        muestra.setAlturaNivelDelMar(alturaNivelDelMar);
        muestra.setBoya(boyaService.calcularColor(boya, alturaNivelDelMar));
        muestra.setHorarioMuestra(horario);
        muestra.setLatitud(latitud);
        muestra.setLongitud(longitud);
        boya.agregarMuestra(muestra);

        return repo.save(muestra);
    }

    public void bajaMuestraPorId(Integer id) {
        Muestra muestra = repo.findByMuestraId(id);
        Boya boya = muestra.getBoya();
        boya.setColorLuz("AZUL");
        boyaService.guardar(boya);
        repo.save(muestra);
    }

    public List<Muestra> traerMuestrasPorBoya(Integer idBoya) {
        Boya boya = boyaService.buscarPorId(idBoya);
        return boya.getMuestras();
    }

    public List<MuestraPorColor> traerMuestrasPorColorDeBoya(String colorBoya){
        List<MuestraPorColor> muestrasPorColor = new ArrayList<>();
        MuestraPorColor muestraPorColor= new MuestraPorColor();
        
        for (Muestra muestra : repo.findAll()) {            
            
            if (((muestra.getBoya().getColorLuz())).equals(colorBoya)){ 
                muestraPorColor.boyaId = muestra.getBoya().getBoyaId();
                muestraPorColor.horario=muestra.getHorarioMuestra();
                muestraPorColor.alturaNivelDelMar=muestra.getAlturaNivelDelMar();                            
                
                muestrasPorColor.add(muestraPorColor);
                
            }            

        }
        return muestrasPorColor;
        

    
    }
}
