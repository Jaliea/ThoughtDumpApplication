package persistence;

import model.Folder;
import model.Note;
import model.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            User user = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyUser() {
        try {
        JsonReader reader = new JsonReader("./data/testEmptyUser.json");
        User user = reader.read();
        assertEquals("Test User", user.getName());
        assertEquals(0, user.getNumFolders());
        } catch (IOException e) {
            fail("shouldn't be thrown");
        }
    }

    @Test
    void testReaderAvgUser() {
        try {
            JsonReader reader = new JsonReader("./data/testAvgUser.json");
            User user = reader.read();
            assertEquals("test user", user.getName());
            assertEquals(2, user.getNumFolders());
            Folder folder2 = user.findFolder("folder2");
            assertEquals(1, folder2.getNumNotes());
            Note note2 = folder2.findNote("im sleepy");
            assertEquals("bruh", note2.getMessage());
        } catch (IOException e) {
            fail("shouldn't be thrown");
        }
    }
}
