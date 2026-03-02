package edu.eci.tdse.framework;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WebFrameworkTest {

    @Test
    public void shouldRegisterRoute() {
        WebFramework.get("/test", (req, res) -> "ok");

        Route route = WebFramework.getRoute("/test");

        assertNotNull(route);
    }

    @Test
    public void shouldSetStaticFolder() {
        WebFramework.staticfiles("/public");

        assertEquals("/public", WebFramework.getStaticFolder());
    }
}
