package com.webapp.redsocial;

import org.junit.runner.RunWith;

import cucumber.junit.Cucumber;

/**
 * 
 * @author Usuario
 *
 */
@RunWith(Cucumber.class)
@Cucumber.Options(format= {"pretty", "html:target/cucumber"})
public class RunTest {
}