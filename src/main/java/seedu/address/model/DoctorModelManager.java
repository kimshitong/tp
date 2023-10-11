package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.doctor.Doctor;

/**
 * Represents the in-memory model of the doctor address book data.
 */
public class DoctorModelManager implements DoctorModel {
    private static final Logger logger = LogsCenter.getLogger(DoctorModelManager.class);

    private final DoctorAddressBook doctorAddressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Doctor> filteredDoctors;

    /**
     * Initializes a ModelManager with the given doctorAddressBook and userPrefs.
     */
    public DoctorModelManager(ReadOnlyDoctorAddressBook doctorAddressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(doctorAddressBook, userPrefs);

        logger.fine("Initializing with doctor address book: " + doctorAddressBook + " and user prefs " + userPrefs);

        this.doctorAddressBook = new DoctorAddressBook(doctorAddressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredDoctors = new FilteredList<>(this.doctorAddressBook.getDoctorList());
    }

    public DoctorModelManager() {
        this(new DoctorAddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getDoctorAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setDoctorAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== DoctorAddressBook ================================================================================

    @Override
    public void setDoctorAddressBook(ReadOnlyDoctorAddressBook doctorAddressBook) {
        this.doctorAddressBook.resetData(doctorAddressBook);
    }

    @Override
    public ReadOnlyDoctorAddressBook getDoctorAddressBook() {
        return doctorAddressBook;
    }

    @Override
    public boolean hasDoctor(Doctor doctor) {
        requireNonNull(doctor);
        return doctorAddressBook.hasDoctor(doctor);
    }

    @Override
    public void deleteDoctor(Doctor target) {
        doctorAddressBook.removeDoctor(target);
    }

    @Override
    public void addDoctor(Doctor doctor) {
        doctorAddressBook.addDoctor(doctor);
        updateFilteredDoctorList(PREDICATE_SHOW_ALL_DOCTORS);
    }

    @Override
    public void setDoctor(Doctor target, Doctor editedDoctor) {
        requireAllNonNull(target, editedDoctor);

        doctorAddressBook.setDoctor(target, editedDoctor);
    }

    //=========== Filtered Doctor List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Doctor} backed by the internal list of
     * {@code versionedDoctorAddressBook}
     */
    @Override
    public ObservableList<Doctor> getFilteredDoctorList() {
        return filteredDoctors;
    }

    @Override
    public void updateFilteredDoctorList(Predicate<Doctor> predicate) {
        requireNonNull(predicate);
        filteredDoctors.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DoctorModelManager)) {
            return false;
        }

        DoctorModelManager otherModelManager = (DoctorModelManager) other;
        return doctorAddressBook.equals(otherModelManager.doctorAddressBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredDoctors.equals(otherModelManager.filteredDoctors);
    }

}
