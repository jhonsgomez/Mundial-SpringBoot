package mundial.app.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Document
public class Partido {
    @Id
    private String id;
    @DocumentReference
    private Equipo equipo1;
    @DocumentReference
    private Equipo equipo2;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;
    private Integer golesEquipo1;
    private Integer golesEquipo2;

    public Partido() {}

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Equipo getEquipo1() {
        return equipo1;
    }
    public void setEquipo1(Equipo equipo1) {
        this.equipo1 = equipo1;
    }
    public Equipo getEquipo2() {
        return equipo2;
    }
    public void setEquipo2(Equipo equipo2) {
        this.equipo2 = equipo2;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Integer getGolesEquipo1() {
        return golesEquipo1;
    }
    public void setGolesEquipo1(Integer golesEquipo1) {
        this.golesEquipo1 = golesEquipo1;
    }
    public Integer getGolesEquipo2() {
        return golesEquipo2;
    }
    public void setGolesEquipo2(Integer golesEquipo2) {
        this.golesEquipo2 = golesEquipo2;
    }
}
