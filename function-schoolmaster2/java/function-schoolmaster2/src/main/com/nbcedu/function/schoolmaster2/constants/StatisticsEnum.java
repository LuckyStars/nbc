package com.nbcedu.function.schoolmaster2.constants;

public enum StatisticsEnum {
	bookSite("场馆预定"),
	documentFlow("公文流转"),
	substitute("请假代课"),
	repair("在线报修");
	 
    private final String value;

    //构造器默认也只能是private, 从而保证构造函数只能在内部使用
	StatisticsEnum(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }

}
