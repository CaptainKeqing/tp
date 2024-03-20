package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.InternshipUserPrefs;
import seedu.address.model.ReadOnlyInternshipUserPrefs;

/**
 * A class to access UserPrefs stored in the hard disk as a json file
 */
public class JsonUserPrefsStorage implements UserPrefsStorage {

    private Path filePath;

    public JsonUserPrefsStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getUserPrefsFilePath() {
        return filePath;
    }

    @Override
    public Optional<InternshipUserPrefs> readUserPrefs() throws DataLoadingException {
        return readUserPrefs(filePath);
    }

    /**
     * Similar to {@link #readUserPrefs()}
     * @param prefsFilePath location of the data. Cannot be null.
     * @throws DataLoadingException if the file format is not as expected.
     */
    public Optional<InternshipUserPrefs> readUserPrefs(Path prefsFilePath) throws DataLoadingException {
        return JsonUtil.readJsonFile(prefsFilePath, InternshipUserPrefs.class);
    }

    @Override
    public void saveUserPrefs(ReadOnlyInternshipUserPrefs userPrefs) throws IOException {
        JsonUtil.saveJsonFile(userPrefs, filePath);
    }

}
