package seedu.address.logic.commands;

import static seedu.address.logic.commands.InternshipCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.InternshipCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalInternships.getTypicalInternshipData;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.InternshipMessages;
import seedu.address.model.InternshipModel;
import seedu.address.model.InternshipModelManager;
import seedu.address.model.InternshipUserPrefs;
import seedu.address.model.internship.Internship;
import seedu.address.testutil.InternshipBuilder;

/**
 * Contains integration tests (interaction with the InternshipModel) for {@code InternshipAddCommand}.
 */
public class InternshipAddCommandIntegrationTest {

    private InternshipModel model;

    @BeforeEach
    public void setUp() {
        model = new InternshipModelManager(getTypicalInternshipData(), new InternshipUserPrefs());
    }

    @Test
    public void execute_newInternship_success() {
        Internship validInternship = new InternshipBuilder().build();

        InternshipModel expectedModel = new InternshipModelManager(model.getInternshipData(),
                new InternshipUserPrefs());
        expectedModel.addInternship(validInternship);

        assertCommandSuccess(new InternshipAddCommand(validInternship), model,
                String.format(InternshipAddCommand.MESSAGE_SUCCESS, InternshipMessages.format(validInternship)),
                expectedModel);
    }

    @Test
    public void execute_duplicateInternship_throwsCommandException() {
        Internship internshipInList = model.getInternshipData().getInternshipList().get(0);
        assertCommandFailure(new InternshipAddCommand(internshipInList), model,
                InternshipAddCommand.MESSAGE_DUPLICATE_INTERNSHIP);
    }

}
