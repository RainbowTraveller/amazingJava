package com.miraie.webapp.mocking.mockitodemo.business;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Demo for stub testing
 *
 * Stub - override methods to return hard-coded values, also referred to as state-based
 *
 * OR
 *
 * provide canned answers to calls made during the test, usually not responding at all to anything outside what's programmed in for the test.
 *
 * 1. Stub is actual impl of an interface
 * 2. For different behaviour of same interface we need different stubs
 * 3. If interface changes we need to remodify all the stubs
 *
 * This is a maintenance mess.
 */
class BusinessImplStubTest {

    @Test
    void TestStub1() {
        BusinessImpl impl = new BusinessImpl(new DataServiceImplStub1());
        int greatestNumber = impl.findGreatestNumber();
        assertEquals(9, greatestNumber);

    }
    @Test
    void TestStub2() {
        BusinessImpl impl = new BusinessImpl(new DataServiceImplStub2());
        int greatestNumber = impl.findGreatestNumber();
        assertEquals(4, greatestNumber);

    }

    class DataServiceImplStub1 implements DataService {
        @Override
        public int[] retrieveAllData () {
            return new int[] {2, 5, 6, 3,7,9};
        }
    }
    class DataServiceImplStub2 implements DataService {
        @Override
        public int[] retrieveAllData () {
            return new int[] {4};
        }
    }
}
