package id.ac.binus.kikimeatshop.model;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.Timestamp;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Product {

    private String id;
    private String name;
    private String description;
    private Double stock;
    private Double price;
    private String image;

    private Date lastUpdated;


    public Product(String id, String name, String description, Double stock,
                   Double price, String image, Date lastUpdated) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.price = price;
        this.image = image;
        this.lastUpdated = lastUpdated;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Map<String, Object> toJson() {
        Map<String, Object> json = new HashMap<>();

        json.put("id", id);
        json.put("name", name);
        json.put("description", description);
        json.put("stock", stock);
        json.put("price", price);
        json.put("image", image);

        // Firestore automatically converts Date to Timestamp on save
        json.put("lastUpdated", lastUpdated != null ? lastUpdated : new Date());

        return json;
    }


    public static Product fromSnapshot(DocumentSnapshot doc) {
        if (doc == null || !doc.exists()) return null;

        Product p = new Product();

        p.id = doc.getString("id");
        p.name = doc.getString("name");
        p.description = doc.getString("description");
        p.stock = doc.getDouble("stock");
        p.price = doc.getDouble("price");
        p.image = doc.getString("image");

        Timestamp ts = doc.getTimestamp("lastUpdated");
        p.lastUpdated = ts != null ? ts.toDate() : null;

        return p;
    }
}
