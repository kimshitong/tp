package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.doctor.Doctor;

/**
 * Unmodifiable view of a doctor address book
 */
public interface ReadOnlyDoctorAddressBook {

    /**
     * Returns an unmodifiable view of the doctors list.
     * This list will not contain any duplicate doctors.
     */
    ObservableList<Doctor> getDoctorList();

}
