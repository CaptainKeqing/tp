package seedu.address;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.address.commons.core.Config;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Version;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.util.ConfigUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.InternshipLogic;
import seedu.address.logic.InternshipLogicManager;
import seedu.address.model.InternshipData;
import seedu.address.model.InternshipModel;
import seedu.address.model.InternshipModelManager;
import seedu.address.model.InternshipUserPrefs;
import seedu.address.model.ReadOnlyInternshipData;
import seedu.address.model.ReadOnlyInternshipUserPrefs;
import seedu.address.model.util.InternshipSampleDataUtil;
import seedu.address.storage.InternshipDataStorage;
import seedu.address.storage.JsonInternshipDataStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.UserPrefsStorage;
import seedu.address.ui.Ui;
import seedu.address.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(0, 2, 2, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected InternshipLogic logic;
    protected Storage storage;
    protected InternshipModel model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing CareerSync ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());
        initLogging(config);

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        InternshipUserPrefs userPrefs = initPrefs(userPrefsStorage);
        InternshipDataStorage internshipDataStorage =
                new JsonInternshipDataStorage(userPrefs.getInternshipDataFilePath());
        storage = new StorageManager(internshipDataStorage, userPrefsStorage);

        model = initModelManager(storage, userPrefs);

        logic = new InternshipLogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s internship data and {@code userPrefs}. <br>
     * The data from the sample internship data will be used instead if {@code storage}'s internship data is not found,
     * or an empty internship data will be used instead if errors occur when reading {@code storage}'s internship data.
     */
    private InternshipModel initModelManager(Storage storage, ReadOnlyInternshipUserPrefs userPrefs) {
        logger.info("Using data file : " + storage.getInternshipDataFilePath());

        Optional<ReadOnlyInternshipData> addressBookOptional;
        ReadOnlyInternshipData initialData;
        try {
            addressBookOptional = storage.readInternshipData();
            if (!addressBookOptional.isPresent()) {
                logger.info("Creating a new data file " + storage.getInternshipDataFilePath()
                        + " populated with a sample InternshipData.");
            }
            initialData = addressBookOptional.orElseGet(InternshipSampleDataUtil::getSampleInternshipData);
        } catch (DataLoadingException e) {
            logger.warning("Data file at " + storage.getInternshipDataFilePath() + " could not be loaded."
                    + " Will be starting with an empty InternshipData.");
            initialData = new InternshipData();
        }

        return new InternshipModelManager(initialData, userPrefs);
    }

    private void initLogging(Config config) {
        LogsCenter.init(config);
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br>
     * The default file path {@code Config#DEFAULT_CONFIG_FILE} will be used instead
     * if {@code configFilePath} is null.
     */
    protected Config initConfig(Path configFilePath) {
        Config initializedConfig;
        Path configFilePathUsed;

        configFilePathUsed = Config.DEFAULT_CONFIG_FILE;

        if (configFilePath != null) {
            logger.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        logger.info("Using config file : " + configFilePathUsed);

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            if (!configOptional.isPresent()) {
                logger.info("Creating new config file " + configFilePathUsed);
            }
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataLoadingException e) {
            logger.warning("Config file at " + configFilePathUsed + " could not be loaded."
                    + " Using default config properties.");
            initializedConfig = new Config();
        }

        //Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }

    /**
     * Returns a {@code InternshipUserPrefs} using the file at {@code storage}'s user prefs file path,
     * or a new {@code InternshipUserPrefs} with default configuration if errors occur when
     * reading from the file.
     */
    protected InternshipUserPrefs initPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using preference file : " + prefsFilePath);

        InternshipUserPrefs initializedPrefs;
        try {
            Optional<InternshipUserPrefs> prefsOptional = storage.readUserPrefs();
            if (!prefsOptional.isPresent()) {
                logger.info("Creating new preference file " + prefsFilePath);
            }
            initializedPrefs = prefsOptional.orElse(new InternshipUserPrefs());
        } catch (DataLoadingException e) {
            logger.warning("Preference file at " + prefsFilePath + " could not be loaded."
                    + " Using default preferences.");
            initializedPrefs = new InternshipUserPrefs();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting InternshipData " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Address Book ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
