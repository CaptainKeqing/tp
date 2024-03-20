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

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.InternshipMessages;
import seedu.address.model.InternshipModel;
import seedu.address.model.internship.Internship;

/**
 * Finds and lists all internships in internship data whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class InternshipFindCommand extends InternshipCommand {
    public static final String COMMAND_WORD = "find";
    public static final String MODE_WITHALL = "withall";
    public static final String MODE_WITHANY = "withany";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all internships whose fields, specified using "
            + "the prefix, contain all/any (depending on mode) of the specified keywords (case-insensitive) "
            + "and displays them as a list with index numbers. \n"
            + "Parameters: MODE ('withall' or 'withany', to specify if all keywords "
            + "must be present or just any keyword must be present)\n"
            + "[" + PREFIX_COMPANY + " COMPANY_NAME_KEYWORD [MORE_COMPANY_NAME_KEYWORDS]\n"
            + "[" + PREFIX_CONTACT_NAME + " CONTACT_NAME_KEYWORD [MORE_CONTACT_NAME_KEYWORDS]\n"
            + "[" + PREFIX_CONTACT_EMAIL + " CONTACT_EMAIL_KEYWORD [MORE_CONTACT_EMAIL_KEYWORDS]\n"
            + "[" + PREFIX_CONTACT_NUMBER + " CONTACT_NUMBER_KEYWORD [MORE_CONTACT_NUMBER_KEYWORDS]\n"
            + "[" + PREFIX_LOCATION + " LOCATION_KEYWORD [MORE_LOCATION_KEYWORDS]\n"
            + "[" + PREFIX_STATUS + " STATUS_KEYWORD [MORE_STATUS_KEYWORDS]\n"
            + "[" + PREFIX_DESCRIPTION + " DESCRIPTION_KEYWORD [MORE_DESCRIPTION_KEYWORDS]\n"
            + "[" + PREFIX_ROLE + " ROLE_KEYWORD [MORE_ROLE_KEYWORDS]\n"
            + "Example: " + COMMAND_WORD + " withany "
            + PREFIX_COMPANY + " Tiktok Google " + PREFIX_STATUS + " accepted";
    public static final String NO_SEARCH_KEY_SPECIFIED = "At least one field and keyword "
            + "must be specified to be searched.";

    private final Predicate<Internship> predicate;

    public InternshipFindCommand(Predicate<Internship> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(InternshipModel model) {
        requireNonNull(model);
        model.updateFilteredInternshipList(predicate);
        return new CommandResult(
                String.format(InternshipMessages.MESSAGE_INTERNSHIPS_LISTED_OVERVIEW,
                        model.getFilteredInternshipList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InternshipFindCommand)) {
            return false;
        }

        InternshipFindCommand otherFindCommand = (InternshipFindCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
