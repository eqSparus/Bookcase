package ru.bookcase.configurations;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import ru.bookcase.configurations.security.config.SecurityConfig;

import javax.servlet.Filter;
import java.nio.charset.StandardCharsets;

public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SecurityConfig.class, DatabaseConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{ControllerConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/bookcase/*"};
    }

    @Override
    protected Filter[] getServletFilters() {

        var encodingFilter = new CharacterEncodingFilter(StandardCharsets.UTF_8.toString());
        encodingFilter.setForceEncoding(true);

        return new Filter[]{
                encodingFilter
        };
    }



}
