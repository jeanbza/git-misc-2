package demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SomeController {
    @Value("${some.yaml.prop}") private String someYamlProp;

    @RequestMapping(value = "/foo")
    @ResponseBody
    public String foo() {
        return "@Value is giving us: " + someYamlProp;
    }
}
