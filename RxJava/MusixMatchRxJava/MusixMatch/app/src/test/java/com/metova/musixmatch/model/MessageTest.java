package com.metova.musixmatch.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jodi on 8/11/17.
 */
public class MessageTest {

    private Message mFakeTestMessage;
    private Body mFakeBody;

    @Before
    public void setup() {
        mFakeTestMessage = new Message();
        mFakeBody = new Body();
    }

    @After
    public void tearDown() {
        mFakeTestMessage = null;
        mFakeBody = null;
    }


    @Test
    public void testGetBody() throws Exception {

        mFakeTestMessage.setBody(mFakeBody);

        assertEquals(mFakeBody, mFakeTestMessage.getBody());
    }

}