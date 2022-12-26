package heyoom.first.security;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class SeedTest {
 
    @Test
    public void 암복호화_테스트() {
        // given
        String rawMessage = "5678";
        System.out.println("원본 데이터 =>" + rawMessage);
 
        // when
        String encryptedMessage = Seed.encrypt(rawMessage);
        System.out.println("암호화된 데이터 => " + encryptedMessage);
 
        String decryptedMessage = Seed.decrypt(encryptedMessage);
        System.out.println("복호화된 데이터 => " + new String(decryptedMessage));
 
        // then
        assertThat(rawMessage).isEqualTo(decryptedMessage);
        assertThat(rawMessage).isNotEqualTo(encryptedMessage);
    }

}
