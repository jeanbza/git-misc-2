package hello;

import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.stereotype.Component;

@Component
public class FooFilter extends FilterSecurityInterceptor {
}
