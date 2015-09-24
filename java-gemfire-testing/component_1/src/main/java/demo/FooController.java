package demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.query.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

import static java.util.Arrays.asList;

@Controller
public class FooController {
    @Resource(name = "SomeRegion") Region<String, Long> someRegion;

    private final ObjectMapper jsonMapper = new ObjectMapper();

    @RequestMapping(value = "/foo", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getUsers() throws JsonProcessingException, NameResolutionException, TypeMismatchException, QueryInvocationTargetException, FunctionDomainException {
        SelectResults<Object> query1 = someRegion.query("select * from /SomeRegion");
        System.out.println(query1.size());
        System.out.println(query1.asList());

        Set<String> strings = someRegion.keySetOnServer();
        System.out.println(strings);

        List<String> users = asList("foo1", "foo2");

        return jsonMapper.writeValueAsString(users);
    }
}
