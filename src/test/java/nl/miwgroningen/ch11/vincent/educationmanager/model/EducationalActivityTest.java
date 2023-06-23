package nl.miwgroningen.ch11.vincent.educationmanager.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EducationalActivityTest {

    @Test
    @DisplayName("Top Level Depth")
    void topLevelActivityDepth() {
        // Arrange
        EducationalActivity educationalActivity = new EducationalActivity();
        int expectedDepth = 0;

        // Activate
        int depth = educationalActivity.getDepth();

        // Assert
        assertEquals(expectedDepth, depth);
    }

    @Test
    @DisplayName("Middle level depth")
    void middleLevelDepth() {
        EducationalActivity educationalActivity = new EducationalActivity();
        EducationalActivity educationalActivity1 = new EducationalActivity();
        EducationalActivity educationalActivity2 = new EducationalActivity();

        educationalActivity2.setSuperActivity(educationalActivity1);
        educationalActivity1.getSubActivities().add(educationalActivity2);
        educationalActivity1.setSuperActivity(educationalActivity);
        educationalActivity.getSubActivities().add(educationalActivity1);

        int expectedDepth = 1;

        int depth = educationalActivity1.getDepth();

        assertEquals(expectedDepth, depth);
    }

    @Test
    @DisplayName("Bottom level depth")
    void bottomLevelDepth() {
        EducationalActivity educationalActivity = new EducationalActivity();
        EducationalActivity educationalActivity1 = new EducationalActivity();
        EducationalActivity educationalActivity2 = new EducationalActivity();

        educationalActivity2.setSuperActivity(educationalActivity1);
        educationalActivity1.getSubActivities().add(educationalActivity2);
        educationalActivity1.setSuperActivity(educationalActivity);
        educationalActivity.getSubActivities().add(educationalActivity1);

        int expectedDepth = 2;

        int depth = educationalActivity2.getDepth();

        assertEquals(expectedDepth, depth);
    }
}