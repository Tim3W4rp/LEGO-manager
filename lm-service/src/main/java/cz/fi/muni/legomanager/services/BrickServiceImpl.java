package cz.fi.muni.legomanager.services;

import cz.fi.muni.legomanager.dao.BrickDao;
import cz.fi.muni.legomanager.entity.Brick;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of the {@link BrickService}.
 *
 * @author Lukáš Dvořák
 */
@Service
public class BrickServiceImpl implements BrickService {

    @Inject
    private BrickDao brickDao;

    @Override
    public void create(Brick brick) {
        brickDao.create(brick);
    }

    @Override
    public void update(Brick brick) {
        brickDao.update(brick);
    }

    @Override
    public void delete(Brick brick) {
        brickDao.delete(brick);
    }

    @Override
    public Brick findById(Long id) {
        return brickDao.findById(id);
    }

    @Override
    public List<Brick> findAll() {
        return brickDao.findAll();
    }
}
