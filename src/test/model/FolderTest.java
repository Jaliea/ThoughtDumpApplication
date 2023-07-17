package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        testFolder.name("new title");
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

        ArrayList<Note> actualNotes = testFolder.viewNotes();

        ArrayList<Note> expectedNotes = new ArrayList<>();
        expectedNotes.add(note1);
        expectedNotes.add(note2);

        assertEquals(expectedNotes, actualNotes);
    }
}
