package evo.model;

public class UserFinger {
    private Integer id;

    private Integer userId;

    private String finger1Url;

    private String finger2Url;

    private String finger3Url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFinger1Url() {
        return finger1Url;
    }

    public void setFinger1Url(String finger1Url) {
        this.finger1Url = finger1Url == null ? null : finger1Url.trim();
    }

    public String getFinger2Url() {
        return finger2Url;
    }

    public void setFinger2Url(String finger2Url) {
        this.finger2Url = finger2Url == null ? null : finger2Url.trim();
    }

    public String getFinger3Url() {
        return finger3Url;
    }

    public void setFinger3Url(String finger3Url) {
        this.finger3Url = finger3Url == null ? null : finger3Url.trim();
    }
}