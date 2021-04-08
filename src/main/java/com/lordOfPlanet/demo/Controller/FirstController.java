package com.lordOfPlanet.demo.Controller;

import com.lordOfPlanet.demo.DAO.LordDao;
import com.lordOfPlanet.demo.DAO.PlanetDAO;
import com.lordOfPlanet.demo.Model.Lord;
import com.lordOfPlanet.demo.Model.Planet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/hello")
public class FirstController {


    private final LordDao lordDao;

    private final PlanetDAO planetDAO;

    @Autowired
    public FirstController(PlanetDAO planetDAO, LordDao lordDao){
        this.lordDao=lordDao;
        this.planetDAO=planetDAO;
    }

    @GetMapping("/add-lord")
    public String addLord(Model model){
        model.addAttribute("lord",new Lord());
        return "add-lord";
    }

    @PostMapping("/add-lord")
    public String addLord(@ModelAttribute("lord") @Valid Lord lord,
                          BindingResult bindingResult){
        if(bindingResult.hasErrors()||lordDao.getLord(lord)!=null)
            return "add-lord";
        lordDao.setLord(lord);
        return "redirect:add-lord";
    }

    @GetMapping("/add-planet")
    public String addPlanet(Model model) {
        model.addAttribute("planet",new Planet());
        return "add-planet";
    }

    @PostMapping("/add-planet")
    public String addPlanet(@ModelAttribute("planet") @Valid Planet planet,
                            BindingResult bindingResult){
        if(bindingResult.hasErrors()||planetDAO.getPlanet(planet.getName())!=null)
            return "add-planet";

        planetDAO.setNamePlanet(planet.getName());
        return "redirect:add-planet";
    }

    @GetMapping("/destroy")
    public String destroy(Model model){
        model.addAttribute("planet",new Planet());
        return "destroy-planet";
    }

    @PostMapping("/destroy")
    public String destroy( @ModelAttribute("planet") @Valid Planet planet,
                           BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "destroy-planet";
        planet=planetDAO.getPlanet(planet.getName());
        if (planet == null)
            return "destroy-planet";

        lordDao.deleteKeyFromLord(planet.getUuid());
        planetDAO.destroyPlanet(planet.getName());
        return "redirect:destroy";


    }

    @GetMapping("/set-lord")
    public String setLord(Model model){

        List<Lord> list=lordDao.getAllLord();
        model.addAttribute("listLords",list);
        List<Planet> stringList=planetDAO.getPlanets();
        model.addAttribute("listPlanets",stringList);
        model.addAttribute("lord",new Lord());
        model.addAttribute("planet",new Planet());
        return "set-lord-on-planet";
    }

    @PostMapping("/set-lord")
    public String setLord(@ModelAttribute("lord" ) @Valid Lord lord,
                          BindingResult bindingResult,
                          @RequestParam ("planet")  String planet
    ) {
        if(bindingResult.hasErrors())
            return "set-lord-on-planet";
        if(planetDAO.getPlanet(planet)!=null&&lordDao.getLord(lord)!=null)
            lordDao.setKey(lord,planetDAO.getPlanet(planet).getUuid());
        return "redirect:set-lord";
    }

    @GetMapping("/get-youngest")
    public String getYounger(Model model){

        List<Lord> list=lordDao.getYoungestLords();
        model.addAttribute("list",list);
        return "get-youngest";
    }




}
