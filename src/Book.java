
public class Book {

    private String name;
    private String id;
    private String author;
    private String price;
    private String publishTeam;

    public Book() {
        this.name = null;
        this.id = null;
        this.author=null;
        this.price = null;
        this.publishTeam = null;
    }

    public Book(String name, String id, String author, String price, String publishTeam) {
        this.name = name;
        this.id = id;
        this.author = author;
        this.price = price;
        this.publishTeam = publishTeam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id= id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;

    }
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPublishTeam(){
        return publishTeam;
    }

    public void setPublishTeam(){
        this.publishTeam = publishTeam;
    }

    @Override
    public String toString() {
        return "Publish [name=" + name + ", id=" + id + ", author=" + author
                + ", price=" + price + ", publishTeam=" + publishTeam + "]";
    }


}