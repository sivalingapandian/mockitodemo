package com.mycompany.mock.dal;


import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * Created by pandian on 7/23/15.
 */
public class MockArgumentTest {

    @Test
    public void shouldReturnOneWhenTestPassed() {
        Comparable comparable = mock(Comparable.class);
        when(comparable.compareTo("Test")).thenReturn(1);
        Assert.assertEquals(1, comparable.compareTo("Test"));
    }

    @Test
    public void shouldReturnNegativeOneForAnyArgument() {
        Comparable comparable = mock(Comparable.class);
        when(comparable.compareTo(anyInt())).thenReturn(-1);
        Assert.assertEquals(-1, comparable.compareTo(20));
    }

    @Test (expected = IOException.class)
    public void shouldThrowExceptionWhenCloseCalled() throws IOException {
        OutputStream mockOutputStream = mock(OutputStream.class);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(mockOutputStream);
        doThrow(new IOException()).when(mockOutputStream).close();
        outputStreamWriter.close();
    }

    @Test
    public void outputStreamWriterClosesOutputStreamClose() throws IOException {
        OutputStream mockOutputStream = mock(OutputStream.class);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(mockOutputStream);
        outputStreamWriter.close();
        verify(mockOutputStream).close();
    }

    @Test
    public void outputStreamWriterBuffersAndForwardsToOutputStream() throws IOException {
        OutputStream mockedOutputStream = mock(OutputStream.class);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(mockedOutputStream);
        outputStreamWriter.write('a');
        outputStreamWriter.flush();

        BaseMatcher arrayStartingWithA = new BaseMatcher() {
            public boolean matches(Object o) {
                byte[] actual = (byte[]) o;
                return actual[0] == 'a';
            }

            public void describeTo(Description description) {

            }
        };

        verify(mockedOutputStream).write((byte[]) argThat(arrayStartingWithA),eq(0),eq(1));

    }

}
