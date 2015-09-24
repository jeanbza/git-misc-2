//package demo;
//
//import com.gemstone.gemfire.cache.*;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
//import org.springframework.cache.CacheManager;
//import org.springframework.context.annotation.*;
//import org.springframework.data.gemfire.RegionAttributesFactoryBean;
//import org.springframework.data.gemfire.client.*;
//import org.springframework.data.gemfire.config.GemfireConstants;
//import org.springframework.data.gemfire.support.GemfireCacheManager;
//import org.springframework.util.ObjectUtils;
//
//import java.net.InetSocketAddress;
//import java.util.*;
//import java.util.concurrent.TimeUnit;
//
//@Configuration
//public class GemfireConfiguration {
//    private String gemfireHostname = "localhost";
//    private String gemfirePort = "10334";
//
//    @Bean
//    public CacheManager cacheManager(GemFireCache gemfireCache) {
//        GemfireCacheManager cacheManager = new GemfireCacheManager();
//        cacheManager.setCache((Cache) gemfireCache);
//
//        return cacheManager;
//    }
//
//    @Bean
//    public Properties gemfireSettings() {
//        Properties gemfireSettings = new Properties();
//
//        gemfireSettings.setProperty("app.gemfire.default.log-level", "config");
//        gemfireSettings.setProperty("app.gemfire.default.pool.free-connection-timeout", String.valueOf(TimeUnit.SECONDS.toMillis(30)));
//        gemfireSettings.setProperty("app.gemfire.default.pool.hostname", gemfireHostname);
//        gemfireSettings.setProperty("app.gemfire.default.pool.idle-timeout", String.valueOf(TimeUnit.MINUTES.toMillis(2)));
//        gemfireSettings.setProperty("app.gemfire.default.pool.min-connections", "1");
//        gemfireSettings.setProperty("app.gemfire.default.pool.port", gemfirePort);
//        gemfireSettings.setProperty("app.gemfire.default.region.initial-capacity", "101");
//        gemfireSettings.setProperty("app.gemfire.default.region.load-factor", "0.75");
//
//        return gemfireSettings;
//    }
//
//    @Bean
//    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer(@Qualifier("gemfireSettings") Properties placeholders) {
//        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
//        propertyPlaceholderConfigurer.setProperties(placeholders);
//
//        return propertyPlaceholderConfigurer;
//    }
//
//    @Bean
//    public Properties gemfireProperties() {
//        Properties gemfireProperties = new Properties();
//        gemfireProperties.setProperty("name", "SpringGemFireCacheClient");
//
//        return gemfireProperties;
//    }
//
//    @Bean(name = GemfireConstants.DEFAULT_GEMFIRE_POOL_NAME)
//    public PoolFactoryBean gemfirePool() {
//        PoolFactoryBean poolFactoryBean = new PoolFactoryBean();
//        poolFactoryBean.setLocators(asCollection(new InetSocketAddress(gemfireHostname, Integer.valueOf(gemfirePort))));
//
//        return poolFactoryBean;
//    }
//
//    @Bean
//    public ClientCacheFactoryBean gemfireCache(@Qualifier("gemfireProperties") Properties gemfireProperties) {
//        ClientCacheFactoryBean clientCacheFactoryBean = new ClientCacheFactoryBean();
//        clientCacheFactoryBean.setProperties(gemfireProperties);
//
//        return clientCacheFactoryBean;
//    }
//
//    @Bean
//    @SuppressWarnings("unchecked")
//    public RegionAttributesFactoryBean exampleRegionAttributes() {
//        RegionAttributesFactoryBean regionAttributesFactoryBean = new RegionAttributesFactoryBean();
//        regionAttributesFactoryBean.setKeyConstraint(String.class);
//        regionAttributesFactoryBean.setValueConstraint(Long.class);
//
//        return regionAttributesFactoryBean;
//    }
//
//    protected Collection<InetSocketAddress> asCollection(InetSocketAddress... socketAddresses) {
//        return (!ObjectUtils.isEmpty(socketAddresses) ? Arrays.asList(socketAddresses) : Collections.<InetSocketAddress>emptyList());
//    }
//}