package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.DoctorModel.PREDICATE_SHOW_ALL_DOCTORS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDoctor.ALICE;
import static seedu.address.testutil.TypicalDoctor.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.NameContainsKeywordsDoctorPredicate;
import seedu.address.testutil.DoctorAddressBookBuilder;

public class DoctorModelManagerTest {

    private DoctorModelManager doctorModelManager = new DoctorModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), doctorModelManager.getUserPrefs());
        assertEquals(new GuiSettings(), doctorModelManager.getGuiSettings());
        assertEquals(new DoctorAddressBook(), new DoctorAddressBook(doctorModelManager.getDoctorAddressBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> doctorModelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        doctorModelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, doctorModelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, doctorModelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> doctorModelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        doctorModelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, doctorModelManager.getGuiSettings());
    }

    @Test
    public void setDoctorAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> doctorModelManager.setDoctorAddressBookFilePath(null));
    }

    @Test
    public void setDoctorAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        doctorModelManager.setDoctorAddressBookFilePath(path);
        assertEquals(path, doctorModelManager.getDoctorAddressBookFilePath());
    }

    @Test
    public void hasDoctor_nullDoctor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> doctorModelManager.hasDoctor(null));
    }

    @Test
    public void hasDoctor_doctorNotInDoctorAddressBook_returnsFalse() {
        assertFalse(doctorModelManager.hasDoctor(ALICE));
    }

    @Test
    public void hasDoctor_doctorInDoctorAddressBook_returnsTrue() {
        doctorModelManager.addDoctor(ALICE);
        assertTrue(doctorModelManager.hasDoctor(ALICE));
    }

    @Test
    public void getFilteredDoctorList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> doctorModelManager.getFilteredDoctorList().remove(0));
    }

    @Test
    public void equals() {
        DoctorAddressBook doctorAddressBook =
                new DoctorAddressBookBuilder().withDoctor(ALICE).withDoctor(BENSON).build();
        DoctorAddressBook differentDoctorAddressBook = new DoctorAddressBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        doctorModelManager = new DoctorModelManager(doctorAddressBook, userPrefs);
        DoctorModelManager doctorModelManagerCopy = new DoctorModelManager(doctorAddressBook, userPrefs);
        assertTrue(doctorModelManager.equals(doctorModelManagerCopy));

        // same object -> returns true
        assertTrue(doctorModelManager.equals(doctorModelManager));

        // null -> returns false
        assertFalse(doctorModelManager.equals(null));

        // different types -> returns false
        assertFalse(doctorModelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(doctorModelManager.equals(new DoctorModelManager(differentDoctorAddressBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        doctorModelManager.updateFilteredDoctorList(new NameContainsKeywordsDoctorPredicate(Arrays.asList(keywords)));
        assertFalse(doctorModelManager.equals(new DoctorModelManager(doctorAddressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        doctorModelManagerCopy.updateFilteredDoctorList(PREDICATE_SHOW_ALL_DOCTORS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(doctorModelManager.equals(new DoctorModelManager(doctorAddressBook, differentUserPrefs)));
    }
}
