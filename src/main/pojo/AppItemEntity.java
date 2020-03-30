package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author Lxh
 * @date 2020/3/20 15:01
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app_item")
public class AppItemEntity {
    @Id
    private Integer appItemId; //
    private Integer itemCategoryId; //
    private String itemName ; //
    private String itemUnit ; //
    private BigDecimal price; //
    private String commodityExplain ; //
    private String duration ; //
    private String picture ; //
    private Integer isShow; //
    private String createTime ; //
    private String updateTime ; //
    private BigDecimal promotionOriginalCost;
    private String promotionBeginDate;
    private String promotionEndDate;
    public void setAppItemId(Integer appItemId) {

        this.appItemId = appItemId; // 支持java的数据设置
    }

    public void setApp_item_id(Integer app_item_id) {

        this.appItemId = app_item_id; // 支持mysql(或单个单词java)的数据设置;
    }

    public void setAPP_ITEM_ID(Integer APP_ITEM_ID) {

        this.appItemId = APP_ITEM_ID; // 支持oracle的数据设置;
    }

    public Integer getAppItemId() {

        return appItemId; // 支持java的数据读取;
    }

    public Integer getApp_item_id() {

        return appItemId; // 支持库脚本(或单个单词java)的数据读取
    }

    public void setItemCategoryId(Integer itemCategoryId) {

        this.itemCategoryId = itemCategoryId; // 支持java的数据设置
    }

    public void setItem_category_id(Integer item_category_id) {

        this.itemCategoryId = item_category_id; // 支持mysql(或单个单词java)的数据设置;
    }

    public void setITEM_CATEGORY_ID(Integer ITEM_CATEGORY_ID) {

        this.itemCategoryId = ITEM_CATEGORY_ID; // 支持oracle的数据设置;
    }

    public Integer getItemCategoryId() {

        return itemCategoryId; // 支持java的数据读取;
    }

    public Integer getItem_category_id() {

        return itemCategoryId; // 支持库脚本(或单个单词java)的数据读取
    }

    public void setItemName(String itemName) {

        this.itemName = itemName; // 支持java的数据设置
    }

    public void setItem_name(String item_name) {

        this.itemName = item_name; // 支持mysql(或单个单词java)的数据设置;
    }

    public void setITEM_NAME(String ITEM_NAME) {

        this.itemName = ITEM_NAME; // 支持oracle的数据设置;
    }

    public String getItemName() {

        return itemName; // 支持java的数据读取;
    }

    public String getItem_name() {

        return itemName; // 支持库脚本(或单个单词java)的数据读取
    }

    public void setItemUnit(String itemUnit) {

        this.itemUnit = itemUnit; // 支持java的数据设置
    }

    public void setItem_unit(String item_unit) {

        this.itemUnit = item_unit; // 支持mysql(或单个单词java)的数据设置;
    }

    public void setITEM_UNIT(String ITEM_UNIT) {

        this.itemUnit = ITEM_UNIT; // 支持oracle的数据设置;
    }

    public String getItemUnit() {

        return itemUnit; // 支持java的数据读取;
    }

    public String getItem_unit() {

        return itemUnit; // 支持库脚本(或单个单词java)的数据读取
    }

    public void setPrice(BigDecimal price) {

        this.price = price; // 支持mysql(或单个单词java)的数据设置;
    }

    public void setPRICE(BigDecimal PRICE) {

        this.price = PRICE; // 支持oracle的数据设置;
    }

    public BigDecimal getPrice() {

        return price; // 支持库脚本(或单个单词java)的数据读取
    }

    public void setCommodityExplain(String commodityExplain) {

        this.commodityExplain = commodityExplain; // 支持java的数据设置
    }

    public void setCommodity_explain(String commodity_explain) {

        this.commodityExplain = commodity_explain; // 支持mysql(或单个单词java)的数据设置;
    }

    public void setCOMMODITY_EXPLAIN(String COMMODITY_EXPLAIN) {

        this.commodityExplain = COMMODITY_EXPLAIN; // 支持oracle的数据设置;
    }

