package onlineshop.util;

import onlineshop.entity.Product;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ListToJsonTest {
    @Test
    public void testParseRequest() {
       ListToJson listToJson=new ListToJson();
        Product product=new Product();
        product.setId(1);
        product.setDescription("description");
        product.setPrice(2);
        product.setTitle("title");
        List<Product> list=new ArrayList<>();
        list.add(product);
       String result =listToJson.convert(list);
       String expectedResult="[{product: {\r\n" +
               "id: 1,\r\n" +
               "title: 'title',\r\n" +
               "price: 2.0,\r\n" +
               "description: 'description'\r\n" +
               "}},\r\n" +
               "];";
        assertEquals(result,expectedResult);
    }
}
