package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;

public class InternshipUserPrefsTest {

    @Test
    public void constructor_nullInternshipUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new InternshipUserPrefs(null));
    }

    @Test
    public void constructor_nullInternshipUserPrefsAndNullGuiSettings_throwsNullPointerException() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setGuiSettings(null));
    }

    @Test
    public void constructor_nonNullInternshipUserPrefsAndNonNullGuiSettings_success() {
        ReadOnlyInternshipUserPrefs emptyUserPrefs = new InternshipUserPrefs();
        InternshipUserPrefs userPrefs = new InternshipUserPrefs(emptyUserPrefs);
        assertEquals(emptyUserPrefs, userPrefs);
    }

    @Test
    public void constructor_nullInternshipUserPrefsAndNonNullGuiSettings_throwsNullPointerException() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        GuiSettings guiSettings = userPrefs.getGuiSettings();
        userPrefs = null;
        InternshipUserPrefs finalUserPrefs = userPrefs;
        assertThrows(NullPointerException.class, () -> finalUserPrefs.setGuiSettings(guiSettings));
    }

    @Test
    public void constructor_nonNullInternshipUserPrefsAndNullGuiSettings_throwsNullPointerException() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        InternshipUserPrefs userPref = new InternshipUserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }
    @Test
    public void setInternshipDataFilePath_nullPath_throwsNullPointerException() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setInternshipDataFilePath(null));
    }
    @Test
    public void resetData_nullUserPrefs_throwsNullPointerException() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.resetData(null));
    }

    @Test
    public void resetData_nullUserPrefsAndNullGuiSettings_throwsNullPointerException() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setGuiSettings(null));
    }

    @Test
    public void resetData_nullUserPrefsAndNonNullGuiSettings_throwsNullPointerException() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        GuiSettings guiSettings = userPrefs.getGuiSettings();
        userPrefs = null;
        InternshipUserPrefs finalUserPrefs = userPrefs;
        assertThrows(NullPointerException.class, () -> finalUserPrefs.setGuiSettings(guiSettings));
    }

    @Test
    public void resetData_nonNullUserPrefsAndNonNullGuiSettings_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        GuiSettings guiSettings = userPrefs.getGuiSettings();
        InternshipUserPrefs userPrefs2 = new InternshipUserPrefs();
        userPrefs2.setGuiSettings(guiSettings);
        userPrefs.resetData(userPrefs2);
        assert(userPrefs.equals(userPrefs2));
    }

    @Test
    public void getInternshipDataFilePath_nonNullInternshipDataFilePath_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        Path intershipDataPath = userPrefs.getInternshipDataFilePath();
        userPrefs.setInternshipDataFilePath(Paths.get("data", "internshipdata2.json"));
        assert(intershipDataPath != null);
    }

    @Test
    public void setInternshipDataFilePath_nonNullInternshipDataFilePath_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        userPrefs.setInternshipDataFilePath(Paths.get("data", "internshipdata2.json"));
        assertEquals(Paths.get("data", "internshipdata2.json"), userPrefs.getInternshipDataFilePath());
    }
    @Test
    public void hashCode_nonNullInternshipDataFilePath_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        userPrefs.setInternshipDataFilePath(Paths.get("data", "internshipdata.json"));
        userPrefs.hashCode();
    }
    @Test
    public void equals_nonNullInternshipDataFilePath_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        userPrefs.setInternshipDataFilePath(Paths.get("data", "internshipdata.json"));
        userPrefs.equals(userPrefs);
    }
    @Test
    public void equals_nullInternshipDataFilePath_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        assertFalse(userPrefs.equals(null));
    }
    @Test
    public void equals_differentInternshipDataFilePath_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipUserPrefs userPrefs2 = new InternshipUserPrefs();
        userPrefs.setInternshipDataFilePath(Paths.get("data", "internshipdata.json"));
        userPrefs2.setInternshipDataFilePath(Paths.get("data", "internshipdata2.json"));
        userPrefs.equals(userPrefs2);
    }

    @Test
    public void equals_sameInternshipDataFilePath_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipUserPrefs userPrefs2 = new InternshipUserPrefs();
        userPrefs.setInternshipDataFilePath(Paths.get("data", "internshipdata.json"));
        userPrefs2.setInternshipDataFilePath(Paths.get("data", "internshipdata.json"));
        userPrefs.equals(userPrefs2);
    }

    @Test
    public void equals_sameInternshipDataFilePathAndGuiSettings_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipUserPrefs userPrefs2 = new InternshipUserPrefs();
        userPrefs.setInternshipDataFilePath(Paths.get("data", "internshipdata.json"));
        userPrefs2.setInternshipDataFilePath(Paths.get("data", "internshipdata.json"));
        userPrefs.setGuiSettings(userPrefs.getGuiSettings());
        userPrefs2.setGuiSettings(userPrefs2.getGuiSettings());
        userPrefs.equals(userPrefs2);
    }

    @Test
    public void equals_differentInternshipDataFilePathAndGuiSettings_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipUserPrefs userPrefs2 = new InternshipUserPrefs();
        userPrefs.setInternshipDataFilePath(Paths.get("data", "internshipdata.json"));
        userPrefs2.setInternshipDataFilePath(Paths.get("data", "internshipdata2.json"));
        userPrefs.setGuiSettings(userPrefs.getGuiSettings());
        userPrefs2.setGuiSettings(userPrefs2.getGuiSettings());
        userPrefs.equals(userPrefs2);
    }

    @Test
    public void equals_differentInternshipDataFilePathAndSameGuiSettings_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipUserPrefs userPrefs2 = new InternshipUserPrefs();
        userPrefs.setInternshipDataFilePath(Paths.get("data", "internshipdata.json"));
        userPrefs2.setInternshipDataFilePath(Paths.get("data", "internshipdata2.json"));
        userPrefs.setGuiSettings(userPrefs.getGuiSettings());
        userPrefs2.setGuiSettings(userPrefs.getGuiSettings());
        userPrefs.equals(userPrefs2);
    }

    @Test
    public void equals_sameInternshipDataFilePathAndDifferentGuiSettings_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipUserPrefs userPrefs2 = new InternshipUserPrefs();
        userPrefs.setInternshipDataFilePath(Paths.get("data", "internshipdata.json"));
        userPrefs2.setInternshipDataFilePath(Paths.get("data", "internshipdata.json"));
        userPrefs.setGuiSettings(userPrefs.getGuiSettings());
        userPrefs2.setGuiSettings(userPrefs2.getGuiSettings());
        userPrefs.equals(userPrefs2);
    }

    @Test
    public void equals_differentInternshipDataFilePathAndNullGuiSettings_nullPointer() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipUserPrefs userPrefs2 = new InternshipUserPrefs();
        userPrefs.setInternshipDataFilePath(Paths.get("data", "internshipdata.json"));
        userPrefs2.setInternshipDataFilePath(Paths.get("data", "internshipdata2.json"));
        userPrefs.setGuiSettings(userPrefs.getGuiSettings());
        assertThrows(NullPointerException.class, () -> userPrefs2.setGuiSettings(null));
    }

    @Test
    public void equals_nullInternshipDataFilePathAndDifferentGuiSettings_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipUserPrefs userPrefs2 = new InternshipUserPrefs();
        userPrefs.setGuiSettings(userPrefs.getGuiSettings());
        userPrefs2.setGuiSettings(userPrefs2.getGuiSettings());
        userPrefs2.setInternshipDataFilePath(Paths.get("data", "internshipdata2.json"));
        userPrefs.equals(userPrefs2);
    }

    @Test
    public void equals_nullInternshipDataFilePathAndNullGuiSettings_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipUserPrefs userPrefs2 = new InternshipUserPrefs();
        userPrefs.equals(userPrefs2);
    }

    @Test
    public void equals_sameInternshipDataFilePathAndNullGuiSettings_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipUserPrefs userPrefs2 = new InternshipUserPrefs();
        userPrefs.setInternshipDataFilePath(Paths.get("data", "internshipdata.json"));
        userPrefs.equals(userPrefs2);
    }

    @Test
    public void equals_nullInternshipDataFilePathAndSameGuiSettings_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipUserPrefs userPrefs2 = new InternshipUserPrefs();
        userPrefs.setGuiSettings(userPrefs.getGuiSettings());
        userPrefs.equals(userPrefs2);
    }

    @Test
    public void equals_differentInternshipDataFilePathAndSameGuiSettingsAndNullInternshipDataFilePath_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipUserPrefs userPrefs2 = new InternshipUserPrefs();
        userPrefs.setInternshipDataFilePath(Paths.get("data", "internshipdata.json"));
        userPrefs2.setGuiSettings(userPrefs.getGuiSettings());
        userPrefs.equals(userPrefs2);
    }

    @Test
    public void toString_nonNullInternshipDataFilePath_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        userPrefs.setInternshipDataFilePath(Paths.get("data", "internshipdata.json"));
        userPrefs.toString();
    }

    @Test
    public void toString_nullInternshipDataFilePath_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        userPrefs.toString();
    }

    @Test
    public void toString_nonNullInternshipDataFilePathAndGuiSettings_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        userPrefs.setInternshipDataFilePath(Paths.get("data", "internshipdata.json"));
        userPrefs.setGuiSettings(userPrefs.getGuiSettings());
        userPrefs.toString();
    }

    @Test
    public void toString_nullInternshipDataFilePathAndGuiSettings_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        userPrefs.setGuiSettings(userPrefs.getGuiSettings());
        userPrefs.toString();
    }

    @Test
    public void toString_nonNullInternshipDataFilePathAndNullGuiSettings_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        userPrefs.setInternshipDataFilePath(Paths.get("data", "internshipdata.json"));
        userPrefs.toString();
    }

    @Test
    public void toString_nullInternshipDataFilePathAndNullGuiSettings_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        userPrefs.toString();
    }

    @Test
    public void hashCode_nonNullGuiSettings_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        userPrefs.setGuiSettings(userPrefs.getGuiSettings());
        userPrefs.hashCode();
    }

    @Test
    public void equals_nonNullGuiSettings_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        userPrefs.setGuiSettings(userPrefs.getGuiSettings());
        userPrefs.equals(userPrefs);
    }

    @Test
    public void equals_nullGuiSettings_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        userPrefs.equals(userPrefs);
    }

    @Test
    public void equals_differentGuiSettings_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipUserPrefs userPrefs2 = new InternshipUserPrefs();
        userPrefs.setGuiSettings(userPrefs.getGuiSettings());
        userPrefs2.setGuiSettings(userPrefs2.getGuiSettings());
        userPrefs.equals(userPrefs2);
    }

    @Test
    public void equals_sameGuiSettings_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipUserPrefs userPrefs2 = new InternshipUserPrefs();
        userPrefs.setGuiSettings(userPrefs.getGuiSettings());
        userPrefs2.setGuiSettings(userPrefs.getGuiSettings());
        userPrefs.equals(userPrefs2);
    }

    @Test
    public void equals_nullGuiSettingsAndDifferentInternshipDataFilePath_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipUserPrefs userPrefs2 = new InternshipUserPrefs();
        userPrefs2.setInternshipDataFilePath(Paths.get("data", "internshipdata2.json"));
        userPrefs.equals(userPrefs2);
    }

    @Test
    public void equals_nullGuiSettingsAndNullInternshipDataFilePath_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipUserPrefs userPrefs2 = new InternshipUserPrefs();
        userPrefs.equals(userPrefs2);
    }

    @Test
    public void equals_sameGuiSettingsAndNullInternshipDataFilePath_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipUserPrefs userPrefs2 = new InternshipUserPrefs();
        userPrefs2.setInternshipDataFilePath(Paths.get("data", "internshipdata.json"));
        userPrefs.equals(userPrefs2);
    }

    @Test
    public void equals_nullGuiSettingsAndSameInternshipDataFilePath_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipUserPrefs userPrefs2 = new InternshipUserPrefs();
        userPrefs.setGuiSettings(userPrefs.getGuiSettings());
        userPrefs.equals(userPrefs2);
    }

    @Test
    public void equals_differentGuiSettingsAndNullInternshipDataFilePath_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipUserPrefs userPrefs2 = new InternshipUserPrefs();
        userPrefs2.setInternshipDataFilePath(Paths.get("data", "internshipdata2.json"));
        userPrefs.setGuiSettings(userPrefs.getGuiSettings());
        userPrefs.equals(userPrefs2);
    }

    @Test
    public void equals_sameGuiSettingsAndDifferentInternshipDataFilePath_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipUserPrefs userPrefs2 = new InternshipUserPrefs();
        userPrefs.setInternshipDataFilePath(Paths.get("data", "internshipdata.json"));
        userPrefs2.setInternshipDataFilePath(Paths.get("data", "internshipdata2.json"));
        userPrefs.setGuiSettings(userPrefs.getGuiSettings());
        userPrefs.equals(userPrefs2);
    }

    @Test
    public void equals_differentGuiSettingsAndSameInternshipDataFilePath_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipUserPrefs userPrefs2 = new InternshipUserPrefs();
        userPrefs.setInternshipDataFilePath(Paths.get("data", "internshipdata.json"));
        userPrefs2.setInternshipDataFilePath(Paths.get("data", "internshipdata.json"));
        userPrefs.setGuiSettings(userPrefs.getGuiSettings());
        userPrefs2.setGuiSettings(userPrefs2.getGuiSettings());
        userPrefs.equals(userPrefs2);
    }

    @Test
    public void equals_sameGuiSettingsAndSameInternshipDataFilePath_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipUserPrefs userPrefs2 = new InternshipUserPrefs();
        userPrefs.setInternshipDataFilePath(Paths.get("data", "internshipdata.json"));
        userPrefs2.setInternshipDataFilePath(Paths.get("data", "internshipdata.json"));
        userPrefs.setGuiSettings(userPrefs.getGuiSettings());
        userPrefs2.setGuiSettings(userPrefs2.getGuiSettings());
        userPrefs.equals(userPrefs2);
    }

    @Test
    public void equals_differentGuiSettingsAndDifferentInternshipDataFilePath_success() {
        InternshipUserPrefs userPrefs = new InternshipUserPrefs();
        InternshipUserPrefs userPrefs2 = new InternshipUserPrefs();
        userPrefs.setInternshipDataFilePath(Paths.get("data", "internshipdata.json"));
        userPrefs2.setInternshipDataFilePath(Paths.get("data", "internshipdata2.json"));
        userPrefs.setGuiSettings(userPrefs.getGuiSettings());
        userPrefs2.setGuiSettings(userPrefs2.getGuiSettings());
        userPrefs.equals(userPrefs2);
    }


}