    public String getCommodityExplain() {

        return commodityExplain; // 支持java的数据读取;
    }

    public String getCommodity_explain() {

        return commodityExplain; // 支持库脚本(或单个单词java)的数据读取
    }

    public void setDuration(String duration) {

        this.duration = duration; // 支持mysql(或单个单词java)的数据设置;
    }

    public void setDURATION(String DURATION) {

        this.duration = DURATION; // 支持oracle的数据设置;
    }

    public String getDuration() {

        return duration; // 支持库脚本(或单个单词java)的数据读取
    }

    public void setPicture(String picture) {

        this.picture = picture; // 支持mysql(或单个单词java)的数据设置;
    }

    public void setPICTURE(String PICTURE) {

        this.picture = PICTURE; // 支持oracle的数据设置;
    }

    public String getPicture() {

        return picture; // 支持库脚本(或单个单词java)的数据读取
    }

    public void setIsShow(Integer isShow) {

        this.isShow = isShow; // 支持java的数据设置
    }

    public void setIs_show(Integer is_show) {

        this.isShow = is_show; // 支持mysql(或单个单词java)的数据设置;
    }

    public void setIS_SHOW(Integer IS_SHOW) {

        this.isShow = IS_SHOW; // 支持oracle的数据设置;
    }

    public Integer getIsShow() {

        return isShow; // 支持java的数据读取;
    }

    public Integer getIs_show() {

        return isShow; // 支持库脚本(或单个单词java)的数据读取
    }

    public void setCreateTime(String createTime) {

        this.createTime = createTime; // 支持java的数据设置
    }

    public void setCreate_time(String create_time) {

        this.createTime = create_time; // 支持mysql(或单个单词java)的数据设置;
    }

    public void setCREATE_TIME(String CREATE_TIME) {

        this.createTime = CREATE_TIME; // 支持oracle的数据设置;
    }

    public String getCreateTime() {

        return createTime; // 支持java的数据读取;
    }

    public String getCreate_time() {

        return createTime; // 支持库脚本(或单个单词java)的数据读取
    }

    public void setUpdateTime(String updateTime) {

        this.updateTime = updateTime; // 支持java的数据设置
    }

    public void setUpdate_time(String update_time) {

        this.updateTime = update_time; // 支持mysql(或单个单词java)的数据设置;
    }

    public void setUPDATE_TIME(String UPDATE_TIME) {

        this.updateTime = UPDATE_TIME; // 支持oracle的数据设置;
    }

    public String getUpdateTime() {

        return updateTime; // 支持java的数据读取;
    }

    public String getUpdate_time() {

        return updateTime; // 支持库脚本(或单个单词java)的数据读取
    }

    public BigDecimal getPromotionOriginalCost() {

        return promotionOriginalCost;
    }

    public void setPromotionOriginalCost(BigDecimal promotionOriginalCost) {

        this.promotionOriginalCost = promotionOriginalCost;
    }

    public String getPromotionBeginDate() {

        return promotionBeginDate;
    }

    public void setPromotionBeginDate(String promotionBeginDate) {

        this.promotionBeginDate = promotionBeginDate;
    }

    public String getPromotionEndDate() {

        return promotionEndDate;
    }

    public void setPromotionEndDate(String promotionEndDate) {

        this.promotionEndDate = promotionEndDate;
    }

    @Override
    public String toString() {

        return "AppItemEntity [appItemId=" + appItemId + ", itemCategoryId="
                + itemCategoryId + ", itemName=" + itemName + ", itemUnit="
                + itemUnit + ", price=" + price + ", commodityExplain="
                + commodityExplain + ", duration=" + duration + ", picture="
                + picture + ", isShow=" + isShow + ", createTime=" + createTime
                + ", updateTime=" + updateTime + ", promotionOriginalCost="
                + promotionOriginalCost + ", promotionBeginDate="
                + promotionBeginDate + ", promotionEndDate=" + promotionEndDate
                + "]";
    }



}
