package model;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: asus
 * Date: 2020-07-15
 * Time: 18:40
 */
public class Article {
    private int articleId;
    private  String  title;
    private String  content;
    private  int userId;

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articeId) {
        this.articleId = articeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", title='" +title +'\''+
                ", content='" + content+'\'' +
                ", userId=" +userId +
                '}';
    }
}
