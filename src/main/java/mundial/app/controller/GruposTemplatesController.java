package mundial.app.controller;

import mundial.app.Entity.Grupo;
import mundial.app.exception.NotFoundException;
import mundial.app.repository.EquipoRepository;
import mundial.app.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/grupos")
public class GruposTemplatesController {
    @Autowired
    private GrupoRepository grupoRepository;
    @Autowired
    private EquipoRepository equipoRepository;

    @GetMapping("/")
    public String gruposListTemplate(Model model) {
        model.addAttribute("grupos", grupoRepository.findAll());
        model.addAttribute("equipos", equipoRepository.findAll());
        return "grupos-list";
    }
    @GetMapping("/new")
    public String gruposNewTemplate(Model model) {
        model.addAttribute("grupo", new Grupo());
        model.addAttribute("equipos", equipoRepository.findAll());
        return "grupos-form";
    }
    @GetMapping("/edit/{id}")
    public String gruposEditTemplate(@PathVariable String id, Model model) {
        Grupo grupo = grupoRepository.findById(id).orElseThrow(() -> new NotFoundException("Grupo no encontrado"));
        model.addAttribute("grupo", grupo);
        model.addAttribute("equipos", equipoRepository.findAll());
        return "grupos-form";
    }
    @PostMapping("/save")
    public String gruposSaveProcess(@ModelAttribute("grupo") Grupo grupo) {
        if (grupo.getId().isEmpty()) {
            grupo.setId(null);
        }
        grupoRepository.save(grupo);
        return "redirect:/grupos/";
    }
    @GetMapping("/delete/{id}")
    public String gruposDeleteProcess(@PathVariable String id) {
        grupoRepository.deleteById(id);
        return "redirect:/grupos/";
    }
}
