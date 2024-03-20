package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.InternshipMessages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.InternshipModel;
import seedu.address.model.internship.Internship;

/**
 * Adds an internship to internship data.
 */
public class InternshipAddCommand extends InternshipCommand {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an internship to internship data. "
            + "Parameters: "
            + PREFIX_COMPANY + " COMPANY_NAME "
            + PREFIX_DESCRIPTION + " DESCRIPTION "
            + PREFIX_STATUS + " STATUS "
            + PREFIX_CONTACT_NAME + " CONTACT_NAME "
            + PREFIX_CONTACT_EMAIL + " CONTACT_EMAIL "
            + PREFIX_CONTACT_NUMBER + " CONTACT_NUMBER "
            + PREFIX_LOCATION + " LOCATION "
            + PREFIX_ROLE + " ROLE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_COMPANY + " Tiktok "
            + PREFIX_DESCRIPTION + " create new recommendation engine "
            + PREFIX_STATUS + " ongoing "
            + PREFIX_CONTACT_NAME + " jane yeo "
            + PREFIX_CONTACT_EMAIL + " hr@tiktok.com "
            + PREFIX_CONTACT_NUMBER + " 9089030 "
            + PREFIX_LOCATION + " remote "
            + PREFIX_ROLE + " Software Intern";

    public static final String MESSAGE_SUCCESS = "New internship added: %1$s";
    public static final String MESSAGE_DUPLICATE_INTERNSHIP = "This internship already exists in the internship list";

    private final Internship toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Internship}.
     */
    public InternshipAddCommand(Internship internship) {
        requireNonNull(internship);
        toAdd = internship;
    }

    @Override
    public CommandResult execute(InternshipModel model) throws CommandException {
        requireNonNull(model);

        if (model.hasInternship(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERNSHIP);
        }

        model.addInternship(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, InternshipMessages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InternshipAddCommand)) {
            return false;
        }

        InternshipAddCommand otherAddCommand = (InternshipAddCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
