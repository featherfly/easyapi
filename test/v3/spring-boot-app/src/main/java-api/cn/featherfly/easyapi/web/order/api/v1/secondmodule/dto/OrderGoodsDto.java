package cn.featherfly.easyapi.web.order.api.v1.secondmodule.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * OrderGoodsDto
 */
@Validated

@jakarta.annotation.Generated(value = "cn.featherfly.easyapi.codegen.v3.spring.EasyapiSpringMvcCodegen", date = "2026-02-06T16:12:03.352420200+08:00[Asia/Shanghai]")


public class OrderGoodsDto   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("goodsCount")
  private Long goodsCount = null;

  @JsonProperty("goodsPrice")
  private Float goodsPrice = null;

  @JsonProperty("goodsName")
  private String goodsName = null;

  @JsonProperty("goodsFlavor")
  private String goodsFlavor = null;

  @JsonProperty("goodsSpec")
  private String goodsSpec = null;

  @JsonProperty("amount")
  private Float amount = null;

  @JsonProperty("payment")
  private Float payment = null;

  public OrderGoodsDto id(Long id) { 


    this.id = id;
    return this;
  }

  /**
   * 主键
   * @return id
   **/
  
  @Schema(description = "主键")
   
  public Long getId() {  
    return id;
  }
  public void setId(Long id){ 


    this.id = id;
  }
  public OrderGoodsDto goodsCount(Long goodsCount) { 


    this.goodsCount = goodsCount;
    return this;
  }

  /**
   * 数量
   * @return goodsCount
   **/
  
  @Schema(description = "数量")
   
  public Long getGoodsCount() {  
    return goodsCount;
  }
  public void setGoodsCount(Long goodsCount){ 


    this.goodsCount = goodsCount;
  }
  public OrderGoodsDto goodsPrice(Float goodsPrice) { 


    this.goodsPrice = goodsPrice;
    return this;
  }

  /**
   * 金额
   * @return goodsPrice
   **/
  
  @Schema(description = "金额")
   
  public Float getGoodsPrice() {  
    return goodsPrice;
  }
  public void setGoodsPrice(Float goodsPrice){ 


    this.goodsPrice = goodsPrice;
  }
  public OrderGoodsDto goodsName(String goodsName) { 


    this.goodsName = goodsName;
    return this;
  }

  /**
   * 名称
   * @return goodsName
   **/
  
  @Schema(description = "名称")
   
  public String getGoodsName() {  
    return goodsName;
  }
  public void setGoodsName(String goodsName){ 


    this.goodsName = goodsName;
  }
  public OrderGoodsDto goodsFlavor(String goodsFlavor) { 


    this.goodsFlavor = goodsFlavor;
    return this;
  }

  /**
   * 味道
   * @return goodsFlavor
   **/
  
  @Schema(description = "味道")
   
  public String getGoodsFlavor() {  
    return goodsFlavor;
  }
  public void setGoodsFlavor(String goodsFlavor){ 


    this.goodsFlavor = goodsFlavor;
  }
  public OrderGoodsDto goodsSpec(String goodsSpec) { 


    this.goodsSpec = goodsSpec;
    return this;
  }

  /**
   * 规格
   * @return goodsSpec
   **/
  
  @Schema(description = "规格")
   
  public String getGoodsSpec() {  
    return goodsSpec;
  }
  public void setGoodsSpec(String goodsSpec){ 


    this.goodsSpec = goodsSpec;
  }
  public OrderGoodsDto amount(Float amount) { 


    this.amount = amount;
    return this;
  }

  /**
   * 订单金额
   * @return amount
   **/
  
  @Schema(description = "订单金额")
   
  public Float getAmount() {  
    return amount;
  }
  public void setAmount(Float amount){ 


    this.amount = amount;
  }
  public OrderGoodsDto payment(Float payment) { 


    this.payment = payment;
    return this;
  }

  /**
   * 实际支付金额
   * @return payment
   **/
  
  @Schema(description = "实际支付金额")
   
  public Float getPayment() {  
    return payment;
  }
  public void setPayment(Float payment){ 


    this.payment = payment;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderGoodsDto orderGoodsDto = (OrderGoodsDto) o;
    return Objects.equals(this.id, orderGoodsDto.id) &&
        Objects.equals(this.goodsCount, orderGoodsDto.goodsCount) &&
        Objects.equals(this.goodsPrice, orderGoodsDto.goodsPrice) &&
        Objects.equals(this.goodsName, orderGoodsDto.goodsName) &&
        Objects.equals(this.goodsFlavor, orderGoodsDto.goodsFlavor) &&
        Objects.equals(this.goodsSpec, orderGoodsDto.goodsSpec) &&
        Objects.equals(this.amount, orderGoodsDto.amount) &&
        Objects.equals(this.payment, orderGoodsDto.payment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, goodsCount, goodsPrice, goodsName, goodsFlavor, goodsSpec, amount, payment);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderGoodsDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    goodsCount: ").append(toIndentedString(goodsCount)).append("\n");
    sb.append("    goodsPrice: ").append(toIndentedString(goodsPrice)).append("\n");
    sb.append("    goodsName: ").append(toIndentedString(goodsName)).append("\n");
    sb.append("    goodsFlavor: ").append(toIndentedString(goodsFlavor)).append("\n");
    sb.append("    goodsSpec: ").append(toIndentedString(goodsSpec)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    payment: ").append(toIndentedString(payment)).append("\n");
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
