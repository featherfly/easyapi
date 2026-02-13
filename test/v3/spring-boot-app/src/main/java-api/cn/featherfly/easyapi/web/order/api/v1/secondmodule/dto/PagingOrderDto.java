package cn.featherfly.easyapi.web.order.api.v1.secondmodule.dto;

import java.util.Objects;
import cn.featherfly.easyapi.web.order.api.v1.secondmodule.dto.OrderDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * PagingOrderDto
 */
@Validated

@jakarta.annotation.Generated(value = "cn.featherfly.easyapi.codegen.v3.spring.EasyapiSpringMvcCodegen", date = "2026-02-06T16:12:03.352420200+08:00[Asia/Shanghai]")


public class PagingOrderDto   {
  @JsonProperty("total")
  private Integer total = null;

  @JsonProperty("pageNumber")
  private Integer pageNumber = null;

  @JsonProperty("content")
  @Valid
  private List<OrderDto> content = null;
  public PagingOrderDto total(Integer total) { 


    this.total = total;
    return this;
  }

  /**
   * Get total
   * @return total
   **/
  
  @Schema(description = "")
   
  public Integer getTotal() {  
    return total;
  }
  public void setTotal(Integer total){ 


    this.total = total;
  }
  public PagingOrderDto pageNumber(Integer pageNumber) { 


    this.pageNumber = pageNumber;
    return this;
  }

  /**
   * Get pageNumber
   * @return pageNumber
   **/
  
  @Schema(description = "")
   
  public Integer getPageNumber() {  
    return pageNumber;
  }
  public void setPageNumber(Integer pageNumber){ 


    this.pageNumber = pageNumber;
  }
  public PagingOrderDto content(List<OrderDto> content) { 


    this.content = content;
    return this;
  }

  public PagingOrderDto addContentItem(OrderDto contentItem) {
    if (this.content == null) {
      this.content = new ArrayList<>();
    }
    this.content.add(contentItem);
    return this;
  }

  /**
   * Get content
   * @return content
   **/
  
  @Schema(description = "")
   
@Valid
  public List<OrderDto> getContent() {  
    return content;
  }
  public void setContent(List<OrderDto> content){ 


    this.content = content;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PagingOrderDto pagingOrderDto = (PagingOrderDto) o;
    return Objects.equals(this.total, pagingOrderDto.total) &&
        Objects.equals(this.pageNumber, pagingOrderDto.pageNumber) &&
        Objects.equals(this.content, pagingOrderDto.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(total, pageNumber, content);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PagingOrderDto {\n");
    
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    pageNumber: ").append(toIndentedString(pageNumber)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
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
