package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.patient.UniqueItemList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSame comparison)
 */
public class DoctorAddressBook implements ReadOnlyDoctorAddressBook {

    private final UniqueItemList<Doctor> doctors;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        doctors = new UniqueItemList<Doctor>();
    }

    public DoctorAddressBook() {}

    /**
     * Creates an AddressBook using the Doctors in the {@code toBeCopied}
     */
    public DoctorAddressBook(ReadOnlyDoctorAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the doctor list with {@code doctors}.
     * {@code doctors} must not contain duplicate doctors.
     */
    public void setDoctors(List<Doctor> doctors) {
        this.doctors.setItems(doctors);
    }

    /**
     * Resets the existing data of this {@code Do9ctorAddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyDoctorAddressBook newData) {
        requireNonNull(newData);

        setDoctors(newData.getDoctorList());
    }

    //// doctor-level operations

    /**
     * Returns true if a doctor with the same identity as {@code doctor} exists in the doctor address book.
     */
    public boolean hasDoctor(Doctor doctor) {
        requireNonNull(doctor);
        return doctors.contains(doctor);
    }

    /**
     * Adds a doctor to the doctor address book.
     * The doctor must not already exist in the doctor address book.
     */
    public void addDoctor(Doctor d) {
        doctors.add(d);
    }

    /**
     * Replaces the given doctor {@code target} in the list with {@code editedDoctor}.
     * {@code target} must exist in the doctor address book.
     * The doctor identity of {@code editedDoctor} must not be the same
     * as another existing doctor in the doctor address book.
     */
    public void setDoctor(Doctor target, Doctor editedDoctor) {
        requireNonNull(editedDoctor);

        doctors.setItem(target, editedDoctor);
    }

    /**
     * Removes {@code key} from this {@code DoctorAddressBook}.
     * {@code key} must exist in the doctor address book.
     */
    public void removeDoctor(Doctor key) {
        doctors.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("doctors", doctors)
                .toString();
    }

    @Override
    public ObservableList<Doctor> getDoctorList() {
        return doctors.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DoctorAddressBook)) {
            return false;
        }

        DoctorAddressBook otherAddressBook = (DoctorAddressBook) other;
        return doctors.equals(otherAddressBook.doctors);
    }

    @Override
    public int hashCode() {
        return doctors.hashCode();
    }
}
