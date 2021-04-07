package com.yadavmo.demo;

import org.junit.Test;

import java.util.List;

public class DemoTest {
    @Test
    public void fun(){
        List<Integer> a = List.of();
        assert a.equals(List.of(null));
    }
}
