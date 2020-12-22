package cn.featherfly.easyapi.client;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 机器信息
 */
public class DeviceDto {

    private String code = null;

    private String sn = null;

    private Double longitude = null;

    private Double latitude = null;

    public enum StatusEnum {
        NORMALITY, FAULT,
    }

    private StatusEnum status = null;

    public enum PlatformEnum {
        ANDROID, IOS, WINDOWS_PHONE,
    }

    private PlatformEnum platform = null;

    private String platformVersion = null;

    /**
     * get 设备类型编码
     *
     * @return 设备类型编码
     **/
    @JsonProperty("code")

    public String getCode() {
        return code;
    }

    /**
     * set 设备类型编码
     *
     * @param code 设备类型编码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * set 设备类型编码
     *
     * @param code 设备类型编码
     * @return DeviceDto
     */
    public DeviceDto code(String code) {
        setCode(code);
        return this;
    }

    /**
     * get 设备序列号
     *
     * @return 设备序列号
     **/
    @JsonProperty("sn")

    public String getSn() {
        return sn;
    }

    /**
     * set 设备序列号
     *
     * @param sn 设备序列号
     */
    public void setSn(String sn) {
        this.sn = sn;
    }

    /**
     * set 设备序列号
     *
     * @param sn 设备序列号
     * @return DeviceDto
     */
    public DeviceDto sn(String sn) {
        setSn(sn);
        return this;
    }

    /**
     * get 经度
     *
     * @return 经度
     **/
    @JsonProperty("longitude")

    public Double getLongitude() {
        return longitude;
    }

    /**
     * set 经度
     *
     * @param longitude 经度
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * set 经度
     *
     * @param longitude 经度
     * @return DeviceDto
     */
    public DeviceDto longitude(Double longitude) {
        setLongitude(longitude);
        return this;
    }

    /**
     * get 纬度
     *
     * @return 纬度
     **/
    @JsonProperty("latitude")

    public Double getLatitude() {
        return latitude;
    }

    /**
     * set 纬度
     *
     * @param latitude 纬度
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * set 纬度
     *
     * @param latitude 纬度
     * @return DeviceDto
     */
    public DeviceDto latitude(Double latitude) {
        setLatitude(latitude);
        return this;
    }

    /**
     * get 状态
     *
     * @return 状态
     **/
    @JsonProperty("status")

    public StatusEnum getStatus() {
        return status;
    }

    /**
     * set 状态
     *
     * @param status 状态
     */
    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    /**
     * set 状态
     *
     * @param status 状态
     * @return DeviceDto
     */
    public DeviceDto status(StatusEnum status) {
        setStatus(status);
        return this;
    }

    /**
     * get 平台
     *
     * @return 平台
     **/
    @JsonProperty("platform")

    public PlatformEnum getPlatform() {
        return platform;
    }

    /**
     * set 平台
     *
     * @param platform 平台
     */
    public void setPlatform(PlatformEnum platform) {
        this.platform = platform;
    }

    /**
     * set 平台
     *
     * @param platform 平台
     * @return DeviceDto
     */
    public DeviceDto platform(PlatformEnum platform) {
        setPlatform(platform);
        return this;
    }

    /**
     * get 平台版本
     *
     * @return 平台版本
     **/
    @JsonProperty("platformVersion")

    public String getPlatformVersion() {
        return platformVersion;
    }

    /**
     * set 平台版本
     *
     * @param platformVersion 平台版本
     */
    public void setPlatformVersion(String platformVersion) {
        this.platformVersion = platformVersion;
    }

    /**
     * set 平台版本
     *
     * @param platformVersion 平台版本
     * @return DeviceDto
     */
    public DeviceDto platformVersion(String platformVersion) {
        setPlatformVersion(platformVersion);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DeviceDto {\n");

        sb.append("  code: ").append(code).append("\n");
        sb.append("  sn: ").append(sn).append("\n");
        sb.append("  longitude: ").append(longitude).append("\n");
        sb.append("  latitude: ").append(latitude).append("\n");
        sb.append("  status: ").append(status).append("\n");
        sb.append("  platform: ").append(platform).append("\n");
        sb.append("  platformVersion: ").append(platformVersion).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}