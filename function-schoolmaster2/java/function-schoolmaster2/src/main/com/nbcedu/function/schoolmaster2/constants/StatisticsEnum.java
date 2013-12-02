package com.nbcedu.function.schoolmaster2.constants;

public enum StatisticsEnum {
	bookSite("场馆预定"),
	documentFlow("公文流转"),
	substitute("请假代课"),
	repair("在线报修");
	 
    public String name;
    
    public static StatisticsEnum getByType(String statisticsType){ 
	    if(statisticsType.equals("bookSite")){ 
	    	return bookSite;
	    }else if(statisticsType.equals("documentFlow")){ 
	    	return documentFlow;   
	    }else if(statisticsType.equals("substitute")){ 
	    	return substitute;
	    }else if(statisticsType.equals("repair")){ 
	    	return repair;
	    }else{
	    	throw new AssertionError("Unexpected value: " + statisticsType); 
	    }   
    }
	StatisticsEnum(String name) {
        this.name = name; 
    }
    
    public static void main(String[] args) {
		System.out.println(StatisticsEnum.getByType("repair").name);
	}

}
