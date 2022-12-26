package heyoom.first.security;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class SeedTest {
 
    @Test
    public void �Ϻ�ȣȭ_�׽�Ʈ() {
        // given
        String rawMessage = "���Ѻ�";
        String testMessage = "����";
        System.out.println("���� ������ =>" + rawMessage);
        System.out.println("���� ������ =>" + testMessage);
 
        // when
        String encryptedMessage = Seed.encrypt(rawMessage);
        String encryptedMessage2 = Seed.encrypt(testMessage);
        System.out.println("��ȣȭ�� ������ => " + encryptedMessage);
        System.out.println("��ȣȭ�� ������ => " + encryptedMessage2);
 
        String decryptedMessage = Seed.decrypt(encryptedMessage);
        System.out.println("��ȣȭ�� ������ => " + new String(decryptedMessage));
 
        // then
        assertThat(rawMessage).isEqualTo(decryptedMessage);
        assertThat(rawMessage).isNotEqualTo(encryptedMessage);
    }

}
