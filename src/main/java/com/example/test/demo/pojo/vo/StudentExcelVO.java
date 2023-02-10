package com.example.test.demo.pojo.vo;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

    /**
     * @Description 学生信息excel导出Vo
     * @Author lsq
     *
     */
    @Data
    @HeadRowHeight(30)
    @NoArgsConstructor
    @AllArgsConstructor
    @ExcelIgnoreUnannotated
    public class StudentExcelVO {
        /**
         * 商品spu
         */
        @ColumnWidth(15)
        @ExcelProperty("商品SPU")
        private Integer commonId;

        /**
         * 商品名称
         */
        @ColumnWidth(40)
        @ExcelProperty("商品名称")
        private String goodsName;

        /**
         * 品牌
         */
        @ColumnWidth(10)
        @ExcelProperty("品牌")
        private String brand;

        /**
         * 品质
         */
        @ColumnWidth(10)
        @ExcelProperty("品质")
        private String quality;

        /**
         * 原库存
         */
        @ColumnWidth(10)
        @ExcelProperty("原库存")
        private Integer goodsStorage;

        /**
         * 原阶梯数量
         */
        @ColumnWidth(15)
        @ExcelProperty("原阶梯数量")
        private Integer batchNum;

        /**
         * 原价格
         */
        @ColumnWidth(10)
        @ExcelProperty("原价格")
        private BigDecimal batchPrice;

}
