package demo;

import com.gemstone.gemfire.cache.Region;
import org.springframework.context.annotation.*;

import static demo.GemfireConnectionUtil.getRegionConnection;

@Configuration
public class GemfireConfiguration {
    @Bean(name = "FooRegion") Region<String, Long> fooRegion() {
        return getRegionConnection("FooRegion");
    }
}