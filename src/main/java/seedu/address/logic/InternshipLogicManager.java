package seedu.address.logic;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.InternshipCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.InternshipDataParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.InternshipModel;
import seedu.address.model.ReadOnlyInternshipData;
import seedu.address.model.internship.Internship;
import seedu.address.storage.Storage;

/**
 * The main InternshipLogicManager of the app.
 */
public class InternshipLogicManager implements InternshipLogic {
    public static final String FILE_OPS_ERROR_FORMAT = "Could not save data due to the following error: %s";

    public static final String FILE_OPS_PERMISSION_ERROR_FORMAT =
            "Could not save data to file %s due to insufficient permissions to write to the file or the folder.";

    private final Logger logger = LogsCenter.getLogger(InternshipLogicManager.class);

    private final InternshipModel model;
    private final Storage storage;
    private final InternshipDataParser internshipDataParser;

    /**
     * Constructs a {@code InternshipLogicManager} with the
     * given {@code InternshipModel} and {@code Storage}.
     */
    public InternshipLogicManager(InternshipModel model, Storage storage) {
        this.model = model;
        this.storage = storage;
        internshipDataParser = new InternshipDataParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        InternshipCommand command = internshipDataParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveInternshipData(model.getInternshipData());
        } catch (AccessDeniedException e) {
            throw new CommandException(String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
        } catch (IOException ioe) {
            throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyInternshipData getInternshipData() {
        return model.getInternshipData();
    }

    @Override
    public ObservableList<Internship> getFilteredInternshipList() {
        return model.getFilteredInternshipList();
    }

    @Override
    public Path getInternshipDataFilePath() {
        return model.getInternshipDataFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
