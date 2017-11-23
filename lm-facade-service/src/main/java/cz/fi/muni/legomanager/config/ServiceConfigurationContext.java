package cz.fi.muni.legomanager.config;

import cz.fi.muni.legomanager.PersistenceSampleApplicationContext;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PersistenceSampleApplicationContext.class)
@ComponentScan
public class ServiceConfigurationContext {
    @Bean
    public Mapper dozer() {
        return new DozerBeanMapper();
    }
}
