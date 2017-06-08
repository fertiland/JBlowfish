package com.qipingli;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple JBlowfish.
 */
public class JBlowfishTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public JBlowfishTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( JBlowfishTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testJBlowfish()
    {
        JBlowfish jb = new JBlowfish();
        jb.setKey("secret");
        jb.setIv("12345678");
        
        try {
            String encodeRes = jb.encode("helloworld", "Base64");
            System.out.println( "encode result:"+ encodeRes);
            String decodeRes = jb.decode(encodeRes, "Base64");
            System.out.println( "decode result:"+ decodeRes);   
            assertTrue("helloworld".equals(decodeRes));
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assertTrue( true );
    }
}
