package com.rnrmedia.rest;

import java.util.Calendar;

import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Type;
import java.lang.annotation.Annotation;
import com.rnrmedia.rest.MockDate;

@Provider
public class MockDateConverterProvider implements ParamConverterProvider {

    @Override
    public <T> ParamConverter<T> getConverter(final Class<T> rawType, Type genericType, Annotation[] annotations) {
        if(rawType.getName().equals(MockDate.class.getName())) {
            return new ParamConverter<T>() {
                @Override
                public T fromString(String value) {
                    Calendar requestedDate = Calendar.getInstance();
                    if(value.equalsIgnoreCase("tomorrow")) {
                        requestedDate.add(Calendar.DATE, 1);
                    } else if(value.equalsIgnoreCase("yesterday")) {
                        requestedDate.add(Calendar.DATE, -1);
                    }
                    MockDate result = new MockDate();
                    result.setDay(requestedDate.get(Calendar.DATE));
                    result.setMonth(requestedDate.get(Calendar.MONTH));
                    result.setYear(requestedDate.get(Calendar.YEAR));
                    return rawType.cast(result);
                }

                @Override
                public String toString(T mybean) {
                    if(mybean == null) {
                        return null;
                    }
                    return mybean.toString();
                }
            };
        }
        return null;
    }
}

