package nttdata.grupouno.com.operations.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    private Util util;

    @Test
    void dateToString() {
        String fecha = "27/09/2022";
        Date today = new Date();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String resp = formatter.format(new Date());
        String h = Util.dateToString(today);
        assertEquals(resp,fecha);
    }

    @Test
    void testDateToString() {
    }

    @Test
    void getMonth() {
        String month = "09";
        String resp = util.getMonth(new Date());
        assertEquals(resp,month);
    }

    @Test
    void getYear() {
        String month = "2022";
        String resp = util.getYear(new Date());
        assertEquals(resp,month);
    }

    @Test
    void comparetest(){
        Integer a= 10;
        Integer b= 9;
        System.out.println(a.compareTo(b));
    }

    @Test
    void stringToDate() throws ParseException {
        String sDate1="2022.09.25";
        String sDate2="2022.09.26";
        Date convertedDate;
        Date convertedDate2;
        Date today = new Date();
        SimpleDateFormat formatter1=new SimpleDateFormat("yyyy.MM.dd");
        convertedDate =(Date) formatter1.parse(sDate1);
        convertedDate2 =(Date) formatter1.parse(sDate2);
        today.compareTo(today);
        System.out.println(convertedDate);
        System.out.println("<<>>>>>" + convertedDate.compareTo(convertedDate2));
        Integer a= 10;
        Integer b= 9;
        System.out.println(a.compareTo(b));
    }
}