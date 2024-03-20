package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.InternshipCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalInternships.getTypicalInternshipData;

import org.junit.jupiter.api.Test;

import seedu.address.model.InternshipData;
import seedu.address.model.InternshipModel;
import seedu.address.model.InternshipModelManager;
import seedu.address.model.InternshipUserPrefs;

/**
 * Contains integration tests (interaction with the InternshipModel) and unit tests for InternshipClearCommand.
 */
public class InternshipClearCommandTest {

    @Test
    public void execute_emptyInternshipData_success() {
        InternshipModel model = new InternshipModelManager();
        InternshipModel expectedModel = new InternshipModelManager();

        assertCommandSuccess(new InternshipClearCommand(), model,
                InternshipClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyInternshipData_success() {
        InternshipModel model = new InternshipModelManager(getTypicalInternshipData(), new InternshipUserPrefs());
        InternshipModel expectedModel = new InternshipModelManager(getTypicalInternshipData(),
                new InternshipUserPrefs());
        expectedModel.setInternshipData(new InternshipData());

        assertCommandSuccess(new InternshipClearCommand(), model,
                InternshipClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_commandWordIsClear_success() {
        assertEquals(InternshipClearCommand.COMMAND_WORD, "clear");
    }
}
