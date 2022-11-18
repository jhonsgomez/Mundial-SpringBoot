package mundial.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mundial.app.Entity.Grupo;
import mundial.app.exception.NotFoundException;
import mundial.app.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/grupos")
public class GrupoController {
    @Autowired
    private GrupoRepository grupoRepository;

    @GetMapping("/")
    public List<Grupo> getAllGrupos() {
        return grupoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Grupo getGrupoById(@PathVariable String id) {
        return grupoRepository.findById(id).orElseThrow(() -> new NotFoundException("Grupo no encontrado"));
    }

    @PostMapping("/")
    public Grupo saveGrupo(@RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Grupo grupo = mapper.convertValue(body, Grupo.class);
        return grupoRepository.save(grupo);
    }

    @PutMapping("/{id}")
    public Grupo updateGrupo(@PathVariable String id, @RequestBody Map<String, Object> body) {
        ObjectMapper mapper = new ObjectMapper();
        Grupo grupo = mapper.convertValue(body, Grupo.class);
        grupo.setId(id);
        return grupoRepository.save(grupo);
    }

    @DeleteMapping("/{id}")
    public Grupo deleteGrupo(@PathVariable String id) {
        Grupo grupo = grupoRepository.findById(id).orElseThrow(() -> new NotFoundException("Grupo no encontrado"));
        grupoRepository.deleteById(id);
        return grupo;
    }
}
