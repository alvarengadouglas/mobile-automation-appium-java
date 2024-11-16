package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import static utils.Utils.*;

public class Hooks {

    @Before
    public void before() throws  Exception {
        //acessarLocalApp();
    }

    @After
    public void after() throws Exception {
        driver.quit();
    }
}
