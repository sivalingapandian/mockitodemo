package com.mycompany.mock.dal;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by pandian on 7/23/15.
 */
public class HelloWorldMockTest {

    @Test
    public void iteratorShouldReturnHelloWorld() {

        Iterator iterator = mock(Iterator.class);
        when(iterator.next()).thenReturn("Hello").thenReturn("World");
        Assert.assertEquals("Hello World",iterator.next() + " " + iterator.next());

    }



}
