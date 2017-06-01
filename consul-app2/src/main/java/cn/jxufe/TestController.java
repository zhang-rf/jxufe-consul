package cn.jxufe;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class TestController {

    private final Demo demo;

    public TestController(Demo demo) {
        this.demo = demo;
    }

    @RequestMapping("test")
    public String test() throws IOException {
        return demo.demo(123).execute().body();
    }
}
