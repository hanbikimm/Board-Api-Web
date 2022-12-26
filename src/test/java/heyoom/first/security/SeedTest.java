package heyoom.first.security;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class SeedTest {
 
    @Test
    public void �Ϻ�ȣȭ_�׽�Ʈ() {
        // given
        String rawMessage = "5678";
        System.out.println("���� ������ =>" + rawMessage);
 
        // when
        String encryptedMessage = Seed.encrypt(rawMessage);
        System.out.println("��ȣȭ�� ������ => " + encryptedMessage);
 
        String decryptedMessage = Seed.decrypt(encryptedMessage);
        System.out.println("��ȣȭ�� ������ => " + new String(decryptedMessage));
 
        // then
        assertThat(rawMessage).isEqualTo(decryptedMessage);
        assertThat(rawMessage).isNotEqualTo(encryptedMessage);
    }

}
