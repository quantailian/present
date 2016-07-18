package comnd.example.dllo.mygiftdemo.model.bean;

/**
 * Created by dllo on 16/7/14.
 * 热门的bean
 */
public class LocalhotBean {

    String name,likesCount,imageUrl,price;

    public LocalhotBean(String name, String likesCount, String imageUrl, String price) {
        this.name = name;
        this.likesCount = likesCount;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public LocalhotBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(String likesCount) {
        this.likesCount = likesCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
