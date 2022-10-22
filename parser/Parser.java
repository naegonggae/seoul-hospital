package com.lion.domain.parser;

public interface Parser<T> {
    T parse(String str);
}
