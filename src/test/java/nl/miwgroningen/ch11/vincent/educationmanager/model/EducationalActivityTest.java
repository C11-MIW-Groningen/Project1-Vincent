package nl.miwgroningen.ch11.vincent.educationmanager.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EducationalActivityTest {

    EducationalActivity educationalActivity;
    EducationalActivity educationalActivity1;
    EducationalActivity educationalActivity2;

    @BeforeEach
    void setupEducationActivities() {
        educationalActivity = new EducationalActivity();
        educationalActivity1 = new EducationalActivity();
        educationalActivity2 = new EducationalActivity();

        educationalActivity2.setSuperActivity(educationalActivity1);
        educationalActivity1.getSubActivities().add(educationalActivity2);
        educationalActivity1.setSuperActivity(educationalActivity);
        educationalActivity.getSubActivities().add(educationalActivity1);
    }

    @Test
    @DisplayName("Top Level Depth")
    void topLevelActivityDepth() {
        // Arrange
        int expectedDepth = 0;

        // Activate
        int depth = educationalActivity.getDepth();

        // Assert
        assertEquals(expectedDepth, depth);
    }

    @Test
    @DisplayName("Middle level depth")
    void middleLevelDepth() {
        int expectedDepth = 1;

        int depth = educationalActivity1.getDepth();

        assertEquals(expectedDepth, depth);
    }

    @Test
    @DisplayName("Bottom level depth")
    void bottomLevelDepth() {
        int expectedDepth = 2;

        int depth = educationalActivity2.getDepth();

        assertEquals(expectedDepth, depth);
    }
}