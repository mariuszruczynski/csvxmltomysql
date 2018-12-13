package parseformat;

import csvxmltomysql.model.Contact;
import csvxmltomysql.model.Customer;
import csvxmltomysql.parseformat.XmlToSql;
import csvxmltomysql.service.SqlService;
import csvxmltomysql.service.SqlServiceTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class XmlToSqlTest {

    @Test
    void shouldReadXmlFileAndSaveToDataBase() {

        Customer customer;
        Contact contact1;
        Contact contact2;
        String patch = "src/test/resources/test.xml";

        XmlToSql.readAndSaveXML(patch);
        int testCunsomerId = SqlService.findMaxCustomersId();
        int testCunsomerContactsId = SqlService.findMaxContactsId();

        customer = SqlServiceTest.findCustomerById(testCunsomerId);
        contact1 = SqlServiceTest.findContactById(testCunsomerContactsId - 1);
        contact2 = SqlServiceTest.findContactById(testCunsomerContactsId);

        assertEquals(customer.getName(), "Jan");
        assertEquals(customer.getSurname(), "Kowalski");
        assertEquals(customer.getAge(), "25");
        assertEquals(customer.getCity(), "Lublin");
        assertEquals(contact1.getCustomerId(), customer.getId());
        assertEquals(contact1.getType(), 2);
        assertEquals(contact1.getContact(), "605605605");
        assertEquals(contact2.getCustomerId(), customer.getId());
        assertEquals(contact2.getType(), 1);
        assertEquals(contact2.getContact(), "kowalski@gmail.com");

        SqlServiceTest.delCustomerById(testCunsomerId);
        SqlServiceTest.delContactById(testCunsomerContactsId - 1);
        SqlServiceTest.delContactById(testCunsomerContactsId);
    }
}
