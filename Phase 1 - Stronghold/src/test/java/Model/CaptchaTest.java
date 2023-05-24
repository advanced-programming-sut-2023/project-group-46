package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaptchaTest {

    @Test
    void CaptchaIsGenerated() throws Exception {
        String output = Captcha.generateCaptcha();
        assertTrue(output.contains("@"));
    }

}