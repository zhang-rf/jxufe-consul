package cn.jxufe;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @RequestMapping("/test/{v}")
    public String test(@PathVariable Integer v) {
        return "Got a " + v;
    }
}
