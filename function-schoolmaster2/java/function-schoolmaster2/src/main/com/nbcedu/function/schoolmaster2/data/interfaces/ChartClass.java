package com.nbcedu.function.schoolmaster2.data.interfaces;

public enum ChartClass {
	
	SingleChart(Single.class);
	private Class<? extends ChartEnum> clazz;
	private ChartClass(Class<? extends ChartEnum> clazz){
		this.clazz = clazz;
	}
	
	enum Single implements ChartEnum{
		Area2D(1,"Area2D"),
		Bar2D(2,"Bar2D"),
		Candlestick(3,"Candlestick");
		
		private final String name;
		private final int id;
		
		private Single(int id,String name){
			this.id = id;
			this.name = name;
		}
		public String getName() {
			return name;
		}
		public int getId() {
			return id;
		}
	};
	
	public static class Test{
		public static void main(String[] args) {
			new Object() {
				void doSome() {
					System.out.println("doSome");
				}
			}.doSome();
		}
	}
}
