package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.address.logic.commands.InternshipAddCommand;
import seedu.address.logic.commands.InternshipEditCommand.EditInternshipDescriptor;
import seedu.address.model.internship.Internship;

/**
 * A utility class for Internship.
 */
public class InternshipUtil {

    /**
     * Returns an add command string for adding the {@code Internship}.
     */
    public static String getAddCommand(Internship internship) {
        return InternshipAddCommand.COMMAND_WORD + " " + getInternshipDetails(internship);
    }

    /**
     * Returns the part of command string for the given {@code Internship}'s details.
     */
    public static String getInternshipDetails(Internship internship) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_COMPANY + " " + internship.getCompanyName().companyName + " ");
        sb.append(PREFIX_CONTACT_NAME + " " + internship.getContactName().contactName + " ");
        sb.append(PREFIX_CONTACT_EMAIL + " " + internship.getContactEmail().value + " ");
        sb.append(PREFIX_CONTACT_NUMBER + " " + internship.getContactNumber().value + " ");
        sb.append(PREFIX_LOCATION + " " + internship.getLocation().toString() + " ");
        sb.append(PREFIX_STATUS + " " + internship.getApplicationStatus().toString() + " ");
        sb.append(PREFIX_DESCRIPTION + " " + internship.getDescription().description + " ");
        sb.append(PREFIX_ROLE + " " + internship.getRole().role + " ");

        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditInternshipDescriptor}'s details.
     */
    public static String getEditInternshipDescriptorDetails(EditInternshipDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getCompanyName().ifPresent(
                companyName -> sb.append(PREFIX_COMPANY).append(" ").append(companyName.companyName).append(" "));
        descriptor.getContactName().ifPresent(
                contactName -> sb.append(PREFIX_CONTACT_NAME).append(" ").append(contactName.contactName).append(" "));
        descriptor.getContactEmail().ifPresent(
                contactEmail -> sb.append(PREFIX_CONTACT_EMAIL).append(" ").append(contactEmail.value).append(" "));
        descriptor.getContactNumber().ifPresent(
                contactNumber -> sb.append(PREFIX_CONTACT_NUMBER).append(" ").append(contactNumber.value).append(" "));
        descriptor.getLocation().ifPresent(
                location -> sb.append(PREFIX_LOCATION).append(" ").append(location).append(" "));
        descriptor.getApplicationStatus().ifPresent(
                applicationStatus -> sb.append(PREFIX_STATUS).append(" ").append(applicationStatus).append(" "));
        descriptor.getDescription().ifPresent(
                description -> sb.append(PREFIX_DESCRIPTION).append(" ").append(description.description).append(" "));
        descriptor.getRole().ifPresent(
                role -> sb.append(PREFIX_ROLE).append(" ").append(role.role).append(" "));

        return sb.toString();
    }
}
