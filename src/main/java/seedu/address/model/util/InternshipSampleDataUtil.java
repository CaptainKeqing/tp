package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.InternshipData;
import seedu.address.model.ReadOnlyInternshipData;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.ContactEmail;
import seedu.address.model.internship.ContactName;
import seedu.address.model.internship.ContactNumber;
import seedu.address.model.internship.Description;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Location;
import seedu.address.model.internship.Role;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code InternshipData} with sample data.
 */
public class InternshipSampleDataUtil {
    public static Internship[] getSampleInternships() {
        return new Internship[]{
            new Internship(new CompanyName("Google"), new ContactName("John Doe"),
                    new ContactEmail("johndoe@example.com"), new ContactNumber("12345678"),
                    new Location("remote"), new ApplicationStatus("to_apply"),
                    new Description("Software Engineering Internship"),
                    new Role("Software Engineer")),
            new Internship(new CompanyName("Facebook"), new ContactName("Jane Smith"),
                    new ContactEmail("janesmith@example.com"), new ContactNumber("98765432"),
                    new Location("local"), new ApplicationStatus("pending"),
                    new Description("Product Management Internship"),
                    new Role("Product Manager")),
            new Internship(new CompanyName("Amazon"), new ContactName("Mark Johnson"),
                    new ContactEmail("markjohnson@example.com"), new ContactNumber("45678901"),
                    new Location("remote"), new ApplicationStatus("rejected"),
                    new Description("Business Development Internship"),
                    new Role("Business Development Associate")),
        };
    }

    public static ReadOnlyInternshipData getSampleInternshipData() {
        InternshipData sampleInternshipData = new InternshipData();
        for (Internship sampleInternship : getSampleInternships()) {
            sampleInternshipData.addInternship(sampleInternship);
        }
        return sampleInternshipData;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }
}
