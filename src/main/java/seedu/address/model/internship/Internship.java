package seedu.address.model.internship;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents an Internship in the internship book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Internship {
    // Identity fields
    private final CompanyName companyName;
    private final Location location;
    private final Description description;
    private final Role role;

    // Data fields
    private final ContactName contactName;
    private final ContactEmail contactEmail;
    private final ContactNumber contactNumber;
    private final ApplicationStatus applicationStatus;
    /**
     * Every field must be present and not null.
     */
    public Internship(CompanyName companyName, ContactName contactName, ContactEmail contactEmail,
                      ContactNumber contactNumber, Location location, ApplicationStatus applicationStatus,
                      Description description, Role role) {
        requireAllNonNull(companyName, contactName, contactEmail, contactNumber, location, applicationStatus,
                description, role);
        this.companyName = companyName;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.contactNumber = contactNumber;
        this.location = location;
        this.applicationStatus = applicationStatus;
        this.description = description;
        this.role = role;
    }

    public CompanyName getCompanyName() {
        return companyName;
    }

    public ContactName getContactName() {
        return contactName;
    }

    public ContactEmail getContactEmail() {
        return contactEmail;
    }

    public ContactNumber getContactNumber() {
        return contactNumber;
    }

    public Location getLocation() {
        return location;
    }

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public Description getDescription() {
        return description;
    }

    public Role getRole() {
        return role;
    }

    /**
     * Returns true if both internships have the same company name, location, role and description.
     * This defines the same notion of equality between two internships. (Identity fields)
     */
    public boolean isSameInternship(Internship otherInternship) {
        if (otherInternship == this) {
            return true;
        }

        return otherInternship != null
                && companyName.equals(otherInternship.companyName)
                && location.equals(otherInternship.location)
                && role.equals(otherInternship.role)
                && description.equals(otherInternship.description);
    }

    /**
     * Returns true if both internships have the same identity and data fields.
     * This defines a stronger notion of equality between two internships.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Internship)) {
            return false;
        }

        Internship otherInternship = (Internship) other;
        return companyName.equals(otherInternship.companyName)
                && contactName.equals(otherInternship.contactName)
                && contactEmail.equals(otherInternship.contactEmail)
                && contactNumber.equals(otherInternship.contactNumber)
                && location.equals(otherInternship.location)
                && applicationStatus.equals(otherInternship.applicationStatus)
                && description.equals(otherInternship.description)
                && role.equals(otherInternship.role);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(companyName, location, role, description);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("companyName", companyName)
                .add("contactName", contactName)
                .add("contactEmail", contactEmail)
                .add("contactNumber", contactNumber)
                .add("location", location)
                .add("applicationStatus", applicationStatus)
                .add("description", description)
                .add("role", role)
                .toString();
    }
}
