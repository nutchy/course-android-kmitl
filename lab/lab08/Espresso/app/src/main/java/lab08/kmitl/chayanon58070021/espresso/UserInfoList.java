package lab08.kmitl.chayanon58070021.espresso;


import java.util.List;

public class UserInfoList {
    public List<UserInfo> getUserInfoList() {
        return userInfoList;
    }

    public void setUserInfoList(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
    }

    private List<UserInfo> userInfoList;

    public void clearList() {
        this.userInfoList.clear();
    }
}