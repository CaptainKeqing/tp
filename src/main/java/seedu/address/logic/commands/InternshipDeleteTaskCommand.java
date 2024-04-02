package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SELECT_TASK;
import static seedu.address.model.InternshipModel.PREDICATE_SHOW_ALL_INTERNSHIPS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.InternshipMessages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.InternshipModel;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Task;

/**
 * Deletes an existing task from an existing Internship
 */
public class InternshipDeleteTaskCommand extends InternshipCommand {

    public static final String COMMAND_WORD = "deletetask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete a task from the internship identified "
            + "by the index number used in the displayed internship data.\n"
            + "Parameters: INDEX (must be a positive integer) " + PREFIX_SELECT_TASK + " TASK_INDEX\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_SELECT_TASK + " 1";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Task Deleted: %1$s";

    private final Index internshipIndex;
    private final Index taskIndex;

    /**
     * @param internshipIndex index of the internship in the filtered internship list to edit
     * @param taskIndex index of the task in the selected internship to edit
     */
    public InternshipDeleteTaskCommand(Index internshipIndex, Index taskIndex) {
        requireNonNull(internshipIndex);
        requireNonNull(taskIndex);

        this.internshipIndex = internshipIndex;
        this.taskIndex = taskIndex;
    }

    @Override
    public CommandResult execute(InternshipModel model) throws CommandException {
        requireNonNull(model);
        List<Internship> lastShownList = model.getFilteredInternshipList();

        if (internshipIndex.getOneBased() > lastShownList.size()) {
            throw new CommandException(InternshipMessages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
        }

        Internship internshipToDeleteTask = lastShownList.get(internshipIndex.getZeroBased());

        if (taskIndex.getOneBased() > internshipToDeleteTask.getTaskList().getTaskListSize()) {
            throw new CommandException(InternshipMessages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
        Task taskToDelete = internshipToDeleteTask.getTaskList().getTask(taskIndex.getZeroBased());
        internshipToDeleteTask.getTaskList().deleteTask(taskIndex.getZeroBased());

        // This is necessary to trigger the UI to update
        model.setInternship(internshipToDeleteTask, internshipToDeleteTask);

        model.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);

        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, taskToDelete));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InternshipDeleteTaskCommand)) {
            return false;
        }

        InternshipDeleteTaskCommand otherDeleteCommand = (InternshipDeleteTaskCommand) other;
        return internshipIndex.equals(otherDeleteCommand.internshipIndex)
                && taskIndex.equals(otherDeleteCommand.taskIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("internshipIndex", internshipIndex)
                .add("taskIndex", taskIndex)
                .toString();
    }
}
