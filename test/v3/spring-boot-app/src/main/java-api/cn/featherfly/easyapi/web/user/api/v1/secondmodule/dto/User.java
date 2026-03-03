package cn.featherfly.easyapi.web.user.api.v1.secondmodule.dto;

import java.util.Objects;
import cn.featherfly.easyapi.web.user.api.v1.secondmodule.dto.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * 用户信息
 */
@Schema(description = "用户信息")
@Validated

@jakarta.annotation.Generated(value = "cn.featherfly.easyapi.codegen.v3.spring.EasyapiSpringMvcCodegen", date = "2026-03-03T15:45:34.387696200+08:00[Asia/Shanghai]")


public class User   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("mobile")
  private String mobile = null;

  @JsonProperty("nickname")
  private String nickname = null;

  @JsonProperty("gender")
  private Gender gender = null;

  @JsonProperty("birthday")
  private LocalDateTime birthday = null;

  @JsonProperty("headImg")
  private String headImg = null;

  @JsonProperty("smokingAge")
  private Integer smokingAge = null;

  @JsonProperty("earning")
  private Integer earning = null;

  public User id(Long id) { 


    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  
  @Schema(description = "")
   
  public Long getId() {  
    return id;
  }
  public void setId(Long id){ 


    this.id = id;
  }
  public User mobile(String mobile) { 


    this.mobile = mobile;
    return this;
  }

  /**
   * 手机号码
   * @return mobile
   **/
  
  @Schema(required = true, description = "手机号码")
  @NotNull 
  public String getMobile() {  
    return mobile;
  }
  public void setMobile(String mobile){ 


    this.mobile = mobile;
  }
  public User nickname(String nickname) { 


    this.nickname = nickname;
    return this;
  }

  /**
   * 昵称
   * @return nickname
   **/
  
  @Schema(required = true, description = "昵称")
  @NotNull 
  public String getNickname() {  
    return nickname;
  }
  public void setNickname(String nickname){ 


    this.nickname = nickname;
  }
  public User gender(Gender gender) { 


    this.gender = gender;
    return this;
  }

  /**
   * Get gender
   * @return gender
   **/
  
  @Schema(description = "")
   
@Valid
  public Gender getGender() {  
    return gender;
  }
  public void setGender(Gender gender){ 


    this.gender = gender;
  }
  public User birthday(LocalDateTime birthday) { 


    this.birthday = birthday;
    return this;
  }

  /**
   * Get birthday
   * @return birthday
   **/
  
  @Schema(description = "")
   
@Valid
  public LocalDateTime getBirthday() {  
    return birthday;
  }
  public void setBirthday(LocalDateTime birthday){ 


    this.birthday = birthday;
  }
  public User headImg(String headImg) { 


    this.headImg = headImg;
    return this;
  }

  /**
   * 头像
   * @return headImg
   **/
  
  @Schema(description = "头像")
   
  public String getHeadImg() {  
    return headImg;
  }
  public void setHeadImg(String headImg){ 


    this.headImg = headImg;
  }
  public User smokingAge(Integer smokingAge) { 


    this.smokingAge = smokingAge;
    return this;
  }

  /**
   * Get smokingAge
   * @return smokingAge
   **/
  
  @Schema(description = "")
   
  public Integer getSmokingAge() {  
    return smokingAge;
  }
  public void setSmokingAge(Integer smokingAge){ 


    this.smokingAge = smokingAge;
  }
  public User earning(Integer earning) { 


    this.earning = earning;
    return this;
  }

  /**
   * Get earning
   * @return earning
   **/
  
  @Schema(description = "")
   
  public Integer getEarning() {  
    return earning;
  }
  public void setEarning(Integer earning){ 


    this.earning = earning;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(this.id, user.id) &&
        Objects.equals(this.mobile, user.mobile) &&
        Objects.equals(this.nickname, user.nickname) &&
        Objects.equals(this.gender, user.gender) &&
        Objects.equals(this.birthday, user.birthday) &&
        Objects.equals(this.headImg, user.headImg) &&
        Objects.equals(this.smokingAge, user.smokingAge) &&
        Objects.equals(this.earning, user.earning);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, mobile, nickname, gender, birthday, headImg, smokingAge, earning);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    mobile: ").append(toIndentedString(mobile)).append("\n");
    sb.append("    nickname: ").append(toIndentedString(nickname)).append("\n");
    sb.append("    gender: ").append(toIndentedString(gender)).append("\n");
    sb.append("    birthday: ").append(toIndentedString(birthday)).append("\n");
    sb.append("    headImg: ").append(toIndentedString(headImg)).append("\n");
    sb.append("    smokingAge: ").append(toIndentedString(smokingAge)).append("\n");
    sb.append("    earning: ").append(toIndentedString(earning)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
