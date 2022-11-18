package mundial.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mundial.app.Entity.Partido;
import mundial.app.exception.NotFoundException;
import mundial.app.repository.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/partidos")
public class PartidoController {
    @Autowired
    private PartidoRepository partidoRepository;

    @GetMapping("/")
    public List<Partido> getAllPartidos() {
        return partidoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Partido getPartidoById(@PathVariable String id) {
        return partidoRepository.findById(id).orElseThrow(() -> new NotFoundException("Partido no Encontrado"));
    }

    @PostMapping("/")
    public Partido savePartido(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Partido partido = mapper.convertValue(body, Partido.class);
        return partidoRepository.save(partido);
    }

    @PutMapping("/{id}")
    public Partido updatePartido(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Partido partido = mapper.convertValue(body, Partido.class);
        partido.setId(id);
        return partidoRepository.save(partido);
    }

    @DeleteMapping("/{id}")
    public Partido deletePartido(@PathVariable String id) {
        Partido partido = partidoRepository.findById(id).orElseThrow(() -> new NotFoundException("Partido no encontrado"));
        partidoRepository.deleteById(id);
        return partido;
    }
}
