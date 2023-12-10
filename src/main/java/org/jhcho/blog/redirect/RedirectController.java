package org.jhcho.blog.redirect;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectController {

    @RequestMapping("/docs")
    public String Redirect() {
        return "redirect:/swagger-ui.html";
    }
}
