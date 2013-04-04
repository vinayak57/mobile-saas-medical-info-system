package mis.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateConvert {

	public static java.sql.Date convertUtilToSQLdate(java.util.Date date)
	{
		
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		return sqlDate;
	}
	public static java.util.Date convertSQLToUtilDate(java.sql.Date date)
	{
		java.util.Date utilDate = new java.util.Date(date.getTime());
		
		return utilDate;
	}
	
	public static void main(String a[])
	{
		java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
		java.util.Date utilDate = new java.util.Date(System.currentTimeMillis());
		
		System.out.println("SQL Date : "+sqlDate);
		System.out.println("Util Date : "+utilDate);		
		System.out.println("Converted Util Date from SQL: "+ convertSQLToUtilDate(sqlDate));
		System.out.println("Converted SQL Date from Util "+ convertUtilDateToString(utilDate));
	} 
	public static String convertUtilDateToString(java.util.Date utilDate)
	{
		String result = new String();
		
		result = new SimpleDateFormat("yyyy/mm/dd").format(utilDate);
		
		return result;
	}
	public static java.util.Date convertStringToUtilDate(String input)
	{
		java.util.Date result;
		
		try {
			result = new SimpleDateFormat("yyyy-mm-dd").parse(input);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			result = new java.util.Date(System.currentTimeMillis());
			e.printStackTrace();
		}
		return result;
	}
	public static String convertCalendarToString(Calendar cal)
	{
		return new SimpleDateFormat("yyyy-mm-dd").format(cal.getTime());
	}
	public static Calendar convertStringToCal(String input)
	{
		Calendar  cal = Calendar.getInstance();
		try {
			cal.setTime(new SimpleDateFormat("yyyy-mm-dd").parse(input));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			cal.setTimeInMillis(System.currentTimeMillis());
		}
		return cal;
	}
}

