package net.futureorigin.architecture.sample.cola.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * BlankController
 * <p></p>
 *
 * @author Leander Lee on 2021/8/22.
 */
@Controller
@ApiIgnore
public class BlankController {

    @RequestMapping(path = "/")
    public String blank() {
        return "redirect:index.html";
    }
}
