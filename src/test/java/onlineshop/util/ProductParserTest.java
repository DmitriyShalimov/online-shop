package onlineshop.util;


import onlineshop.entity.Product;
import org.junit.Test;
import org.mockito.Mockito;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class ProductParserTest {


    @Test
    public void testGetProducts() throws SQLException {
        ResultSet resultSet=Mockito.mock(ResultSet.class);
        Mockito.when(resultSet.getInt("id")).thenReturn(1);
        Mockito.when(resultSet.getString("title")).thenReturn("title");
        Mockito.when(resultSet.getDouble("price")).thenReturn((double) 2);
        Mockito.when(resultSet.getString("description")).thenReturn("description");
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Product product=new Product();
        product.setId(1);
        product.setDescription("description");
        product.setPrice(2);
        product.setTitle("title");
        ProductParser productParser=new ProductParser();
        Product result=productParser.getProducts(resultSet).get(0);
        assertEquals(result.getId(),product.getId());
        assertEquals(result.getTitle(),product.getTitle());
        assertEquals(result.getDescription(),product.getDescription());
    }
}
