package main.java;

import com.sun.tools.javac.Main;
import javafx.application.Platform;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.*;

import static main.java.HelperFuncs.*;
import static org.junit.jupiter.api.Assertions.*;

@Nested
class HelperFuncsTest {
    private TextField userInput;
    private TextField passInput;

    @BeforeEach
    void setUp() {
        if (!Platform.isFxApplicationThread()) {
            Platform.startup(() -> {});
        }
        Main main = new Main();
        userInput = new TextField();
        passInput = new TextField();
        userInput.setText("test");
        passInput.setText("test");
        assertEquals("test", userInput.getText(), "User field populated.");
        assertEquals("test", passInput.getText(), "Password field populated.");
    }

    @AfterEach
    void tearDown() {
        userInput = null;
        passInput = null;
    }

    @Test
    void clearShouldResetFields() {
        clear(userInput, passInput);
        assertEquals("", userInput.getText(), "User field cleared.");
        assertEquals("", passInput.getText(), "Password field cleared.");
    }


    @Test
    void invalidCredentialsShouldFail() {
        boolean success = login(userInput,passInput);
        assertFalse(success, "Login failed with invalid credentials.");
    }

    @Test
    void validCredentialsShouldSucceed() {
        userInput.setText("user");
        passInput.setText("pass");
        boolean success = login(userInput,passInput);
        assertTrue(success, "Login succeeded with valid credentials.");
    }

    @Test
    void logShouldUpdate() throws IOException {
        File logFile = new File("Logs.txt");
        if (logFile.exists()) {
            logFile.delete();

            Main main = new Main();
            login(userInput, passInput);

            assertTrue(logFile.exists(), "Log file created.");

            try (BufferedReader br = new BufferedReader
                (new FileReader("Logs.txt"))) {
                assertNotNull(br.readLine(), "Log file has contents.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
