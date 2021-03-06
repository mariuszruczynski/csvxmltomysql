package parseformat;

import csvxmltomysql.model.Contact;
import csvxmltomysql.model.Customer;
import csvxmltomysql.parseformat.CsvToSql;
import csvxmltomysql.service.SqlService;
import csvxmltomysql.service.SqlServiceTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CsvToSqlTest {

    private SqlService sqlService = new SqlService();
    private CsvToSql csvToSql = new CsvToSql();


    @Test
    void shouldReadCsvFileAndSaveToDataBase() {

        Customer customer;
        Contact contact1;
        Contact contact2;
        String patch = "src/test/resources/test.csv";

        csvToSql.readAndSaveCsvFile(patch);
        int testCunsomerId = sqlService.findMaxCustomersId();
        int testCunsomerContactsId = sqlService.findMaxContactsId();

        customer = SqlServiceTest.findCustomerById(testCunsomerId);
        contact1 = SqlServiceTest.findContactById(testCunsomerContactsId - 1);
        contact2 = SqlServiceTest.findContactById(testCunsomerContactsId);

        assertEquals(customer.getName(), "Zenon");
        assertEquals(customer.getSurname(), "Nowak");
        assertEquals(customer.getAge(), "21");
        assertEquals(customer.getCity(), "Lublin");
        assertEquals(contact1.getCustomerId(), customer.getId());
        assertEquals(contact1.getType(), 2);
        assertEquals(contact1.getContact(), "605-605-605");
        assertEquals(contact2.getCustomerId(), customer.getId());
        assertEquals(contact2.getType(), 1);
        assertEquals(contact2.getContact(), "Zenon@zenon.pl");

        SqlServiceTest.delCustomerById(testCunsomerId);
        SqlServiceTest.delContactById(testCunsomerContactsId - 1);
        SqlServiceTest.delContactById(testCunsomerContactsId);
    }
}
