package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.ApplicationStatus.StatusEnum;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.ContactEmail;
import seedu.address.model.internship.ContactName;
import seedu.address.model.internship.ContactNumber;
import seedu.address.model.internship.Description;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Location;
import seedu.address.model.internship.Role;

/**
 * A UI component that displays information of a {@code Internship}.
 */
public class InternshipCard extends UiPart<Region> {

    private static final String FXML = "InternshipCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */
    private static final String STYLE_WITH_FONT_COLOUR_RED = "-fx-text-fill: #ff0000;";
    private static final String STYLE_WITH_FONT_COLOUR_GREEN = "-fx-text-fill: #00ff00;";
    private static final String STYLE_WITH_FONT_COLOUR_YELLOW = "-fx-text-fill: #ffff00;";
    private static final String STYLE_WITH_FONT_COLOUR_CYAN = "-fx-text-fill: #00ffff;";
    private static final String STYLE_WITH_FONT_COLOUR_LIGHTSEAGREEN = "-fx-text-fill: #20b2aa;";

    public final Internship internship;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label companyNameAndRole;
    @FXML
    private Label status;
    @FXML
    private Label description;
    @FXML
    private Label loc;
    @FXML
    private Label poc;

    /**
     * Creates a {@code InternshipCard} with the given {@code Internship} and index to display.
     */
    public InternshipCard(Internship internship, int displayedIndex) {
        super(FXML);
        this.internship = internship;

        id.setText(displayedIndex + ". ");
        setCompanyNameAndRoleLabel(internship.getCompanyName(), internship.getRole());
        setStatusLabel(internship.getApplicationStatus());
        setDescriptionLabel(internship.getDescription());
        setLocationLabel(internship.getLocation());
        setPocLabel(internship.getContactName(), internship.getContactEmail(), internship.getContactNumber());
    }

    /**
     * Sets the POC label to the given contact details.
     *
     * @param contactName name of the contact person under the Internship entry
     * @param contactEmail email of the contact person under the Internship entry
     * @param contactNumber phone number of the contact person under the Internship entry
     */
    private void setPocLabel(ContactName contactName, ContactEmail contactEmail, ContactNumber contactNumber) {
        String stringToSet = "POC: " + contactName.toString() + " | "
                + contactEmail.toString() + " | " + contactNumber.toString();
        poc.setText(stringToSet);
    }

    /**
     * Sets the location label to the given location.
     *
     * @param loc location of the internship
     */
    private void setLocationLabel(Location loc) {
        this.loc.setText(loc.toString());
    }

    /**
     * Sets the description label to the given description.
     *
     * @param desc description of the internship
     */
    private void setDescriptionLabel(Description desc) {
        description.setText(desc.toString());
    }

    /**
     * Sets the company name and role label to the given company name and role.
     *
     * @param companyName name of the company under the Internship entry
     * @param role role of the internship under the Internship entry
     */
    private void setCompanyNameAndRoleLabel(CompanyName companyName, Role role) {
        String stringToSet = companyName.toString() + " -- " + role.toString();
        companyNameAndRole.setText(stringToSet);
    }

    /**
     * Sets the status label to the given application status.
     *
     * @param applicationStatus status of the application under the Internship entry
     */
    private void setStatusLabel(ApplicationStatus applicationStatus) {
        status.setText(" * " + applicationStatus.toString());
        setStatusLabelColour(applicationStatus.getStatus());
    }

    /**
     * Sets the colour of the status label based on the status of the application.
     *
     * @param statusEnum status of the application under the Internship entry
     */
    private void setStatusLabelColour(StatusEnum statusEnum) {
        switch (statusEnum) {
        case TO_APPLY:
            status.setStyle(STYLE_WITH_FONT_COLOUR_CYAN);
            break;
        case PENDING:
            status.setStyle(STYLE_WITH_FONT_COLOUR_YELLOW);
            break;
        case REJECTED:
            status.setStyle(STYLE_WITH_FONT_COLOUR_RED);
            break;
        case ACCEPTED:
            status.setStyle(STYLE_WITH_FONT_COLOUR_GREEN);
            break;
        case ONGOING:
            status.setStyle(STYLE_WITH_FONT_COLOUR_LIGHTSEAGREEN);
            break;
        default:
            throw new IllegalArgumentException("Unexpected application status: "
                    + statusEnum);
        }
    }

    protected Label getIdLabel() {
        return id;
    }

    protected Label getCompanyNameAndRoleLabel() {
        return companyNameAndRole;
    }

    protected Label getStatusLabel() {
        return status;
    }

    protected Label getDescriptionLabel() {
        return description;
    }

    protected Label getLocationLabel() {
        return loc;
    }

    protected Label getPocLabel() {
        return poc;
    }
}
