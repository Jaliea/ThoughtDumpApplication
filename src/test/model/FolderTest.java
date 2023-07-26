package model;

import model.Folder;
import model.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FolderTest {
    private Folder testFolder;

    @BeforeEach
    void runBefore() {testFolder = new Folder("test folder");}

   @Test
   void testConstructor() {
        assertEquals("test folder", testFolder.getFolderTitle());
        ArrayList<Note> actualNotes = testFolder.viewNotes();
        assertEquals(0, actualNotes.size());
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

        ArrayList<Note> actualNotes = testFolder.viewNotes();

        ArrayList<Note> expectedNotes = new ArrayList<>();
        expectedNotes.add(note1);
        expectedNotes.add(note2);

        assertEquals(expectedNotes, actualNotes);
    }

    @Test
    void testAddUnselectedNote() {
        Note note1 = new Note();
        note1.unselect();
        testFolder.addNote(note1);

        ArrayList<Note> actualNotes = testFolder.viewNotes();
        ArrayList<Note> expectedNotes = new ArrayList<>();
        assertEquals(expectedNotes, actualNotes);
        assertEquals(0, actualNotes.size());
    }
}
