package demo;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.query.*;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

@Repository
public class FooRegionRepository {
    @Resource(name = "FooRegion") Region<String, Long> fooRegion;

    public List<Long> getAll() {
        try {
            SelectResults<String> valuesQuery = fooRegion.query("select * from /FooRegion");

            List<Long> values = valuesQuery.asList().stream()
                .map(Long::valueOf)
                .collect(toList());

            return values;
        } catch (FunctionDomainException | TypeMismatchException | NameResolutionException | QueryInvocationTargetException e) {
            return emptyList();
        }
    }
}
