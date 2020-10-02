package com.example.mad_project;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private SignUp signup;

    @Test
    public void testEmailValidity() {
        String testEmail = "anupamchugh@gmail.com";
        Assert.assertThat(String.format("Email Address Validity Test failed for %s ", testEmail), SignUp.checkEmailForValidity(testEmail), is(true));
    }

    @Test
    public void testEmailValidity1() {
        String testEmail = "anupamchughgmailcom";
        Assert.assertThat(String.format("Email Address Validity Test failed for %s ", testEmail), SignUp.checkEmailForValidity(testEmail), is(false));
    }

}