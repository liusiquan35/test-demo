package com.example.test.demo.utils.excel;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 导入excel文件
 */
public class ReadExcel {
    private static final Logger logger = LoggerFactory.getLogger(ReadExcel.class);

    /**
     * @param file
     * @param clazz
     * @return java.util.List<T>
     * @author 86135
     * @createTime 2021/9/7 15:27
     * @description 读取excel文件的值
     */
    public static<T> List<T> readExcel(MultipartFile file, Class<T> clazz) {
        List<T> objectList = new ArrayList<>();

        Workbook workbook = null;
        InputStream inputStream = null;

        try {
            inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String substring = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            //判断后缀是否为excel文件
            if (substring.equals("xls") || substring.equals("xlsx")) {
                //创建文本簿
                workbook = WorkbookFactory.create(inputStream);
            }

            //获取第一个工作表
            Sheet sheetAt = workbook.getSheetAt(0);

            //获取数据的总行数
            int lastRowNum = sheetAt.getLastRowNum();

            //获取数据的总列数
            int numberOfCells = sheetAt.getRow(0).getPhysicalNumberOfCells();

            //获取表头
            Row headRow = sheetAt.getRow(0);

            //获取反射类的所有字段
            Field[] declaredFields = getAllFields(clazz);

            //创建一个字段数组，用于和excel执行顺序一致
            Field[] fields = new Field[numberOfCells];

            //获取静态方法，该方法适用于把excel表头映射成对应的实体类属性
            Method covert = clazz.getDeclaredMethod("convert", String.class);

            T t = clazz.newInstance();

            for (int i = 0; i < numberOfCells; i++) {
                for (Field field : declaredFields) {
                    Cell cell = headRow.getCell(i);
                    //按照excel中的存储存放数组,以便后面遍历excel表格,数据一一对应.
                    if (covert.invoke(t, getXCellVal(cell)).equals(field.getName())) {
                        fields[i] = field;
                        continue;
                    }
                }
            }

            for (int x = 1; x < lastRowNum; x++) {
                T object = clazz.newInstance();
                //获得第i行对象
                Row row = sheetAt.getRow(x);
                //如果一行里的所有单元格都为空则不放进list里面
                int a = 0;
                for (int y = 0; y < numberOfCells; y++) {
                    if (!(row == null)) {
                        Cell cell = row.getCell(y);
                        if (cell == null) {
                            a++;
                        } else {
                            Field field = fields[y];
                            String value = getXCellVal(cell);
                            if (value != null && !value.equals("") && field != null) {
                                //给字段设置值.
                                setValue(field, value, object);
                            }
                        }
                    }
                }

                if (a != numberOfCells && row != null) {
                    objectList.add(object);
                }
            }

        } catch (Exception e) {
            logger.debug(e.getMessage(), e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.debug(e.getMessage(), e);
                }
            }
        }
        return objectList;
    }

    /**
     * 获取本类及其父类的属性的方法
     * @param clazz 当前类对象
     * @return 字段数组
     */
    private static Field[] getAllFields(Class<?> clazz) {
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null){
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        return fieldList.toArray(fields);
    }

    /**
     * @param field
     * @param value
     * @param object
     * @return void
     * @author 86135
     * @createTime 2021/9/7 11:20
     * @description 给字段赋值，判断值的类型，然后转换成实体类需要的类型
     */
    private static void setValue(Field field, String value, Object object) {
        try {
            field.setAccessible(true);
            DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            if (field.getGenericType().toString().contains("Integer")) {
                field.set(object, Integer.valueOf(value));
            } else if (field.getGenericType().toString().contains("String")) {
                field.set(object, value);
            } else if (field.getGenericType().toString().contains("Date")) {
                field.set(object, fmt.parse(value));
            }
            field.setAccessible(false);
        } catch (ParseException e) {
            logger.debug(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            logger.debug(e.getMessage(), e);
        }
    }

    /**
     * @param cell
     * @return java.lang.String
     * @author 86135
     * @createTime 2021/9/7 11:03
     * @description 获取单元格中的值
     */
    private static String getXCellVal(Cell cell) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        DecimalFormat df = new DecimalFormat("0.0000");
        String val = "";
        switch (cell.getCellType()) {
            case XSSFCell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    val = fmt.format(cell.getDateCellValue()); //日期型
                } else {
                    val = df.format(cell.getNumericCellValue()); //数字型
                    // 去掉多余的0，如最后一位是.则去掉
                    val = val.replaceAll("0+?$", "").replaceAll("[.]$", "");
                }
                break;
            case XSSFCell.CELL_TYPE_STRING: //文本类型
                val = cell.getStringCellValue();
                break;
            case XSSFCell.CELL_TYPE_BOOLEAN: //布尔型
                val = String.valueOf(cell.getBooleanCellValue());
                break;
            case XSSFCell.CELL_TYPE_BLANK: //空白
                val = cell.getStringCellValue();
                break;
            case XSSFCell.CELL_TYPE_ERROR: //错误
                val = "";
                break;
            case XSSFCell.CELL_TYPE_FORMULA: //公式
                try {
                    val = String.valueOf(cell.getStringCellValue());
                } catch (IllegalStateException e) {
                    val = String.valueOf(cell.getNumericCellValue());
                }
                break;
            default:
                val = cell.getRichStringCellValue() == null ? null : cell.getRichStringCellValue().toString();
        }
        return val;
    }
}