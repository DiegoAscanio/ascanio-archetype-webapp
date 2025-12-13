package models;

import org.junit.Test;
import static org.junit.Assert.*;

public class BasicModelTest {

    @Test
    public void constructorAndGettersWork() {
        BasicModel model = new BasicModel("host123", "alice");
        assertEquals("host123", model.getHostname());
        assertEquals("alice", model.getUsername());
    }

    @Test
    public void fromSystemProvidesNonEmptyValues() {
        BasicModel model = BasicModel.fromSystem();
        assertNotNull(model.getHostname());
        assertFalse(model.getHostname().isEmpty());
        assertNotNull(model.getUsername());
        assertFalse(model.getUsername().isEmpty());
    }
}
