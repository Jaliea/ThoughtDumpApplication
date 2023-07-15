package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoteTest {
    private Note testNote;

    @BeforeEach
    void runBefore() {testNote = new Note();}

    @Test
    void testConstructor() {
        assertTrue(testNote.isSelected());
        assertEquals("", testNote.getMessage());
        assertNull(testNote.getFolder());
        assertEquals("", testNote.getNoteTitle());
    }

    @Test
    void testWrite() {
        testNote.write("hi");
        assertEquals("hi", testNote.getMessage());
    }

    @Test
    void testAddToFolder() {
        Folder testFolder = new Folder("Test");
        testNote.addToFolder(testFolder);
        assertEquals(testFolder, testNote.getFolder());
    }

    @Test
    void testTrash() {
       assertNull(testNote.trash());
    }

    @Test
    void testView() {
        testNote.write("hi");
        assertEquals("hi", testNote.view());
    }

    @Test
    void testEdit() {
        testNote.write("ily");
        testNote.edit("ily <3");
        assertEquals("ily <3",testNote.getMessage());
    }

    @Test
    void testRenameTitle() {
        testNote.renameTitle("heh");
        assertEquals("heh", testNote.getNoteTitle());
    }
}