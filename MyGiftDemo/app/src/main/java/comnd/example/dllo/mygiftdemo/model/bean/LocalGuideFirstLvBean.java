package comnd.example.dllo.mygiftdemo.model.bean;

/**
 * Created by dllo on 16/7/13.
 * 指南页面 下面滑动的listview的 数据类
 */
public class LocalGuideFirstLvBean {

    private String imageCenter;
    private String title;
    private String likeCount;
    private String short_msg;

    public LocalGuideFirstLvBean(String imageCenter, String title, String likeCount, String short_msg) {
        this.imageCenter = imageCenter;
        this.title = title;
        this.likeCount = likeCount;
        this.short_msg = short_msg;
    }

    public String getImageCenter() {
        return imageCenter;
    }

    public void setImageCenter(String imageCenter) {
        this.imageCenter = imageCenter;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public String getShort_msg() {
        return short_msg;
    }

    public void setShort_msg(String short_msg) {
        this.short_msg = short_msg;
    }
}
