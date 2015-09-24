package demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gemstone.gemfire.cache.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static java.util.Arrays.asList;

@Controller
public class FooController {
    @Autowired Region<String, Long> someregion;

    private final ObjectMapper jsonMapper = new ObjectMapper();

    @RequestMapping(value = "/foo", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getUsers() throws JsonProcessingException {
        Set<String> keySet = someregion.keySetOnServer();
        System.out.println(keySet);

        List<String> users = asList("foo1", "foo2");

        return jsonMapper.writeValueAsString(users);
    }
}
