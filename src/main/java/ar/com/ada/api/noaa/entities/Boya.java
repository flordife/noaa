package ar.com.ada.api.noaa.entities;

import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "boya")
public class Boya {

    @Id
    @Column(name = "boya_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boyaId;

    @Column(name = "color_luz")
    private String colorLuz;

    @Column(name = "longitud_instalacion")
    private double longitudInstalacion;

    @Column(name = "latitud_instalacion")
    private double latitudInstalacion;

    @OneToMany(mappedBy = "boya", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Muestra> muestras = new ArrayList<>();

    /*private List<Muestra> muestrasPorColor = new ArrayList<>();

    public List<Muestra> getMuestrasPorColor() {
        return muestrasPorColor;
    }

    public void setMuestrasPorColor(List<Muestra> muestrasPorColor) {
        this.muestrasPorColor = muestrasPorColor;
    }*/

    public Integer getBoyaId() {
        return boyaId;
    }

    public void setBoyaId(Integer boyaId) {
        this.boyaId = boyaId;
    }

    public String getColorLuz() {
        return colorLuz;
    }

    public void setColorLuz(String colorLuz) {
        this.colorLuz = colorLuz;
    }

    public double getLongitudInstalacion() {
        return longitudInstalacion;
    }

    public void setLongitudInstalacion(double longitudInstalacion) {
        this.longitudInstalacion = longitudInstalacion;
    }

    public double getLatitudInstalacion() {
        return latitudInstalacion;
    }

    public void setLatitudInstalacion(double latitudInstalacion) {
        this.latitudInstalacion = latitudInstalacion;
    }

    public List<Muestra> getMuestras() {
        return muestras;
    }

    public void setMuestras(List<Muestra> muestras) {
        this.muestras = muestras;
    }

    public void agregarMuestra(Muestra muestra) {
        this.muestras.add(muestra);
    }

    /*
     * public enum ColorBoyaEnum{
     * 
     * ROJO(1), AMARILLO(2), VERDE(3), AZUL(4);
     * 
     * private final int value;
     * 
     * private ColorBoyaEnum(int value) { this.value = value; }
     * 
     * public int getValue() { return value; }
     * 
     * public static ColorBoyaEnum parse(int id) { ColorBoyaEnum status = null; //
     * Default for (ColorBoyaEnum item : ColorBoyaEnum.values()) { if
     * (item.getValue() == id) { status = item; break; } } return status; } }
     */

}
