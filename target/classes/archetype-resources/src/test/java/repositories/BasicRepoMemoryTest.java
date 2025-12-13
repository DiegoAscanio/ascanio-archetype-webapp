package repositories;

import models.BasicModel;
import org.junit.Test;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.*;

public class BasicRepoMemoryTest {

    @Test
    public void createAndFindByHostname() {
        BasicRepo repo = new BasicRepoMemory();
        BasicModel m = new BasicModel("hostA", "alice");
        repo.create(m);
        Optional<BasicModel> found = repo.findByHostname("hostA");
        assertTrue(found.isPresent());
        assertEquals("alice", found.get().getUsername());
    }

    @Test
    public void createReplacesExistingByHostname() {
        BasicRepo repo = new BasicRepoMemory();
        repo.create(new BasicModel("hostA", "alice"));
        repo.create(new BasicModel("hostA", "bob"));
        BasicModel result = repo.findByHostname("hostA").orElseThrow();
        assertEquals("bob", result.getUsername());
    }

    @Test
    public void findAllReturnsCopy() {
        BasicRepoMemory repo = new BasicRepoMemory();
        repo.create(new BasicModel("h1", "u1"));
        repo.create(new BasicModel("h2", "u2"));
        List<BasicModel> list = repo.findAll();
        assertEquals(2, list.size());
        list.clear();
        assertEquals("Original store unaffected", 2, repo.findAll().size());
    }

    @Test
    public void updateExistingModel() {
        BasicRepo repo = new BasicRepoMemory();
        repo.create(new BasicModel("hostA", "alice"));
        BasicModel updated = repo.update(new BasicModel("hostA", "carol"));
        assertEquals("carol", updated.getUsername());
        assertEquals("carol", repo.findByHostname("hostA").orElseThrow().getUsername());
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateNonExistingThrows() {
        BasicRepo repo = new BasicRepoMemory();
        repo.update(new BasicModel("missing", "x"));
    }

    @Test
    public void deleteByHostnameRemovesItem() {
        BasicRepo repo = new BasicRepoMemory();
        repo.create(new BasicModel("hostA", "alice"));
        assertTrue(repo.deleteByHostname("hostA"));
        assertFalse(repo.findByHostname("hostA").isPresent());
    }

    @Test
    public void deleteByHostnameReturnsFalseWhenMissing() {
        BasicRepo repo = new BasicRepoMemory();
        assertFalse(repo.deleteByHostname("missing"));
    }
}
