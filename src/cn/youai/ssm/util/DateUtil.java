package cn.youai.ssm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 卜亮亮
 * @version 1.0
 */
public class DateUtil {
	/**
	 * 标准时间差
	 * @param beginDate
	 * @param endDate
	 * @param f
	 * @return
	 */
	 public static long getDifference(Date beginDate, Date endDate, int f) {
	        long result = 0;
	        if (beginDate == null || endDate == null) {
	            return 0;
	        }
	        try {
	            // 日期相减获取日期差X(单位:毫秒)
	            long millisecond = endDate.getTime() - beginDate.getTime();
	            /**
	             * Math.abs((int)(millisecond/1000)); 绝对值 1秒 = 1000毫秒
	             * millisecond/1000 --> 秒 millisecond/1000*60 - > 分钟
	             * millisecond/(1000*60*60) -- > 小时 millisecond/(1000*60*60*24) -->
	             * 天
	             * */
	            switch (f) {
	            case 0: // second
	                return  (millisecond / 1000);
	            case 1: // minute
	                return (millisecond / (1000 * 60));
	            case 2: // hour
	                return  (millisecond / (1000 * 60 * 60));
	            case 3: // day
	                return (millisecond / (1000 * 60 * 60 * 24));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return result;
	    }
	 
	 
	 /**
	 * 计算时差 根据 long 返回时间点
	 * @param millisecond
	 * @return string 0天0时11分55秒
	 */
	public static String parseMillisecone(long millisecond) {
	    String time = null;
	    try {
	        long yushu_day = millisecond % (1000 * 60 * 60 * 24);
	        long yushu_hour = (millisecond % (1000 * 60 * 60 * 24))
	                % (1000 * 60 * 60);
	        long yushu_minute = millisecond % (1000 * 60 * 60 * 24)
	                % (1000 * 60 * 60) % (1000 * 60);
	        @SuppressWarnings("unused")
	        long yushu_second = millisecond % (1000 * 60 * 60 * 24)
	                % (1000 * 60 * 60) % (1000 * 60) % 1000;
	        if (yushu_day == 0) {
	            return (millisecond / (1000 * 60 * 60 * 24)) + "天";
	        } else {
	            if (yushu_hour == 0) {
	                return (millisecond / (1000 * 60 * 60 * 24)) + "天"
	                        + (yushu_day / (1000 * 60 * 60)) + "时";
	            } else {
	                if (yushu_minute == 0) {
	                    return (millisecond / (1000 * 60 * 60 * 24)) + "天"
	                            + (yushu_day / (1000 * 60 * 60)) + "时"
	                            + (yushu_hour / (1000 * 60)) + "分";
	                } else {
	                    return (millisecond / (1000 * 60 * 60 * 24)) + "天"
	                            + (yushu_day / (1000 * 60 * 60)) + "时"
	                            + (yushu_hour / (1000 * 60)) + "分"
	                            + (yushu_minute / 1000) + "秒";

	                }

	            }

	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return time;
	}
	
	/**
	 * 返回两个时间戳之间的相差天数
	 * @param singinDate
	 * @return
	 * @throws ParseException
	 */
	public static Long dayDifference(Long singinDate) throws ParseException{
		Date systemDate = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String formatString1 = format.format(systemDate);
		String formatString2 = format.format(singinDate*1000);
		Date beginDate = format.parse(formatString1);
		Date endDate = format.parse(formatString2);
		Long dayDiff = (beginDate.getTime()-endDate.getTime())/(24*60*60*1000);
		return dayDiff;
	}
	
	/**
	 * 获取当前系统时间
	 */
	
	public static String getSystemDatestr(Date date,int fomate){
		Date systemDate = null;
		String dateStr = null;
		SimpleDateFormat format = null;
		if(date == null || date.equals("")){
			systemDate = new Date();
		}else{
			systemDate = date;
		}
		switch (fomate) {
		case 1:
			format = new SimpleDateFormat("yyyy-MM-dd");
			dateStr = format.format(systemDate);
			break;
		case 2:
			format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			dateStr = format.format(systemDate);
			break;
		}
		return dateStr;
	}
		
	/**
	 * 时间大小的比较
	 */
  public static int compareToDate(String beginTime, String endTime ){
	  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");   
	  int comResult = -5;
	  try {
	  	Date date1 = format.parse(beginTime);
	  	Date date2 = format.parse(endTime);
	  	comResult = date1.compareTo(date2);
	  } catch (ParseException e) {
	  	e.printStackTrace();
	  }
	  return comResult;
  } 
	
  
  /**
   * 格式化时间
 * @throws ParseException 
   */
  public static Date formateDate(Date date){
	  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
	  String dateStr = format.format(date);
	  try {
		date = format.parse(dateStr);
	} catch (ParseException e) {
		e.printStackTrace();
	}
	  return date;
  }
  
	/**
	 * 测试
	 */
	public static void main(String[] args) throws ParseException {
	    // 获取指定long型的时间
	    System.out.println(parseMillisecone(436765000L));
	    // 获取时间差的秒数
	    long diff = getDifference(new Date(), new SimpleDateFormat(
	            "yyyy-MM-dd HH:mm:ss").parse("1970-01-01 00:00:00"), 0);
	    System.out.println(getDifference(new Date(), new SimpleDateFormat(
	            "yyyy-MM-dd HH:mm:ss").parse("1901-01-01 00:00:00"), 0));
	    
	    System.out.println("时间：" + parseMillisecone(diff));
	    System.out.println("时间：" + parseMillisecone(diff * 1000));
	    
	    Date d = new Date();
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    String format2 = format.format(d);
	    Date beginDate= format.parse(format2); 
	    System.out.println("beginDate------"+beginDate);
	    Date endDate= format.parse("2007-12-24");   
	    long day=(beginDate.getTime()-endDate.getTime())/(24*60*60*1000); 
	    long yushu=day%7;
	    System.out.println("相隔的天数="+day+"-----"+yushu);
	    Long dd = 1540310399L;
	    Long long1 = dayDifference(dd);
	    System.out.println(long1);
	    
	    System.out.println("_____________"+formateDate(new Date()));

	}
}
