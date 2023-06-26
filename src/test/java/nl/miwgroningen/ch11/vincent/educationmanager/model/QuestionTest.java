package nl.miwgroningen.ch11.vincent.educationmanager.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * Purpose of the program
 */
class QuestionTest {

    @ParameterizedTest
    @DisplayName("Setting points possible for a question should result in an error")
    @ValueSource(ints = {-1, -2, -8, -44})
    void settingPointsPossibleForAQuestionShouldResultInAnError(int illegalPoints) {
        Question question = new Question();

        assertThrows(IllegalArgumentException.class, () -> question.setPointsPossible(illegalPoints));
    }

    @ParameterizedTest
    @DisplayName("Setting allowed points should not result in an error")
    @ValueSource(ints = {0, 1, 2, 3, 44})
    void settingAllowedPointsShouldNotResultInAnError(int legalPoints) {
        Question question = new Question();

        assertDoesNotThrow(() -> question.setPointsPossible(legalPoints));
    }
}