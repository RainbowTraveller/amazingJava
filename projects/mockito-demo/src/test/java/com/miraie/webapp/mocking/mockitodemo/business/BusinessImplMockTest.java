package com.miraie.webapp.mocking.mockitodemo.business;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BusinessImplMockTest {

    @Test
    void findGreatestNumber_basicScenarioTest() {

        DataService dataServiceMock = mock(DataService.class);
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {1,2,3,4});
        BusinessImpl impl = new BusinessImpl(dataServiceMock);
        int greatestNumber = impl.findGreatestNumber();
        assertEquals(4,greatestNumber);
    }
    @Test
    void findGreatestNumber_oneElementTest() {

        DataService dataServiceMock = mock(DataService.class);
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {4});
        BusinessImpl impl = new BusinessImpl(dataServiceMock);
        int greatestNumber = impl.findGreatestNumber();
        assertEquals(4,greatestNumber);
    }
}
