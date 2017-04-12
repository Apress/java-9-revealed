// ArrayLengthObjectFilter.java
package com.jdojo.misc;

import java.io.ObjectInputFilter;

public class ArrayLengthObjectFilter implements ObjectInputFilter {
    private long maxLenth = -1;

    public ArrayLengthObjectFilter(int maxLength) {
        this.maxLenth = maxLength;
    }

    @Override
    public Status checkInput(FilterInfo info) {
        long arrayLength = info.arrayLength();
        if (arrayLength >= 0 && arrayLength > this.maxLenth) {
            return Status.REJECTED;
        }

        return Status.ALLOWED;
    }
}
