package mundial.app.controller;

import mundial.app.Entity.Equipo;
import mundial.app.exception.NotFoundException;
import mundial.app.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/equipos")
public class EquiposTemplatesController {
    @Autowired
    private EquipoRepository equipoRepository;

    @GetMapping("/")
    public String equiposListTemplate(Model model) {
        model.addAttribute("equipos", equipoRepository.findAll());
        return "equipos-list";
    }

    @GetMapping("/new")
    public String equiposNewTemplate(Model model) {
        model.addAttribute("equipo", new Equipo());
        return "equipos-form";
    }

    @GetMapping("/edit/{id}")
    public String equipoEditTemplate(@PathVariable("id") String id, Model model) {
        model.addAttribute("equipo", equipoRepository.findById(id).orElseThrow(() -> new NotFoundException("Equipo no encontrado")));
        return "equipos-form";
    }

    @PostMapping("/save")
    public String equiposSaveProcess(@ModelAttribute("equipo") Equipo equipo) {
        if (equipo.getId().isEmpty()) {
            equipo.setId(null);
        }
        equipoRepository.save(equipo);
        return "redirect:/equipos/";
    }

    @GetMapping("/delete/{id}")
    public String equipoDeleteProcess(@PathVariable("id") String id) {
        equipoRepository.deleteById(id);
        return "redirect:/equipos/";
    }
}
