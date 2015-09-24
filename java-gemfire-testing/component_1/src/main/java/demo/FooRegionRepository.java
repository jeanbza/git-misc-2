package demo;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.query.*;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

import static java.util.Collections.emptyList;

@Repository
public class FooRegionRepository {
    private Region<String, Long> fooRegion;

    @Resource(name = "FooRegion")
    public void setFooRegion(Region<String, Long> fooRegion) {
        this.fooRegion = fooRegion;
    }

    public List<Long> getAll() {
        try {
            SelectResults<Long> valuesQuery = fooRegion.query("select * from /FooRegion");
            return valuesQuery.asList();
        } catch (FunctionDomainException | TypeMismatchException | NameResolutionException | QueryInvocationTargetException e) {
            return emptyList();
        }
    }
}
