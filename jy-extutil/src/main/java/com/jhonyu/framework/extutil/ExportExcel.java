package com.jhonyu.framework.extutil;


import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.jhonyu.framework.extend.BrowserHelper;
import com.jhonyu.framework.extend.LogHelper;
import com.jhonyu.framework.util.common.CollectionUtil;

/**
 * 
 * @Description: 数据的导出
 * @className: ExportExcel
 * @userName: 重构  by jiangyu
 * @author chen
 * @date: 2016年1月27日 上午10:27:59
 */
public class ExportExcel
{
    private static final Logger LOG = Logger.getLogger(ExportExcel.class);
    
    /** 默认标题 **/
    private static final String DEFALUT_TITLE = "Sheet";
    
    /** 默认时间类型 **/
    private static final String DEFALUT_PATTERN = "yyyy-MM-dd";
    
    
    /**
     * @Description: 导出excel
     * @userName: jiangyu
     * @date: 2016年1月5日 下午12:44:18
     * @param request 请求对象
     * @param response 响应对象
     * @param headers 导出文件的表头
     * @param dataset 导出文件的数据
     * @param clazz 导出数据的类型
     * @param fileName 导出文件的名称
     * @return
     */
    public static boolean exportExcel(HttpServletRequest request, HttpServletResponse response,
                               String[] headers, Collection<?> dataset, Class<?> clazz,String fileName)
    {
        return exportExcel(request, response, DEFALUT_TITLE, headers, dataset,clazz, DEFALUT_PATTERN,fileName);
    }

    
    /**
     * @Description: 导出文件
     * @userName: jiangyu
     * @date: 2016年1月5日 下午12:45:56
     * @param request 请求对象
     * @param response 响应对象
     * @param title sheet名称
     * @param headers 导出文件的表头
     * @param dataset 导出文件的数据
     * @param clazz 导出数据的类型
     * @param pattern 时间类型
     * @param fileName 导出文件的名称
     * @return
     */
    public static boolean exportExcel(HttpServletRequest request, HttpServletResponse response,
                               String title, String[] headers, Collection<?> dataset,Class<?> clazz,
                               String pattern, String fileName)
    {
        /** 1.校验输入参数  **/
        if (!checkParams(title, headers, dataset, response, pattern))
        {
            return false;
        }

        /** added by jiangyu begin **/

        OutputStream fOut = null;
        try
        {
            if (CollectionUtil.isEmpty(fileName))
            {
                fileName = "report_" + System.currentTimeMillis();
            }
            /** 2.设置导出的头信息 **/
            response.setContentType("application/vnd.ms-excel");

            /** 3.根据浏览器进行转码，使其支持中文文件名 **/
            if (BrowserHelper.isIE(request))
            {
                /** 4.IE浏览器的特殊处理 **/
                response.setHeader("content-disposition","attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8")+ ".xls");
            }
            else
            {
                /** 5.其他浏览器的设置 **/
                String newtitle = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
                response.setHeader("content-disposition", "attachment;filename=" + newtitle+ ".xls");
            }
            String newtitle = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
            response.setHeader("content-disposition", "attachment;filename=" + newtitle + ".xls");
            /** added by jiangyu end **/

            /** 6.声明一个工作薄 **/
            HSSFWorkbook workbook = null;
            /** 7.写入数据 **/
            workbook = createWorkBook(title, headers, dataset,clazz, pattern);
            /** 8.转换成流  **/
            fOut = response.getOutputStream();
            workbook.write(fOut);
        }
        catch (IOException e)
        {
            LogHelper.errLog(LOG, e,"");
            throw new RuntimeException("Export excel data failed ！");
        }
        /** added by jiangyu **/
        catch (Exception e) {
            LogHelper.errLog(LOG, e,"");
            throw new RuntimeException("Export excel data failed ！");
        }
        finally
        {
            try
            {
                if (!CollectionUtil.isEmpty(fOut))
                {
                    fOut.flush();
                    fOut.close();
                }
            }
            catch (IOException e)
            {
                LogHelper.errLog(LOG, e,"close out stream failed ");
                e.printStackTrace();
            }
        }
        return true;
    }
    /**
     * @Description: 创建一个workbook
     * @userName: jiangyu
     * @date: 2016年1月4日 下午6:09:25
     * @param title 标题
     * @param headers 表头项
     * @param dataset excel数据
     * @param pattern 时间日期的格式
     * @return
     * @throws Exception
     */
    @SuppressWarnings({"deprecation"})
    private static HSSFWorkbook createWorkBook(String title, String[] headers, Collection<?> dataset,Class<?> clazz,String pattern) throws Exception
    {
        HSSFWorkbook workbook= new HSSFWorkbook();;
        /** 1.生成一个表格  **/
        HSSFSheet sheet = workbook.createSheet(title);
        
        /** 2.设置表头的样式  **/
        HSSFCellStyle headerStyle = generateHeaderStyle(workbook);

        /** 3.产生表格标题行 **/
        HSSFRow row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++ )
        {
            sheet.setColumnWidth(i, 6000);
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(headerStyle);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        /** 4.产生实际的excel数据  **/
        workbook = dataProcessExport(dataset, clazz, pattern, workbook, sheet);
        return workbook;
    }

    
    /**
     * @Description: 查询出来的数据的转换成excel内容
     * @userName: jiangyu
     * @date: 2016年1月27日 上午10:25:39
     * @param dataset 数据集合
     * @param clazz 数据实体的类型
     * @param pattern 时间格式模板
     * @param workbook 
     * @param sheet excel文件sheet
     * @return 
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws Exception
     */
    private static HSSFWorkbook dataProcessExport(Collection<?> dataset, Class<?> clazz, String pattern,HSSFWorkbook workbook, HSSFSheet sheet)throws InstantiationException, IllegalAccessException, Exception
    {
        HSSFRow row = null;
        
        /** 1.设置excel内容的样式  added by jiangyu **/
        HSSFCellStyle contentStyle = generateContentStyle(workbook);
        
        /** 2.遍历集合数据，产生数据行  **/
        Iterator<?> it = dataset.iterator();
        int index=0;
        Object obj = clazz.newInstance();
        while (it.hasNext())
        {
            index++ ;
            row = sheet.createRow(index);
            obj = it.next();

            /** 3.利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值  **/
            Field[] fields = obj.getClass().getDeclaredFields();
            for (short i = 0; i < fields.length; i++ )
            {
                /** 4.设置单元格字段值  **/
                processField(row, i, fields, obj, workbook, pattern,contentStyle);
            }
        }
        return workbook;
    }

   

    /**
     * 检测导出excel输入参数
     * @param title   标题
     * @param headers 表格字段标题
     * @param dataset  数据集合
     * @param out  输出流
     * @param pattern 日期类型
     * @return
     */
    private static boolean checkParams(String title, String[] headers, Collection<?> dataset,HttpServletResponse out, String pattern)
    {
        if (StringUtils.isEmpty(title))
        {
            LOG.error("Excel title is empty.");
            return false;
        }
        if (null == headers || headers.length == 0)
        {
            LOG.error("Excel headers is empty.");
            return false;
        }
        if (null == dataset)
        {
            LOG.error("Excel dataset is null.");
            return false;
        }
        if (null == out)
        {
            LOG.error("Excel outputStream is null.");
            return false;
        }
        if (StringUtils.isEmpty(pattern))
        {
            LOG.error("Excel pattern is empty.");
            return false;
        }
        return true;
    }

    /**
     * 设置单元格字段值
     * 
     * @param row
     * @param i
     * @param style
     * @param fields
     * @param t
     * @param workbook
     * @param pattern
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
    private static void processField(HSSFRow row, short i, Field[] fields, Object t,HSSFWorkbook workbook, String pattern,HSSFCellStyle contentStyle) throws Exception
    {
        HSSFCell cell = row.createCell(i);
        cell.setCellStyle(contentStyle);
        
        Field field = fields[i];
        String fieldName = field.getName();
        String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase()+ fieldName.substring(1);
        try
        {
            Class<? extends Object> tCls = t.getClass();
            Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
            Object value = getMethod.invoke(t, new Object[] {});

            // 判断值的类型后进行强制类型转换
            String textValue = null;
            /** 加入为空的判断  added by jiangyu **/
            if (!CollectionUtil.isEmpty(value))
            {
                if (value instanceof Boolean)
                {
                    boolean bValue = (Boolean)value;
                    textValue = "是";
                    if (!bValue)
                    {
                        textValue = "否";
                    }
                }
                else if (value instanceof Date)
                {
                    Date date = (Date)value;
                    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                    textValue = sdf.format(date);
                }
                else
                {
                    /** 其它数据类型都当作字符串简单处理  **/
                    textValue = value.toString();
                }
            }

            /** 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成  **/
            if (!CollectionUtil.isEmpty(textValue))
            {
                Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                Matcher matcher = p.matcher(textValue);
                if (matcher.matches())
                {
                    /** 是数字当作double处理  **/
                    cell.setCellValue(Double.parseDouble(textValue));
                }
                else
                {
                    cell.setCellValue(textValue);
                }
            }
        }
        catch (SecurityException e)
        {
            LOG.error(e.getMessage());
            throw new SecurityException("SecurityException.");
        }
        catch (NoSuchMethodException e)
        {
            LOG.error(e.getMessage());
            throw new NoSuchMethodException("NoSuchMethodException.");
        }
        catch (IllegalArgumentException e)
        {
            LOG.error(e.getMessage());
            throw new IllegalArgumentException("IllegalArgumentException.");
        }
        catch (IllegalAccessException e)
        {
            LOG.error(e.getMessage());
            throw new IllegalAccessException("IllegalAccessException.");
        }
        catch (InvocationTargetException e)
        {
            LOG.error(e.getMessage());
            throw new InvocationTargetException(e, "InvocationTargetException.");
        }
    }
    
    /**
     * @Description: 生成表头样式
     * @userName: jiangyu
     * @date: 2016年1月27日 上午10:15:49
     * @param workbook
     * @return
     */
    private static HSSFCellStyle generateHeaderStyle(HSSFWorkbook workbook)
    {
        /** 1.生成一个样式  **/
        HSSFCellStyle headerStyle = workbook.createCellStyle();

        /** 2.设置这些样式 **/
        headerStyle.setFillForegroundColor(HSSFColor.WHITE.index);
        headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        
        /** 3.生成一个字体  **/
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short)14);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        /** 4.把字体应用到当前的样式  **/
        headerStyle.setFont(font);
        return headerStyle;
    }
    
    /**
     * @Description: 创建excel内容的样式
     * @userName: jiangyu
     * @date: 2016年1月27日 上午10:17:34
     * @param workbook
     * @return
     */
    private static HSSFCellStyle generateContentStyle(HSSFWorkbook workbook)
    {
        /** 1.生成一个样式 **/
        HSSFCellStyle contentStyle = workbook.createCellStyle();

        /** 2.设置这些样式  **/
        contentStyle.setFillForegroundColor(HSSFColor.WHITE.index);
        contentStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        
        /** 3.生成一个字体  **/
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short)11);
        /** 4.把字体应用到当前的样式  **/
        contentStyle.setFont(font);
        return contentStyle;
    }
}