package demo;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.query.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Repository;

import java.util.List;

import static java.util.Collections.emptyList;

@Repository
public class FooRegionRepository {
    private Region<String, Long> fooRegion;

    // @Value because @Autowired sucks at maps, and Region extends Map. We could also use @Resource but then
    // we would have to use a setter, and setters suck
    @Autowired
    public FooRegionRepository(@Value("#{FooRegion}") Region<String, Long> fooRegion) {
        this.fooRegion = fooRegion;
    }

    public List<Long> getAllValues() {
        try {
            SelectResults<Long> valuesQuery = fooRegion.query("select * from /FooRegion");
            return valuesQuery.asList();
        } catch (FunctionDomainException | TypeMismatchException | NameResolutionException | QueryInvocationTargetException e) {
            return emptyList();
        }
    }
}
