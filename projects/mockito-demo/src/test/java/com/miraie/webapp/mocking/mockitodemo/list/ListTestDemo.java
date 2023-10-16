package com.miraie.webapp.mocking.mockitodemo.list;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

public class ListTestDemo {

    @Test
    public void testSimpleSize() {
        List listMock = mock(List.class);
        when(listMock.size()).thenReturn(3);
        assertEquals(3,listMock.size());
    }
    @Test
    public void testMultipleReturns() {
        List listMock = mock(List.class);
        when(listMock.size()).thenReturn(3).thenReturn(2);
        assertEquals(3,listMock.size());
        assertEquals(2,listMock.size());
        assertEquals(2,listMock.size());
        assertEquals(2,listMock.size());
        assertEquals(2,listMock.size());
    }
    @Test
    public void testParameters() {
        List listMock = mock(List.class);
        when(listMock.get(0)).thenReturn("StringContent");
        assertEquals("StringContent",listMock.get(0));
        assertEquals(null,listMock.get(1));
    }
    @Test
    public void testGenericParameters() {
        List listMock = mock(List.class);
        when(listMock.get(Mockito.anyInt())).thenReturn("StringContent");
        assertEquals("StringContent",listMock.get(0));
        assertEquals("StringContent",listMock.get(1));
        assertEquals("StringContent",listMock.get(4));
    }
}
