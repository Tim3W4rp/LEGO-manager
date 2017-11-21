package cz.fi.muni.legomanager.services;

import org.dozer.Mapper;

import java.util.Collection;
import java.util.List;

public interface DozerService {
    public  <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    public  <T> T mapTo(Object u, Class<T> mapToClass);
    public Mapper getMapper();

}
