package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.InternshipCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.InternshipCommandTestUtil.showInternshipAtIndex;
import static seedu.address.testutil.InternshipTypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.address.testutil.TypicalInternships.getTypicalInternshipData;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.InternshipModel;
import seedu.address.model.InternshipModelManager;
import seedu.address.model.InternshipUserPrefs;

/**
 * Contains integration tests (interaction with the InternshipModel) and unit tests for InternshipListCommand.
 */
public class InternshipListCommandTest {

    private InternshipModel model;
    private InternshipModel expectedModel;

    @BeforeEach
    public void setUp() {
        model = new InternshipModelManager(getTypicalInternshipData(), new InternshipUserPrefs());
        expectedModel = new InternshipModelManager(model.getInternshipData(), new InternshipUserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new InternshipListCommand(), model, InternshipListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);
        assertCommandSuccess(new InternshipListCommand(), model, InternshipListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_commandWordIsList_success() {
        assertEquals(InternshipListCommand.COMMAND_WORD, "list");
    }
    // Add tests for list if its sorted in the future
}
