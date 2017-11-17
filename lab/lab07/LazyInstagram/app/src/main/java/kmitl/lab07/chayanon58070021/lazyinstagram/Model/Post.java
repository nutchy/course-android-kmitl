package kmitl.lab07.chayanon58070021.lazyinstagram.Model;

/**
 * Created by nutchy on 10/13/2017 AD.
 */

public class Post {
    private int like;
    private int comment;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }
}
