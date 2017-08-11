package com.metova.musixmatch.model;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by jodi on 8/11/17.
 */
public class MessageTest {
    @Test
    public void getBody() throws Exception {
        Message fakeTestMesage = new Message();
        Body fakeBody = new Body();

        fakeTestMesage.setBody(fakeBody);

        assertEquals(fakeBody, fakeTestMesage.getBody());
    }

}