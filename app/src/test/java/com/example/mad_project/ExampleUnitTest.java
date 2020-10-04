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
    private BOOKPayment payment;
    private Addparkingpayment parkingpayment;

    @Before
    public void setUp() {
        parkingpayment= new Addparkingpayment();

    }

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
//test cases by Kalhan
    @Test
    public  void setBOOkPayment_check(){
        float result = payment.bookpaymentCal(5);
        assertEquals(500,result,0.001);

    }

    @Test
    public  void setBOOkPayment_check1(){
        float result = payment.bookpaymentCal(4);
        assertEquals(400,result,0.001);

    }
        //checking add parking calculation.by sanduni
    @Test
    public void addparkingpay() {
        float result = parkingpayment.getamount(8);
        assertEquals(800,result,0.001);

    }

    @Test
    public void addparkingpay1() {
        float result = parkingpayment.getamount(2);
        assertEquals(100,result,0.001);
    }
//test cases by gamitha
    @Test
    public void addparkingpay2() {
        float result = parkingpayment.getamount(1);
        assertEquals(50,result,0.001);
    }

    @Test
    public void checkBookpaymnet() {
        float result = payment.bookpaymentCal(6);
        assertEquals(600,result,0.001);
    }
}