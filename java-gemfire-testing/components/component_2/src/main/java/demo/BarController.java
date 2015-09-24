package demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Arrays.asList;

@Controller
public class BarController {
    private final ObjectMapper jsonMapper = new ObjectMapper();

    @RequestMapping(value = "/bar", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getUsers() throws JsonProcessingException {
        List<String> users = asList("bar1", "bar2");

        return jsonMapper.writeValueAsString(users);
    }
}
