package com.monthu.recode.domain.feed.application;

@FunctionalInterface
public interface ChangeCountConsumer <T> {

    void changeCount(T t);

}
