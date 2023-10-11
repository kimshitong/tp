package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.doctor.Doctor;

/**
 * The API of the Model component.
 */
public interface DoctorModel {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Doctor> PREDICATE_SHOW_ALL_DOCTORS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' doctor address book file path.
     */
    Path getDoctorAddressBookFilePath();

    /**
     * Sets the user prefs' doctor address book file path.
     */
    void setDoctorAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces doctor address book data with the data in {@code addressBook}.
     */
    void setDoctorAddressBook(ReadOnlyDoctorAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyDoctorAddressBook getDoctorAddressBook();

    /**
     * Returns true if a doctor with the same identity as {@code doctor} exists in the doctor address book.
     */
    boolean hasDoctor(Doctor doctor);

    /**
     * Deletes the given doctor.
     * The doctor must exist in the doctor address book.
     */
    void deleteDoctor(Doctor target);

    /**
     * Adds the given doctor.
     * {@code doctor} must not already exist in the doctor address book.
     */
    void addDoctor(Doctor doctor);

    /**
     * Replaces the given doctor {@code target} with {@code editedDoctor}.
     * {@code target} must exist in the doctor address book.
     * The doctor identity of {@code editedDoctor} must not be the same
     * as another existing doctor in the doctor address book.
     */
    void setDoctor(Doctor target, Doctor editedDoctor);

    /** Returns an unmodifiable view of the filtered doctor list */
    ObservableList<Doctor> getFilteredDoctorList();

    /**
     * Updates the filter of the filtered doctor list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredDoctorList(Predicate<Doctor> predicate);
}
