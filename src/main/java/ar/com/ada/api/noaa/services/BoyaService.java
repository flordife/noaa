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

    public Boya crear(double longitudInstalacion, double latitudInstalacion) {
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

    // Revisar la lógica de este método

    /*
     * Nota: Si cuando se carga una muestra nueva en una boya, la alturaNivelDelMar
     * es de MENOS de -50(menos 50) o de MAS de +50 (más 50), debera devolver
     * “AMARILLO” en el color. En el caso de que sea menor a -100 o mayor a +100 el
     * color deberá ser ROJO. Si no, el color deberá devolver es VERDE
     */
    public Boya calcularColor(Boya boya, Double alturaNivelDelMar) {

        if ((alturaNivelDelMar <= -50 && alturaNivelDelMar >= -100)
                || (alturaNivelDelMar >= 50 && alturaNivelDelMar <= 100)) {
            boya.setColorLuz("AMARILLO");
        } else if (alturaNivelDelMar <= -100 || alturaNivelDelMar >= 100) {
            boya.setColorLuz("ROJO");
        } else
            boya.setColorLuz("VERDE");

        return boya;
    }

}
