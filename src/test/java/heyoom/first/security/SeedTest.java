package heyoom.first.security;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class SeedTest {
 
    @Test
    public void 암호화테스트() {
        // given
        String rawMessage = "김한비";
        String testMessage = "한비";
        System.out.println("원본 메세지 =>" + rawMessage);
        System.out.println("원본 메세지 =>" + testMessage);
 
        // when
        String encryptedMessage = Seed.encrypt(rawMessage);
        String encryptedMessage2 = Seed.encrypt(testMessage);
        System.out.println("암호화된 메세지 => " + encryptedMessage);
        System.out.println("암호화된 메세지 => " + encryptedMessage2);
 
        String decryptedMessage = Seed.decrypt(encryptedMessage);
        System.out.println("복호화된 메세지 => " + new String(decryptedMessage));
 
        // then
        assertThat(rawMessage).isEqualTo(decryptedMessage);
        assertThat(rawMessage).isNotEqualTo(encryptedMessage);
    }

}
