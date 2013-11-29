package com.nbcedu.function.schoolmaster2.constants;

public class Constants {
	/**当前登录用户uid key**/
	public static final String SESSION_UID_KEY = "sm2_init";
	public static final String COMMON_UPLOAD = "uploads";
	
	public static final String SESSION_USER_NAME = "sm2_userName";
	enum{
		  bookSite, spring, summer, fall;

	        private final static String location = "Phoenix";        

	        public static Season getBest() {
	            if (location.equals("Phoenix"))
	                return winter;
	            else
	                return summer;
	        }

		
	} 
	
	
}
