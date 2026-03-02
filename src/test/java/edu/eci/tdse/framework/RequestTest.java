package edu.eci.tdse.framework;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RequestTest {

    @Test
    public void shouldExtractQueryParameters() {
        Request req = new Request("/hello", "name=Pedro");

        assertEquals("Pedro", req.getValues("name"));
    }

    @Test
    public void shouldReturnNullForMissingParam() {
        Request req = new Request("/hello", "name=Pedro");

        assertNull(req.getValues("age"));
    }
}
