package com.javashitang;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lilimin
 * @since 2021-09-14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionMsgProducerTests {


    @Test
    public void method1() {
        try {
            method2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void method2() {
        int a = 10 / 0;
    }
}
