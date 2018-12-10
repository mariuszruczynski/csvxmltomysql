package model;

import csvxmltomysql.model.CheckContactType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckContactTypeTest {

    @Test
    void testIsContactAPhoneNumber() {

        String number1 = "+252-565-525";
        String number2 = "565656565";
        String number3 = "A565656565";
        String number4 = "+605 605 605";
        String number5 = "605-605-605";
        String number6 = "605 605 60";
        String number7 = "605 605 6066";

        assertTrue(CheckContactType.isTelNumber(number1));
        assertTrue(CheckContactType.isTelNumber(number2));
        assertTrue(!CheckContactType.isTelNumber(number3));
        assertTrue(CheckContactType.isTelNumber(number4));
        assertTrue(CheckContactType.isTelNumber(number5));
        assertTrue(!CheckContactType.isTelNumber(number6));
        assertTrue(!CheckContactType.isTelNumber(number7));
    }

    @Test
    void testIsContactAJabber() {

        String testContact1 = "jbr:sometext";
        String testContact2 = "jbrsometext";
        String testContact3 = "jbr:2565455";
        String testContact4 = "sometext2584jbr:";
        String testContact5 = "JBR:somet@text.pl";

        assertTrue(CheckContactType.isJabber(testContact1));
        assertTrue(!CheckContactType.isJabber(testContact2));
        assertTrue(CheckContactType.isJabber(testContact3));
        assertTrue(!CheckContactType.isJabber(testContact4));
        assertTrue(CheckContactType.isJabber(testContact5));
    }

    @Test
    void testIsContactAEmail() {
        String testContact1 = "some@sometext";
        String testContact2 = "@sometext";
        String testContact3 = "sometext@some.text";
        String testContact4 = "@2584552";
        String testContact5 = "some@sometext";
        String testContact6 = "some.sometext@";

        assertTrue(CheckContactType.isEmail(testContact1));
        assertTrue(!CheckContactType.isEmail(testContact2));
        assertTrue(CheckContactType.isEmail(testContact3));
        assertTrue(!CheckContactType.isEmail(testContact4));
        assertTrue(CheckContactType.isEmail(testContact5));
        assertTrue(!CheckContactType.isEmail(testContact6));
    }
}
