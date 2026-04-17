package cn.featherfly.easyapi.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The type TradeOrder.
 *
 * @author zhongj
 */
public class TradeOrder {

    private Long id;

    private String orderNo;

    private String title;

    private List<String> tags;

    private PaymentPlatform paymentPlatform;

    private PaymentPlatform[] supportPaymentPlatforms;

    private List<PaymentPlatform> supportPaymentPlatformList;

    private List<TradeOrderGoods> tradeOrderGoods = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PaymentPlatform getPaymentPlatform() {
        return paymentPlatform;
    }

    public void setPaymentPlatform(PaymentPlatform paymentPlatform) {
        this.paymentPlatform = paymentPlatform;
    }

    public PaymentPlatform[] getSupportPaymentPlatforms() {
        return supportPaymentPlatforms;
    }

    public void setSupportPaymentPlatforms(PaymentPlatform[] supportPaymentPlatforms) {
        this.supportPaymentPlatforms = supportPaymentPlatforms;
    }

    public List<PaymentPlatform> getSupportPaymentPlatformList() {
        return supportPaymentPlatformList;
    }

    public void setSupportPaymentPlatformList(List<PaymentPlatform> supportPaymentPlatformList) {
        this.supportPaymentPlatformList = supportPaymentPlatformList;
    }

    public List<TradeOrderGoods> getTradeOrderGoods() {
        return tradeOrderGoods;
    }

    public void setTradeOrderGoods(List<TradeOrderGoods> tradeOrderGoods) {
        this.tradeOrderGoods = tradeOrderGoods;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
