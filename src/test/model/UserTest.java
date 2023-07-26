package model;

import model.Folder;
import model.Note;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private User testUser;
    private Folder testFolder1;
    private Folder testFolder2;
    private Note testNote1;
    private Note testNote2;
    private Note testNote3;

    @BeforeEach
    void runBefore() {
        testUser = new User("tester");
        testFolder1 = new Folder("one");
        testFolder2 = new Folder("two");
        testNote1 = new Note();
        testNote2 = new Note();
        testNote3 = new Note();
    }

    @Test
    void testConstructor() {
        assertEquals("tester", testUser.getName());
        assertEquals(0, testUser.getNumFolders());
    }

    @Test
    void testAddFolders() { // tested similar to how a user would use the app
        List<Folder> myFolders = new ArrayList<>();
        myFolders.add(testFolder1);
        testFolder1.addNote(testNote1);
        myFolders.add(testFolder2);
        testFolder2.addNote(testNote2);
        testFolder2.addNote(testNote3);

        testUser.addFolders(myFolders); // saving folders to file

        assertEquals(2, testUser.getNumFolders());

        List<Folder> expectedFolders = new ArrayList<>();
        expectedFolders.add(testFolder1);
        expectedFolders.add(testFolder2);

        assertEquals(expectedFolders, testUser.getFolders());

        myFolders.add(testFolder1); // folder is retrieved and updated
        assertEquals(3, myFolders.size());

        testUser.addFolders(myFolders);
        assertEquals(2, testUser.getNumFolders());
        assertEquals(expectedFolders, testUser.getFolders());
    }

    @Test
    void testFolderNames() {
        testUser.addFolder(testFolder1);
        testUser.addFolder(testFolder2);
        assertEquals(2, testUser.getNumFolders());

        Folder testFolder3 = new Folder("three");
        testUser.addFolder(testFolder3);

        List<String> actual = testUser.folderNames();
        List<String> expected = new ArrayList<>();
        expected.add("one");
        expected.add("two");
        expected.add("three");

        assertEquals(expected, actual);
    }

    @Test
    void testAddNote() {
        testUser.addFolder(testFolder1);
        testUser.addNote(testNote1, "one");
        List<Note> actual = testFolder1.viewNotes();
        List<Note> expected = new ArrayList<>();
        expected.add(testNote1);
        assertEquals(expected, actual);
    }

}
