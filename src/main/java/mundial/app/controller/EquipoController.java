package mundial.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mundial.app.Entity.Equipo;
import mundial.app.exception.NotFoundException;
import mundial.app.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/equipos")
public class EquipoController {
    @Autowired
    private EquipoRepository equipoRepository;

    @GetMapping("/")
    public List<Equipo> getAllEquipos() {
        return equipoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Equipo getEquipoById(@PathVariable String id) {
        return equipoRepository.findById(id).orElseThrow(() -> new NotFoundException("Equipo no encontrado"));
    }

    @PostMapping("/")
    public Equipo saveEquipo(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Equipo equipo = mapper.convertValue(body, Equipo.class);
        return equipoRepository.save(equipo);
    }

    @PutMapping("/{id}")
    public Equipo updateEquipo(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Equipo equipo = mapper.convertValue(body, Equipo.class);
        equipo.setId(id);
        return equipoRepository.save(equipo);
    }

    @DeleteMapping("/{id}")
    public Equipo deleteEquipo(@PathVariable String id) {
        Equipo equipo = equipoRepository.findById(id).orElseThrow(() -> new NotFoundException("Equipo no encontrado"));
        equipoRepository.deleteById(id);
        return equipo;
    }
}
