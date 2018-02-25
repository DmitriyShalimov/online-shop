package onlineshop.util;

import onlineshop.entity.Product;

import java.util.List;

public class ListToJson {
    public String convert(List<Product> list) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (Product product : list) {
            stringBuilder.append("{product: {").append(System.lineSeparator());
            stringBuilder.append("id: ").append(product.getId()).append(",").append(System.lineSeparator());
            stringBuilder.append("title: '").append(product.getTitle()).append("',").append(System.lineSeparator());
            stringBuilder.append("price: ").append(product.getPrice()).append(",").append(System.lineSeparator());
            stringBuilder.append("description: '").append(product.getDescription()).append("'").append(System.lineSeparator());
            stringBuilder.append("}},").append(System.lineSeparator());
        }
        stringBuilder.append("];");
        return stringBuilder.toString();
    }
}
