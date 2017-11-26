package cz.fi.muni.legomanager.services;

import cz.fi.muni.legomanager.config.ServiceConfigurationContext;
import cz.fi.muni.legomanager.dao.SetOfKitsDao;
import cz.fi.muni.legomanager.entity.SetOfKits;
import cz.fi.muni.legomanager.entity.SetOfKits;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeMethod;

@ContextConfiguration(classes = ServiceConfigurationContext.class)
public class SetOfKitsServiceTest {
    @Mock
    private SetOfKitsDao setDao;

    @Autowired
    @InjectMocks
    private SetOfKitsService setService;

    private SetOfKits newSet;

    @BeforeMethod
    public void beforeTest() {

    }




}
