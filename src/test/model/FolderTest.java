package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class FolderTest {
    private Folder testFolder;

    @BeforeEach
    void runBefore() {testFolder = new Folder();}

   @Test
   void testConstructor() {
        assertTrue(testFolder.isSelected());
        assertEquals("", testFolder.getFolderTitle());
   }

    @Test
    void testRename() {
        testFolder.rename("new title");
        assertEquals("new title", testFolder.getFolderTitle());
    }

    @Test
    void testViewNotes() {
        Note note1 = new Note();
        note1.renameTitle("one");
        note1.addToFolder(testFolder);
        Note note2 = new Note();
        note2.renameTitle("two");
        note2.addToFolder(testFolder);
        ArrayList<String> actualNotes = testFolder.viewNotes();

        ArrayList<String> expectedNotes = new ArrayList<>();
        expectedNotes.add("one");
        expectedNotes.add("two");

        assertEquals(expectedNotes, actualNotes);
    }
}
