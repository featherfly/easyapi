package cn.featherfly.easyapi.domain;

import java.time.LocalDateTime;

import cn.featherfly.common.model.app.Platform;

/**
 * The type User.
 *
 * @author zhongj
 */
public class User {

    private Long id;

    private String nickname;

    private String mobile;

    private Gender gender;

    private LocalDateTime birthday;

    private String headImg;

    private Integer smokingAge;

    private Integer earning;

    private Boolean available;

    private Platform[] platforms;
    private Platform platform;

    public enum Gender {
        MALE,
        FEMALE
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public Integer getSmokingAge() {
        return smokingAge;
    }

    public void setSmokingAge(Integer smokingAge) {
        this.smokingAge = smokingAge;
    }

    public Integer getEarning() {
        return earning;
    }

    public void setEarning(Integer earning) {
        this.earning = earning;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Platform[] getPlatforms() {
        return platforms;
    }

    public void setPlatforms(Platform[] platforms) {
        this.platforms = platforms;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }
}
