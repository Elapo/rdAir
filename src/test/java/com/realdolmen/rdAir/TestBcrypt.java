package com.realdolmen.rdAir;

import org.mindrot.jbcrypt.BCrypt;

public class TestBcrypt {
    public static void main(String[] args) {
        String hash = BCrypt.hashpw("abc", BCrypt.gensalt());
        System.out.println(hash);
        System.out.println(BCrypt.checkpw("abc", hash));
    }
}
