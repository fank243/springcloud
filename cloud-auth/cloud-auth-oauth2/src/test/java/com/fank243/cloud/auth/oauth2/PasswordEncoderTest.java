package com.fank243.cloud.auth.oauth2;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {

    @Test
    public void bCryptPassword() {
        String encode = new BCryptPasswordEncoder().encode(">$vLli~R|-[BuKzwgE8Q");
        String encode2 = new BCryptPasswordEncoder().encode("123456");
        System.out.println(encode);
        System.out.println(encode2);
    }
}
