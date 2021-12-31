package controllers;

import model.location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import services.coronavirusdataservice;

import java.util.List;

@Controller
public class homecontrol {

    @Autowired
    coronavirusdataservice cv;

    @GetMapping("/")
    public ModelAndView homepage() {
        ModelAndView mv = new ModelAndView();
        List<location> mystats = cv.getAllstats();
        int totalcases = mystats.stream().mapToInt(stat -> stat.getLatestconfirmedcases()).sum();
        int totalnewcases = mystats.stream().mapToInt(stat -> stat.getDiffdaycases()).sum();
        mv.addObject("stats", mystats);
        mv.addObject("totalstats", totalcases);
        mv.addObject("totalnewcases", totalnewcases);
        mv.setViewName("home");
        return mv;
    }
}
