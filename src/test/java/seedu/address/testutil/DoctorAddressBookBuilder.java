package seedu.address.testutil;

import seedu.address.model.DoctorAddressBook;
import seedu.address.model.person.doctor.Doctor;

/**
 * A utility class to help with building DoctorAddressBook objects.
 * Example usage: <br>
 *     {@code DoctorAddressBook ab = new DoctorAddressBookBuilder().withDoctor("John", "Doe").build();}
 */
public class DoctorAddressBookBuilder {

    private DoctorAddressBook doctorAddressBook;

    public DoctorAddressBookBuilder() {
        doctorAddressBook = new DoctorAddressBook();
    }

    public DoctorAddressBookBuilder(DoctorAddressBook addressBook) {
        this.doctorAddressBook = addressBook;
    }

    /**
     * Adds a new {@code Doctor} to the {@code DoctorAddressBook} that we are building.
     */
    public DoctorAddressBookBuilder withDoctor(Doctor doctor) {
        doctorAddressBook.addDoctor(doctor);
        return this;
    }

    public DoctorAddressBook build() {
        return doctorAddressBook;
    }
}
