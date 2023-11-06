package com.example;

import org.junit.jupiter.api.Test;

import com.example.QueueTestTask5.QueueTest;


class AppTest {
    QueueTest qtest = new QueueTest();

    @Test
    public void testAll(){
        qtest.testString();
        qtest.testDouble();
        qtest.testInt();
    }
}
