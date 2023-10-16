package com.miraie.webapp.mocking.mockitodemo.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BusinessImplMockTest {
    //What to mock
    @Mock
    DataService dataServiceMock;

    // Where to inject the above mock
    @InjectMocks
    private BusinessImpl impl;


    /*
        Commented lines indicate implementation without the annotations above

     */
    @Test
    void findGreatestNumber_basicScenarioTest () {

//        DataService dataServiceMock = mock(DataService.class);
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{1, 2, 3, 4});
//        BusinessImpl impl = new BusinessImpl(dataServiceMock);
        int greatestNumber = impl.findGreatestNumber();
        assertEquals(4, greatestNumber);
    }

    @Test
    void findGreatestNumber_oneElementTest () {

//        DataService dataServiceMock = mock(DataService.class);
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{4});
//        BusinessImpl impl = new BusinessImpl(dataServiceMock);
        int greatestNumber = impl.findGreatestNumber();
        assertEquals(4, greatestNumber);
    }

    @Test
    void findGreatestNumber_EmptyTest () {

//        DataService dataServiceMock = mock(DataService.class);
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[]{});
//        BusinessImpl impl = new BusinessImpl(dataServiceMock);
        Integer greatestNumber = impl.findGreatestNumber();
        assertNull(greatestNumber);
    }
}
