package com.paopao.model.modelView.RequestModel;

public class CrewRequest {
    private String useType;
    private String mail;
    private String phoneNum;
    private String oldPassword;
    private String newPassword;

    private String crewName;
    private String avatarImgCode;


    private int curUserId;
    private int associatedUserId;
    private int pagerSize;
    private int pagerIndex;

    /**
     * 密码重设类型
     */
    public static class UseType{
        public static String USE_PHONE = "usePhone";
        public static String USE_MAIL = "useMail";
    }

    public String getUseType() {
        return useType;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getAvatarImgCode() {
        return avatarImgCode;
    }

    public void setAvatarImgCode(String avatarImgCode) {
        this.avatarImgCode = avatarImgCode;
    }

    public String getCrewName() {
        return crewName;
    }

    public void setCrewName(String crewName) {
        this.crewName = crewName;
    }

    public int getPagerSize() {
        return pagerSize;
    }

    public void setPagerSize(int pagerSize) {
        this.pagerSize = pagerSize;
    }

    public int getPagerIndex() {
        return pagerIndex;
    }

    public void setPagerIndex(int pagerIndex) {
        this.pagerIndex = pagerIndex;
    }

    public int getAssociatedUserId() {
        return associatedUserId;
    }

    public void setAssociatedUserId(int associatedUserId) {
        this.associatedUserId = associatedUserId;
    }

    public int getCurUserId() {
        return curUserId;
    }

    public void setCurUserId(int curUserId) {
        this.curUserId = curUserId;
    }
}
