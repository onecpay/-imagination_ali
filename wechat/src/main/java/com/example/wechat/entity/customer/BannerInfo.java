package com.example.wechat.entity.customer;

import com.common.entity.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_wechat_banner")
public class BannerInfo extends BaseEntity implements Serializable {
    /**
     * id
     */
    @Id
    private Long id;

    /**
     * 版本控制
     */
    private Integer version;

    /**
     * banner 图描述
     */
    @Column(name = "banner_name")
    private String bannerName;

    /**
     * 链接url地址
     */
    @Column(name = "banner_url")
    private String bannerUrl;

    /**
     * 图片编号
     */
    @Column(name = "banner_code")
    private String bannerCode;

    /**
     * 状态
     */
    private String status;

    /**
     * 创建日期
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 变更日期
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 图片url地址
     */
    @Column(name = "image_url")
    private String imageUrl;

    private static final long serialVersionUID = 1L;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取版本控制
     *
     * @return version - 版本控制
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 设置版本控制
     *
     * @param version 版本控制
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * 获取banner 图描述
     *
     * @return banner_name - banner 图描述
     */
    public String getBannerName() {
        return bannerName;
    }

    /**
     * 设置banner 图描述
     *
     * @param bannerName banner 图描述
     */
    public void setBannerName(String bannerName) {
        this.bannerName = bannerName == null ? null : bannerName.trim();
    }

    /**
     * 获取链接url地址
     *
     * @return banner_url - 链接url地址
     */
    public String getBannerUrl() {
        return bannerUrl;
    }

    /**
     * 设置链接url地址
     *
     * @param bannerUrl 链接url地址
     */
    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl == null ? null : bannerUrl.trim();
    }

    /**
     * 获取图片编号
     *
     * @return banner_code - 图片编号
     */
    public String getBannerCode() {
        return bannerCode;
    }

    /**
     * 设置图片编号
     *
     * @param bannerCode 图片编号
     */
    public void setBannerCode(String bannerCode) {
        this.bannerCode = bannerCode == null ? null : bannerCode.trim();
    }

    /**
     * 获取状态
     *
     * @return status - 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 获取创建日期
     *
     * @return create_time - 创建日期
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建日期
     *
     * @param createTime 创建日期
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取变更日期
     *
     * @return update_time - 变更日期
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置变更日期
     *
     * @param updateTime 变更日期
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取备注信息
     *
     * @return remark - 备注信息
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注信息
     *
     * @param remark 备注信息
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取图片url地址
     *
     * @return image_url - 图片url地址
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 设置图片url地址
     *
     * @param imageUrl 图片url地址
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", version=").append(version);
        sb.append(", bannerName=").append(bannerName);
        sb.append(", bannerUrl=").append(bannerUrl);
        sb.append(", bannerCode=").append(bannerCode);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remark=").append(remark);
        sb.append(", imageUrl=").append(imageUrl);
        sb.append("]");
        return sb.toString();
    }
}