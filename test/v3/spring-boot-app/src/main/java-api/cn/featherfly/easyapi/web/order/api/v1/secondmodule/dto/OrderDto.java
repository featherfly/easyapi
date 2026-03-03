package cn.featherfly.easyapi.web.order.api.v1.secondmodule.dto;

import java.util.Objects;
import cn.featherfly.easyapi.web.order.api.v1.secondmodule.dto.OrderGoodsDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * OrderDto
 */
@Validated

@jakarta.annotation.Generated(value = "cn.featherfly.easyapi.codegen.v3.spring.EasyapiSpringMvcCodegen", date = "2026-03-03T15:45:34.792214800+08:00[Asia/Shanghai]")


public class OrderDto   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("orderNo")
  private String orderNo = null;

  @JsonProperty("status")
  private Integer status = null;

  @JsonProperty("processGoodsStatus")
  private Boolean processGoodsStatus = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("descp")
  private String descp = null;

  @JsonProperty("userId")
  private Long userId = null;

  @JsonProperty("organizationId")
  private Long organizationId = null;

  @JsonProperty("shelfId")
  private Long shelfId = null;

  @JsonProperty("amount")
  private Float amount = null;

  @JsonProperty("payment")
  private Float payment = null;

  @JsonProperty("paymentPlatform")
  private Integer paymentPlatform = null;

  @JsonProperty("createTime")
  private String createTime = null;

  @JsonProperty("latestPaymentTime")
  private String latestPaymentTime = null;

  @JsonProperty("finishTime")
  private String finishTime = null;

  @JsonProperty("expireTime")
  private String expireTime = null;

  @JsonProperty("orderType")
  private Integer orderType = null;

  @JsonProperty("wxAppId")
  private String wxAppId = null;

  @JsonProperty("wxTransactionId")
  private String wxTransactionId = null;

  @JsonProperty("wxRefundId")
  private String wxRefundId = null;

  @JsonProperty("wxPrepayId")
  private String wxPrepayId = null;

  @JsonProperty("wxPrepayIdExpireTime")
  private String wxPrepayIdExpireTime = null;

  @JsonProperty("code")
  private String code = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("goods")
  @Valid
  private List<OrderGoodsDto> goods = null;
  public OrderDto id(Long id) { 


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
  public OrderDto orderNo(String orderNo) { 


    this.orderNo = orderNo;
    return this;
  }

  /**
   * 订单号
   * @return orderNo
   **/
  
  @Schema(description = "订单号")
   
  public String getOrderNo() {  
    return orderNo;
  }
  public void setOrderNo(String orderNo){ 


    this.orderNo = orderNo;
  }
  public OrderDto status(Integer status) { 


    this.status = status;
    return this;
  }

  /**
   * 状态
   * @return status
   **/
  
  @Schema(description = "状态")
   
  public Integer getStatus() {  
    return status;
  }
  public void setStatus(Integer status){ 


    this.status = status;
  }
  public OrderDto processGoodsStatus(Boolean processGoodsStatus) { 


    this.processGoodsStatus = processGoodsStatus;
    return this;
  }

  /**
   * 商品处理状态
   * @return processGoodsStatus
   **/
  
  @Schema(description = "商品处理状态")
   
  public Boolean isProcessGoodsStatus() {  
    return processGoodsStatus;
  }
  public void setProcessGoodsStatus(Boolean processGoodsStatus){ 


    this.processGoodsStatus = processGoodsStatus;
  }
  public OrderDto title(String title) { 


    this.title = title;
    return this;
  }

  /**
   * 标题
   * @return title
   **/
  
  @Schema(description = "标题")
   
  public String getTitle() {  
    return title;
  }
  public void setTitle(String title){ 


    this.title = title;
  }
  public OrderDto descp(String descp) { 


    this.descp = descp;
    return this;
  }

  /**
   * 描述
   * @return descp
   **/
  
  @Schema(description = "描述")
   
  public String getDescp() {  
    return descp;
  }
  public void setDescp(String descp){ 


    this.descp = descp;
  }
  public OrderDto userId(Long userId) { 


    this.userId = userId;
    return this;
  }

  /**
   * 用户ID
   * @return userId
   **/
  
  @Schema(description = "用户ID")
   
  public Long getUserId() {  
    return userId;
  }
  public void setUserId(Long userId){ 


    this.userId = userId;
  }
  public OrderDto organizationId(Long organizationId) { 


    this.organizationId = organizationId;
    return this;
  }

  /**
   * 公司ID
   * @return organizationId
   **/
  
  @Schema(description = "公司ID")
   
  public Long getOrganizationId() {  
    return organizationId;
  }
  public void setOrganizationId(Long organizationId){ 


    this.organizationId = organizationId;
  }
  public OrderDto shelfId(Long shelfId) { 


    this.shelfId = shelfId;
    return this;
  }

  /**
   * 货柜ID
   * @return shelfId
   **/
  
  @Schema(description = "货柜ID")
   
  public Long getShelfId() {  
    return shelfId;
  }
  public void setShelfId(Long shelfId){ 


    this.shelfId = shelfId;
  }
  public OrderDto amount(Float amount) { 


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
  public OrderDto payment(Float payment) { 


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
  public OrderDto paymentPlatform(Integer paymentPlatform) { 


    this.paymentPlatform = paymentPlatform;
    return this;
  }

  /**
   * 支付类型
   * @return paymentPlatform
   **/
  
  @Schema(description = "支付类型")
   
  public Integer getPaymentPlatform() {  
    return paymentPlatform;
  }
  public void setPaymentPlatform(Integer paymentPlatform){ 


    this.paymentPlatform = paymentPlatform;
  }
  public OrderDto createTime(String createTime) { 


    this.createTime = createTime;
    return this;
  }

  /**
   * 创建时间
   * @return createTime
   **/
  
  @Schema(description = "创建时间")
   
  public String getCreateTime() {  
    return createTime;
  }
  public void setCreateTime(String createTime){ 


    this.createTime = createTime;
  }
  public OrderDto latestPaymentTime(String latestPaymentTime) { 


    this.latestPaymentTime = latestPaymentTime;
    return this;
  }

  /**
   * 支付时间
   * @return latestPaymentTime
   **/
  
  @Schema(description = "支付时间")
   
  public String getLatestPaymentTime() {  
    return latestPaymentTime;
  }
  public void setLatestPaymentTime(String latestPaymentTime){ 


    this.latestPaymentTime = latestPaymentTime;
  }
  public OrderDto finishTime(String finishTime) { 


    this.finishTime = finishTime;
    return this;
  }

  /**
   * 订单完成时间
   * @return finishTime
   **/
  
  @Schema(description = "订单完成时间")
   
  public String getFinishTime() {  
    return finishTime;
  }
  public void setFinishTime(String finishTime){ 


    this.finishTime = finishTime;
  }
  public OrderDto expireTime(String expireTime) { 


    this.expireTime = expireTime;
    return this;
  }

  /**
   * 到期时间
   * @return expireTime
   **/
  
  @Schema(description = "到期时间")
   
  public String getExpireTime() {  
    return expireTime;
  }
  public void setExpireTime(String expireTime){ 


    this.expireTime = expireTime;
  }
  public OrderDto orderType(Integer orderType) { 


    this.orderType = orderType;
    return this;
  }

  /**
   * 订单类型
   * @return orderType
   **/
  
  @Schema(description = "订单类型")
   
  public Integer getOrderType() {  
    return orderType;
  }
  public void setOrderType(Integer orderType){ 


    this.orderType = orderType;
  }
  public OrderDto wxAppId(String wxAppId) { 


    this.wxAppId = wxAppId;
    return this;
  }

  /**
   * 微信ID
   * @return wxAppId
   **/
  
  @Schema(description = "微信ID")
   
  public String getWxAppId() {  
    return wxAppId;
  }
  public void setWxAppId(String wxAppId){ 


    this.wxAppId = wxAppId;
  }
  public OrderDto wxTransactionId(String wxTransactionId) { 


    this.wxTransactionId = wxTransactionId;
    return this;
  }

  /**
   * Get wxTransactionId
   * @return wxTransactionId
   **/
  
  @Schema(description = "")
   
  public String getWxTransactionId() {  
    return wxTransactionId;
  }
  public void setWxTransactionId(String wxTransactionId){ 


    this.wxTransactionId = wxTransactionId;
  }
  public OrderDto wxRefundId(String wxRefundId) { 


    this.wxRefundId = wxRefundId;
    return this;
  }

  /**
   * Get wxRefundId
   * @return wxRefundId
   **/
  
  @Schema(description = "")
   
  public String getWxRefundId() {  
    return wxRefundId;
  }
  public void setWxRefundId(String wxRefundId){ 


    this.wxRefundId = wxRefundId;
  }
  public OrderDto wxPrepayId(String wxPrepayId) { 


    this.wxPrepayId = wxPrepayId;
    return this;
  }

  /**
   * Get wxPrepayId
   * @return wxPrepayId
   **/
  
  @Schema(description = "")
   
  public String getWxPrepayId() {  
    return wxPrepayId;
  }
  public void setWxPrepayId(String wxPrepayId){ 


    this.wxPrepayId = wxPrepayId;
  }
  public OrderDto wxPrepayIdExpireTime(String wxPrepayIdExpireTime) { 


    this.wxPrepayIdExpireTime = wxPrepayIdExpireTime;
    return this;
  }

  /**
   * Get wxPrepayIdExpireTime
   * @return wxPrepayIdExpireTime
   **/
  
  @Schema(description = "")
   
  public String getWxPrepayIdExpireTime() {  
    return wxPrepayIdExpireTime;
  }
  public void setWxPrepayIdExpireTime(String wxPrepayIdExpireTime){ 


    this.wxPrepayIdExpireTime = wxPrepayIdExpireTime;
  }
  public OrderDto code(String code) { 


    this.code = code;
    return this;
  }

  /**
   * Get code
   * @return code
   **/
  
  @Schema(description = "")
   
  public String getCode() {  
    return code;
  }
  public void setCode(String code){ 


    this.code = code;
  }
  public OrderDto name(String name) { 


    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   **/
  
  @Schema(description = "")
   
  public String getName() {  
    return name;
  }
  public void setName(String name){ 


    this.name = name;
  }
  public OrderDto goods(List<OrderGoodsDto> goods) { 


    this.goods = goods;
    return this;
  }

  public OrderDto addGoodsItem(OrderGoodsDto goodsItem) {
    if (this.goods == null) {
      this.goods = new ArrayList<>();
    }
    this.goods.add(goodsItem);
    return this;
  }

  /**
   * Get goods
   * @return goods
   **/
  
  @Schema(description = "")
   
@Valid
  public List<OrderGoodsDto> getGoods() {  
    return goods;
  }
  public void setGoods(List<OrderGoodsDto> goods){ 


    this.goods = goods;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderDto orderDto = (OrderDto) o;
    return Objects.equals(this.id, orderDto.id) &&
        Objects.equals(this.orderNo, orderDto.orderNo) &&
        Objects.equals(this.status, orderDto.status) &&
        Objects.equals(this.processGoodsStatus, orderDto.processGoodsStatus) &&
        Objects.equals(this.title, orderDto.title) &&
        Objects.equals(this.descp, orderDto.descp) &&
        Objects.equals(this.userId, orderDto.userId) &&
        Objects.equals(this.organizationId, orderDto.organizationId) &&
        Objects.equals(this.shelfId, orderDto.shelfId) &&
        Objects.equals(this.amount, orderDto.amount) &&
        Objects.equals(this.payment, orderDto.payment) &&
        Objects.equals(this.paymentPlatform, orderDto.paymentPlatform) &&
        Objects.equals(this.createTime, orderDto.createTime) &&
        Objects.equals(this.latestPaymentTime, orderDto.latestPaymentTime) &&
        Objects.equals(this.finishTime, orderDto.finishTime) &&
        Objects.equals(this.expireTime, orderDto.expireTime) &&
        Objects.equals(this.orderType, orderDto.orderType) &&
        Objects.equals(this.wxAppId, orderDto.wxAppId) &&
        Objects.equals(this.wxTransactionId, orderDto.wxTransactionId) &&
        Objects.equals(this.wxRefundId, orderDto.wxRefundId) &&
        Objects.equals(this.wxPrepayId, orderDto.wxPrepayId) &&
        Objects.equals(this.wxPrepayIdExpireTime, orderDto.wxPrepayIdExpireTime) &&
        Objects.equals(this.code, orderDto.code) &&
        Objects.equals(this.name, orderDto.name) &&
        Objects.equals(this.goods, orderDto.goods);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, orderNo, status, processGoodsStatus, title, descp, userId, organizationId, shelfId, amount, payment, paymentPlatform, createTime, latestPaymentTime, finishTime, expireTime, orderType, wxAppId, wxTransactionId, wxRefundId, wxPrepayId, wxPrepayIdExpireTime, code, name, goods);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    orderNo: ").append(toIndentedString(orderNo)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    processGoodsStatus: ").append(toIndentedString(processGoodsStatus)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    descp: ").append(toIndentedString(descp)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    organizationId: ").append(toIndentedString(organizationId)).append("\n");
    sb.append("    shelfId: ").append(toIndentedString(shelfId)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    payment: ").append(toIndentedString(payment)).append("\n");
    sb.append("    paymentPlatform: ").append(toIndentedString(paymentPlatform)).append("\n");
    sb.append("    createTime: ").append(toIndentedString(createTime)).append("\n");
    sb.append("    latestPaymentTime: ").append(toIndentedString(latestPaymentTime)).append("\n");
    sb.append("    finishTime: ").append(toIndentedString(finishTime)).append("\n");
    sb.append("    expireTime: ").append(toIndentedString(expireTime)).append("\n");
    sb.append("    orderType: ").append(toIndentedString(orderType)).append("\n");
    sb.append("    wxAppId: ").append(toIndentedString(wxAppId)).append("\n");
    sb.append("    wxTransactionId: ").append(toIndentedString(wxTransactionId)).append("\n");
    sb.append("    wxRefundId: ").append(toIndentedString(wxRefundId)).append("\n");
    sb.append("    wxPrepayId: ").append(toIndentedString(wxPrepayId)).append("\n");
    sb.append("    wxPrepayIdExpireTime: ").append(toIndentedString(wxPrepayIdExpireTime)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    goods: ").append(toIndentedString(goods)).append("\n");
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
