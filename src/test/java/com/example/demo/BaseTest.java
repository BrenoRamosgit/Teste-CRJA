package com.example.demo;

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;
import static org.mockito.MockitoAnnotations.openMocks;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    @BeforeAll
    public static void setUp() {
        loadTemplates("com.example.demo.fixture");
    }

    @BeforeEach
    public void before() {
        openMocks(this);
    }
}
