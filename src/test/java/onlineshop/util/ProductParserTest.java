package onlineshop.util;

import com.shalimov.onlineshop.entity.Product;
import com.shalimov.onlineshop.dao.parser.ProductParser;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class ProductParserTest {

    @Test
//    before
    public void testGetProducts() throws SQLException {
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.when(resultSet.getInt("id")).thenReturn(1);
        Mockito.when(resultSet.getString("title")).thenReturn("title");
        Mockito.when(resultSet.getDouble("price")).thenReturn((double) 2);
        Mockito.when(resultSet.getString("description")).thenReturn("description");
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Product expectedProduct = new Product();
        expectedProduct.setId(1);
        expectedProduct.setDescription("description");
        expectedProduct.setPrice(2);
        expectedProduct.setTitle("title");

        //when
        ProductParser productParser = new ProductParser();

        //then
        assertEquals(productParser.getProducts(resultSet).size(), 1);
        Product actualResult = productParser.getProducts(resultSet).get(0);
        assertEquals(expectedProduct.getId(), actualResult.getId());
        assertEquals(expectedProduct.getTitle(), actualResult.getTitle());
        assertEquals(expectedProduct.getDescription(), actualResult.getDescription());
    }
}
