package com.metova.musixmatch.api;

import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

/**
 * Created by jodi on 8/14/17.
 */
public class ClientTest {


    private Client client;

    @Before
    public void setUp() throws Exception {
        client = new Client();
    }

    @After
    public void tearDown() throws Exception {
        client = null;
    }

    @Test
    public void getClient() throws Exception {
        assertNotNull(client);
    }

}