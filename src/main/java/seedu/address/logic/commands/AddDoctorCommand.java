package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.DoctorCliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.DoctorCliSyntax.PREFIX_NRIC;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.DoctorMessages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.DoctorModel;
import seedu.address.model.person.doctor.Doctor;

/**
 * Adds a doctor to the address book.
 */
public class AddDoctorCommand extends DoctorCommand {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a doctor to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_NRIC + "NRIC";

    public static final String MESSAGE_SUCCESS = "New doctor added: %1$s";
    public static final String MESSAGE_DUPLICATE_DOCTOR = "This doctor already exists in the address book";

    private final Doctor toAdd;

    /**
     * Creates an AddDoctorCommand to add the specified {@code Doctor}
     */
    public AddDoctorCommand(Doctor doctor) {
        requireNonNull(doctor);
        toAdd = doctor;
    }

    @Override
    public CommandResult execute(DoctorModel model) throws CommandException {
        requireNonNull(model);

        if (model.hasDoctor(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_DOCTOR);
        }

        model.addDoctor(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, DoctorMessages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddDoctorCommand)) {
            return false;
        }

        AddDoctorCommand otherAddCommand = (AddDoctorCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
