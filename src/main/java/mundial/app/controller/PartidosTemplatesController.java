package mundial.app.controller;

import mundial.app.Entity.Partido;
import mundial.app.exception.NotFoundException;
import mundial.app.repository.EquipoRepository;
import mundial.app.repository.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/partidos")
public class PartidosTemplatesController {
    @Autowired
    private PartidoRepository partidoRepository;
    @Autowired
    private EquipoRepository equipoRepository;

    @GetMapping("/")
    public String partidosListTemplate(Model model) {
        model.addAttribute("partidos", partidoRepository.findAll());
        return "partidos-list";
    }
    @GetMapping("/new")
    public String partidosNewTemplate(Model model) {
        model.addAttribute("partido", new Partido());
        model.addAttribute("equipos", equipoRepository.findAll());
        return "partidos-form";
    }
    @GetMapping("/edit/{id}")
    public String partidosEditTemplate(Model model, @PathVariable("id") String id) {
        Partido partido = partidoRepository.findById(id).orElseThrow(() -> new NotFoundException("Partido no encontrado"));
        model.addAttribute("partido", partido);
        model.addAttribute("equipos", equipoRepository.findAll());
        return "partidos-form";
    }
    @PostMapping("/save")
    public String partidosSaveProcess(@ModelAttribute("partido") Partido partido) {
        if (partido.getId().isEmpty()) {
            partido.setId(null);
        }
        partidoRepository.save(partido);
        return "redirect:/partidos/";
    }
    @GetMapping("/delete/{id}")
    public String partidosDeleteProcess(@PathVariable("id") String id) {
        partidoRepository.deleteById(id);
        return "redirect:/partidos/";
    }
}
