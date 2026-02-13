package cn.featherfly.easyapi.web.order.api.v1.secondmodule.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets OrderStatus
 */
public enum OrderStatus {
  NOTSPEND("NOTSPEND"),
    PAYMENTING("PAYMENTING"),
    PAYED("PAYED"),
    DELIVERING("DELIVERING"),
    DELIVERED("DELIVERED"),
    DELIVERING_EXCEPTION("DELIVERING_EXCEPTION"),
    SUCCESS("SUCCESS"),
    REFUNDING("REFUNDING"),
    REFUND("REFUND"),
    CANCEL("CANCEL"),
    TIMEOUT("TIMEOUT"),
    EXCEPTION("EXCEPTION"),
    REBATES("REBATES");

  private String value;

  OrderStatus(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static OrderStatus fromValue(String text) {
    for (OrderStatus b : OrderStatus.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
