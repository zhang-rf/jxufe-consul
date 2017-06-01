package cn.jxufe;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @RequestMapping("demo")
    public String demo(@RequestParam Integer n) {
        return "Got a " + n;
    }
}
