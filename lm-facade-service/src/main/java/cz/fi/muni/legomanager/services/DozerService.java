package cz.fi.muni.legomanager.services;

import org.dozer.Mapper;

import java.util.Collection;
import java.util.List;

public interface DozerService {
    <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    <T> T mapTo(Object u, Class<T> mapToClass);

    Mapper getMapper();

}
