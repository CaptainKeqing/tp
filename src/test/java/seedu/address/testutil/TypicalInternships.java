package seedu.address.testutil;

import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_APPLICATION_STATUS_AMY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_APPLICATION_STATUS_BOB;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_COMPANY_NAME_AMY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_COMPANY_NAME_BOB;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_CONTACT_EMAIL_AMY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_CONTACT_EMAIL_BOB;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_CONTACT_NAME_AMY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_CONTACT_NAME_BOB;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_CONTACT_NUMBER_AMY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_CONTACT_NUMBER_BOB;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_DESCRIPTION_AMY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_LOCATION_AMY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_LOCATION_BOB;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_ROLE_AMY;
import static seedu.address.logic.commands.InternshipCommandTestUtil.VALID_ROLE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.InternshipData;
import seedu.address.model.internship.Internship;

/**
 * A utility class containing a list of {@code Internship} objects to be used in tests.
 */
public class TypicalInternships {

    public static final Internship ALICE_MICROSOFT = new InternshipBuilder().withCompanyName("Microsoft")
            .withContactName("Alice Pauline").withContactEmail("alice@example.com").withContactNumber("94351253")
            .withApplicationStatus("ongoing").withLocation("remote")
            .withDescription("Use Figma to design User-friendly web interfaces").withRole("Frontend Engineer")
            .build();

    public static final Internship BENSON_GOOGLE = new InternshipBuilder().withCompanyName("Google")
            .withContactName("Benson Meier").withContactEmail("benson@example.com").withContactNumber("98765432")
            .withLocation("local").withApplicationStatus("pending").withDescription("Write REST APIs for services")
            .withRole("Backend Engineer").build();

    public static final Internship CARL_OPENAI = new InternshipBuilder().withCompanyName("OpenAI")
            .withContactName("Carl Kurz").withContactEmail("carl@google.com").withContactNumber("95352563")
            .withLocation("overseas").withApplicationStatus("rejected").withDescription("Enter prompts into ChatGPT")
            .withRole("Prompt Engineer").build();

    // Manually added
    public static final Internship HOON_APPLE = new InternshipBuilder().withCompanyName("Apple")
            .withContactName("Hoon Meier").withContactEmail("hoon@gmail.com").withContactNumber("8482424")
            .withLocation("local").withApplicationStatus("pending").withDescription("Develop iOS applications")
            .withRole("iOS Developer").build();

    public static final Internship IDA_NETFLIX = new InternshipBuilder().withCompanyName("Netflix")
            .withContactName("Ida Mueller").withContactEmail("ida@example.com").withContactNumber("8482131")
            .withLocation("remote").withApplicationStatus("ongoing").withDescription("Write REST APIs for netflix")
            .withRole("Backend Engineer").build();

    // Manually added - Internship details found in {@code InternshipCommandTestUtil}
    public static final Internship AMY = new InternshipBuilder().withCompanyName(VALID_COMPANY_NAME_AMY)
            .withContactName(VALID_CONTACT_NAME_AMY).withContactEmail(VALID_CONTACT_EMAIL_AMY)
            .withContactNumber(VALID_CONTACT_NUMBER_AMY).withLocation(VALID_LOCATION_AMY)
            .withApplicationStatus(VALID_APPLICATION_STATUS_AMY).withDescription(VALID_DESCRIPTION_AMY)
            .withRole(VALID_ROLE_AMY).build();

    public static final Internship BOB = new InternshipBuilder().withCompanyName(VALID_COMPANY_NAME_BOB)
            .withContactName(VALID_CONTACT_NAME_BOB).withContactEmail(VALID_CONTACT_EMAIL_BOB)
            .withContactNumber(VALID_CONTACT_NUMBER_BOB).withLocation(VALID_LOCATION_BOB)
            .withApplicationStatus(VALID_APPLICATION_STATUS_BOB).withDescription(VALID_DESCRIPTION_BOB)
            .withRole(VALID_ROLE_BOB).build();

    private TypicalInternships() {
    } // prevents instantiation

    /**
     * Returns an {@code InternshipData} with all the typical internships.
     */
    public static InternshipData getTypicalInternshipData() {
        InternshipData id = new InternshipData();
        for (Internship internship : getTypicalInternships()) {
            id.addInternship(internship);
        }
        return id;
    }
    public static List<Internship> getTypicalInternships() {
        return new ArrayList<>(Arrays.asList(ALICE_MICROSOFT, BENSON_GOOGLE, CARL_OPENAI));
    }
}
