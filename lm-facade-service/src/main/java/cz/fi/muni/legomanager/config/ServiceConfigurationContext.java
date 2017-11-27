package cz.fi.muni.legomanager.config;

import cz.fi.muni.legomanager.PersistenceSampleApplicationContext;
import cz.fi.muni.legomanager.dto.BrickCreateDTO;
import cz.fi.muni.legomanager.dto.BrickDTO;
import cz.fi.muni.legomanager.dto.CategoryDTO;
import cz.fi.muni.legomanager.dto.KitBrickDTO;
import cz.fi.muni.legomanager.dto.KitCreateDTO;
import cz.fi.muni.legomanager.dto.KitDTO;
import cz.fi.muni.legomanager.dto.SetOfKitsCreateDTO;
import cz.fi.muni.legomanager.dto.SetOfKitsDTO;
import cz.fi.muni.legomanager.dto.ShapeCreateDTO;
import cz.fi.muni.legomanager.dto.ShapeDTO;
import cz.fi.muni.legomanager.entity.Brick;
import cz.fi.muni.legomanager.entity.Category;
import cz.fi.muni.legomanager.entity.Kit;
import cz.fi.muni.legomanager.entity.KitBrick;
import cz.fi.muni.legomanager.entity.SetOfKits;
import cz.fi.muni.legomanager.entity.Shape;
import cz.fi.muni.legomanager.facade.KitFacadeImpl;
import cz.fi.muni.legomanager.services.BrickServiceImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PersistenceSampleApplicationContext.class)
@ComponentScan(basePackageClasses = {BrickServiceImpl.class, KitFacadeImpl.class})
public class ServiceConfigurationContext {

    @Bean
    public Mapper dozer() {
        DozerBeanMapper dozer = new DozerBeanMapper();
        dozer.addMapping(new DozerCustomConfig());
        return dozer;
    }

    /**
     * Custom config for Dozer if needed
     *
     * @author Lukáš Dvořák
     */
    public class DozerCustomConfig extends BeanMappingBuilder {
        @Override
        protected void configure() {
            mapping(Brick.class, BrickDTO.class);
            mapping(Brick.class, BrickCreateDTO.class);
            mapping(Category.class, CategoryDTO.class);
            mapping(Kit.class, KitDTO.class);
            mapping(Kit.class, KitCreateDTO.class);
            mapping(KitBrick.class, KitBrickDTO.class);
            mapping(SetOfKits.class, SetOfKitsDTO.class);
            mapping(SetOfKits.class, SetOfKitsCreateDTO.class);
            mapping(Shape.class, ShapeDTO.class);
            mapping(Shape.class, ShapeCreateDTO.class);
        }
    }
}
