package comnd.example.dllo.mygiftdemo.model.bean;

/**
 * Created by dllo on 16/7/14.
 * 指南页面第二个fragment复用这个 数据类
 */
public class FuyongBean {

    // 两级解析 items 这一层
    private String contentTitle; //第1期 | 欧洲折扣场：搞不懂欧盟没关系，我们只负责买买买"
    private String coverImage; // 中央图片
    private String likeCount; // 喜欢的个数
    // items - author这一层
    private String touxiang; //
    private String nickname; // 用户名
    // items - column这一层
    private String category; // 礼物
    private String listviewTitle; // 省钱大总攻

    public FuyongBean(String contentTitle, String coverImage,
                      String likeCount, String touxiang, String nickname, String category, String listviewTitle) {
        this.contentTitle = contentTitle;
        this.coverImage = coverImage;
        this.likeCount = likeCount;
        this.touxiang = touxiang;
        this.nickname = nickname;
        this.category = category;
        this.listviewTitle = listviewTitle;
    }


    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public String getTouxiang() {
        return touxiang;
    }

    public void setTouxiang(String touxiang) {
        this.touxiang = touxiang;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getListviewTitle() {
        return listviewTitle;
    }

    public void setListviewTitle(String listviewTitle) {
        this.listviewTitle = listviewTitle;
    }
}
