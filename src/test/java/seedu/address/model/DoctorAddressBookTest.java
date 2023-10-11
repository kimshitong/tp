package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDoctor.ALICE;
import static seedu.address.testutil.TypicalDoctor.getTypicalDoctorAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.exceptions.DuplicateItemException;
import seedu.address.testutil.DoctorBuilder;

public class DoctorAddressBookTest {

    private final DoctorAddressBook doctorAddressBook = new DoctorAddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), doctorAddressBook.getDoctorList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> doctorAddressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyDoctorAddressBook_replacesData() {
        DoctorAddressBook newData = getTypicalDoctorAddressBook();
        doctorAddressBook.resetData(newData);
        assertEquals(newData, doctorAddressBook);
    }

    @Test
    public void resetData_withDuplicateDoctors_throwsDuplicateDoctorException() {
        // Two doctors with the same identity fields
        Doctor editedAlice = new DoctorBuilder(ALICE).build();
        List<Doctor> newDoctors = Arrays.asList(ALICE, editedAlice);
        DoctorAddressBookStub newData = new DoctorAddressBookStub(newDoctors);

        assertThrows(DuplicateItemException.class, () -> doctorAddressBook.resetData(newData));
    }

    @Test
    public void hasDoctor_nullDoctor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> doctorAddressBook.hasDoctor(null));
    }

    @Test
    public void hasDoctor_doctorNotInDoctorAddressBook_returnsFalse() {
        assertFalse(doctorAddressBook.hasDoctor(ALICE));
    }

    @Test
    public void hasDoctor_personInDoctorAddressBook_returnsTrue() {
        doctorAddressBook.addDoctor(ALICE);
        assertTrue(doctorAddressBook.hasDoctor(ALICE));
    }

    @Test
    public void hasDoctor_doctorWithSameIdentityFieldsInDoctorAddressBook_returnsTrue() {
        doctorAddressBook.addDoctor(ALICE);
        Doctor editedAlice = new DoctorBuilder(ALICE).build();
        assertTrue(doctorAddressBook.hasDoctor(editedAlice));
    }

    @Test
    public void getDoctorList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> doctorAddressBook.getDoctorList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = DoctorAddressBook.class.getCanonicalName()
                + "{doctors=" + doctorAddressBook.getDoctorList() + "}";
        assertEquals(expected, doctorAddressBook.toString());
    }

    /**
     * A stub ReadOnlyDoctorAddressBook whose doctors list can violate interface constraints.
     */
    private static class DoctorAddressBookStub implements ReadOnlyDoctorAddressBook {
        private final ObservableList<Doctor> doctors = FXCollections.observableArrayList();

        DoctorAddressBookStub(Collection<Doctor> doctors) {
            this.doctors.setAll(doctors);
        }

        @Override
        public ObservableList<Doctor> getDoctorList() {
            return doctors;
        }
    }

}
