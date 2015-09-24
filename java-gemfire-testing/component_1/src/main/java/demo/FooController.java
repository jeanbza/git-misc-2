package demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FooController {
    private final ObjectMapper jsonMapper = new ObjectMapper();

    private FooRegionRepository fooRegionRepository;

    @Autowired
    public FooController(FooRegionRepository fooRegionRepository) {
        this.fooRegionRepository = fooRegionRepository;
    }

    @RequestMapping(value = "/foo", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getUsers() throws JsonProcessingException {
        List<Long> allFooRegionValues = fooRegionRepository.getAll();

        return jsonMapper.writeValueAsString(allFooRegionValues);
    }
}
