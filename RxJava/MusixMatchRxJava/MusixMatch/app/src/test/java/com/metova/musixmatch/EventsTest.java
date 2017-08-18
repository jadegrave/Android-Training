package com.metova.musixmatch;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;


/**
 * Created by jodi on 8/14/17.
 */

public class EventsTest {

    private Events mFakeEvent;


    @Before
    public void setup() {
        mFakeEvent = new Events(6);
    }

    @After
    public void tearDown() {
        mFakeEvent = null;
    }
    @Test
    public void testGetPosition() throws Exception {
        assertEquals(6, mFakeEvent.getPosition());
    }

}
