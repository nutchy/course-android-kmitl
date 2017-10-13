package kmitl.lab07.chayanon58070021.mylazyinstagram;

/**
 * Created by nutchy on 10/13/2017 AD.
 */

public class Layout {
    public static final int TYPE_USER_DETAIL = 0;
    public static final int TYPE_POST_ITEM = 1;

    private int type;

    public Layout(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
