package demo;

import com.gemstone.gemfire.cache.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import static demo.GemfireConnectionUtil.getRegionConnection;

@Configuration
public class GemfireConfiguration {
    @Value("${gemfire.locator.host}") private String gemfireLocatorHost;
    @Value("${gemfire.locator.port}") private String gemfireLocatorPort;

    @Bean(name = "FooRegion") Region<String, Long> fooRegion() {
        return getRegionConnection("FooRegion", gemfireLocatorHost, Integer.valueOf(gemfireLocatorPort));
    }
}