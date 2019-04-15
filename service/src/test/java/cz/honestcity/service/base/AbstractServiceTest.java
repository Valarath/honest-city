package cz.honestcity.service.base;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

public abstract class AbstractServiceTest {

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }
}
