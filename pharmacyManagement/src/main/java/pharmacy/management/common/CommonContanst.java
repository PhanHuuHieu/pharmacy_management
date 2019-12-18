package pharmacy.management.common;

public class CommonContanst {
	public static final String YEAR_START="2016";
	public static void setBlank(String s0,String s1,String s2)
	{
		if(s0.equals("null"))
		{
			s0="0";
		}
		if(s1.equals("null"))
		{
			s1="0";
		}
		if(s2.equals("null"))
		{
			s2="0";
		}
	}
}
