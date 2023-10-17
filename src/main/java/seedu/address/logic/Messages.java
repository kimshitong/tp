package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.patient.Patient;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX =
            "The appointment index provided is invalid";

    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName())
                .append("; Phone: ")
                .append(person.getPhone())
                .append("; Email: ")
                .append(person.getEmail())
                .append("; Address: ")
                .append(person.getAddress())
                .append("; Tags: ");
        person.getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the {@code appointment} for display to the user.
     */
    public static String format(Appointment appointment) {
        final StringBuilder builder = new StringBuilder();
        builder.append("Patient NRIC: ")
                .append(appointment.getPatientNric())
                .append("; Doctor NRIC: ")
                .append(appointment.getDoctorNric())
                .append("; From: ")
                .append(appointment.getStartTime())
                .append("; to: ")
                .append(appointment.getEndTime());
        return builder.toString();
    }

    /**
     * Formats the {@code patient} for display to the user.
     */

    public static String format(Patient patient) {
        final StringBuilder builder = new StringBuilder();
        builder.append(patient.getName())
                .append("; Phone: ")
                .append(patient.getPhone())
                .append("; NRIC: ")
                .append(patient.getNric());
        return builder.toString();
    }

    /**
     * Formats the {@code doctor} for display to the user.
     */
    public static String format(Doctor doctor) {
        final StringBuilder builder = new StringBuilder();
        builder.append(doctor.getName())
                .append("; Nric: ")
                .append(doctor.getNric());
        return builder.toString();
    }


}
