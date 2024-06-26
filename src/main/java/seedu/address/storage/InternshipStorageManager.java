package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.InternshipUserPrefs;
import seedu.address.model.ReadOnlyInternshipData;
import seedu.address.model.ReadOnlyInternshipUserPrefs;

/**
 * Manages storage of InternshipData in local storage.
 */
public class InternshipStorageManager implements InternshipStorage {

    private static final Logger logger = LogsCenter.getLogger(InternshipStorageManager.class);
    private InternshipDataStorage internshipDataStorage;
    private InternshipUserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code InternshipStorageManager} with the given
     * {@code InternshipDataStorage} and {@code UserPrefStorage}.
     */
    public InternshipStorageManager(InternshipDataStorage internshipDataStorage,
                                    InternshipUserPrefsStorage userPrefsStorage) {
        this.internshipDataStorage = internshipDataStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    /**
     * Returns the file path of the data file.
     */
    @Override
    public Optional<InternshipUserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    /**
     * Saves the given {@link ReadOnlyInternshipUserPrefs} to the storage.
     * @param userPrefs cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    @Override
    public void saveUserPrefs(ReadOnlyInternshipUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Path getInternshipDataFilePath() {
        return internshipDataStorage.getInternshipDataFilePath();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ReadOnlyInternshipData> readInternshipData() throws DataLoadingException {
        return readInternshipData(internshipDataStorage.getInternshipDataFilePath());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ReadOnlyInternshipData> readInternshipData(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return internshipDataStorage.readInternshipData(filePath);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveInternshipData(ReadOnlyInternshipData internshipData) throws IOException {
        saveInternshipData(internshipData, internshipDataStorage.getInternshipDataFilePath());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveInternshipData(ReadOnlyInternshipData internshipData, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        internshipDataStorage.saveInternshipData(internshipData, filePath);
    }

}
