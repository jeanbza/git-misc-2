package demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static java.util.Arrays.asList;

@Controller
public class FooController {
    private final ObjectMapper jsonMapper = new ObjectMapper();

    @Autowired private FooRegionRepository fooRegionRepository;

    @RequestMapping(value = "/foo", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getUsers() throws JsonProcessingException {
//        List<Long> allFooRegionValues = fooRegionRepository.getAllValues();

        return jsonMapper.writeValueAsString(asList(1, 2, 3));
    }
}
