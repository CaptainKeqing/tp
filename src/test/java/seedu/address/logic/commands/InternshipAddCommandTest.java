package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternships.HOON_APPLE;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.InternshipMessages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.InternshipData;
import seedu.address.model.InternshipModel;
import seedu.address.model.ReadOnlyInternshipData;
import seedu.address.model.ReadOnlyInternshipUserPrefs;
import seedu.address.model.internship.Internship;
import seedu.address.testutil.InternshipBuilder;

public class InternshipAddCommandTest {

    @Test
    public void constructor_nullInternship_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new InternshipAddCommand(null));
    }

    @Test
    public void execute_internshipAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingInternshipAdded modelStub = new ModelStubAcceptingInternshipAdded();
        Internship validInternship = new InternshipBuilder().build();

        CommandResult commandResult = new InternshipAddCommand(validInternship).execute(modelStub);

        assertEquals(String.format(InternshipAddCommand.MESSAGE_SUCCESS, InternshipMessages.format(validInternship)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validInternship), modelStub.internshipsAdded);
    }

    @Test
    public void execute_duplicateInternship_throwsCommandException() {
        Internship validInternship = new InternshipBuilder().build();
        InternshipAddCommand internshipAddCommand = new InternshipAddCommand(validInternship);
        ModelStub modelStub = new ModelStubWithInternship(validInternship);

        assertThrows(CommandException.class, internshipAddCommand.MESSAGE_DUPLICATE_INTERNSHIP, ()
                -> internshipAddCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Internship alice = new InternshipBuilder().withCompanyName("Microsoft").build();
        Internship bob = new InternshipBuilder().withCompanyName("Google").build();
        InternshipAddCommand addAliceCommand = new InternshipAddCommand(alice);
        InternshipAddCommand addBobCommand = new InternshipAddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        InternshipAddCommand addAliceCommandCopy = new InternshipAddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different Internship -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    @Test
    public void toStringMethod() {
        InternshipAddCommand internshipAddCommand = new InternshipAddCommand(HOON_APPLE);
        String expected = InternshipAddCommand.class.getCanonicalName() + "{toAdd=" + HOON_APPLE + "}";
        assertEquals(expected, internshipAddCommand.toString());
    }

    /**
     * A default model stub that have all methods failing.
     */
    private class ModelStub implements InternshipModel {
        @Override
        public void setUserPrefs(ReadOnlyInternshipUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyInternshipUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getInternshipDataFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInternshipDataFilePath(Path internshipDataFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addInternship(Internship internship) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInternshipData(ReadOnlyInternshipData newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyInternshipData getInternshipData() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasInternship(Internship internship) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteInternship(Internship target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInternship(Internship target, Internship editedInternship) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Internship> getFilteredInternshipList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredInternshipList(Predicate<Internship> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single internship entry.
     */
    private class ModelStubWithInternship extends ModelStub {
        private final Internship internship;

        ModelStubWithInternship(Internship internship) {
            requireNonNull(internship);
            this.internship = internship;
        }

        @Override
        public boolean hasInternship(Internship internship) {
            requireNonNull(internship);
            return this.internship.isSameInternship(internship);
        }
    }

    /**
     * A Model stub that always accept the Internship being added.
     */
    private class ModelStubAcceptingInternshipAdded extends ModelStub {
        final ArrayList<Internship> internshipsAdded = new ArrayList<>();

        @Override
        public boolean hasInternship(Internship internship) {
            requireNonNull(internship);
            return internshipsAdded.stream().anyMatch(internship::isSameInternship);
        }

        @Override
        public void addInternship(Internship internship) {
            requireNonNull(internship);
            internshipsAdded.add(internship);
        }

        @Override
        public ReadOnlyInternshipData getInternshipData() {
            return new InternshipData();
        }
    }
}
