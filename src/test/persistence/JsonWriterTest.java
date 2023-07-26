package persistence;

import model.Folder;
import model.Note;
import model.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            User user = new User("tester");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyUser() {
        try {
            User user = new User("Test User");
            JsonWriter writer = new JsonWriter("./data/testEmptyUser.json");
            writer.open();
            writer.write(user);
            writer.close();
        } catch (IOException e) {
            fail("shouldn't be thrown");
        }
    }

    @Test
    void testWriterAvgUser() {
        try {
            User user = new User("test user");
            Note note1 = new Note();
            note1.write("hello");
            note1.renameTitle("note1");
            Folder folder1 = new Folder("folder1");
            folder1.addNote(note1);
            Note note2 = new Note();
            note2.write("bruh");
            note2.renameTitle("im sleepy");
            Folder folder2 = new Folder("folder2");
            folder2.addNote(note2);
            user.addFolder(folder1);
            user.addFolder(folder2);
            JsonWriter writer = new JsonWriter("./data/testAvgUser.json");
            writer.open();
            writer.write(user);
            writer.close();
        } catch (IOException e) {
            fail("shouldn't be thrown");
        }
    }
}
