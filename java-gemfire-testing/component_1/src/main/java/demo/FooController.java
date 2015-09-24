package demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Arrays.asList;

@Controller
public class FooController {
    private final ObjectMapper jsonMapper = new ObjectMapper();

    @RequestMapping(value = "/foo", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getUsers() throws JsonProcessingException {
        List<String> users = asList("foo1", "foo2");

        return jsonMapper.writeValueAsString(users);
    }
}
