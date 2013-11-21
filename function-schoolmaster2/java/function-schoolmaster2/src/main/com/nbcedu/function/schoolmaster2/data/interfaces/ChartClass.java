package com.nbcedu.function.schoolmaster2.data.interfaces;

public enum ChartClass {
	
	SingleChart(Single.class);
	private Class<? extends ChartEnum> clazz;
	private ChartClass(Class<? extends ChartEnum> clazz){
		this.clazz = clazz;
	}
	
	public enum Single implements ChartEnum{
		
		Area2D(1,"Area2D"),
		Bar2D(2,"Bar2D"),
		Candlestick(3,"Candlestick"),
		Pie2D(4,"Pie2D"),
		Pie3D(5,"Pie3D"),
		Column2D(6,"Column2D"),
		Column3D(7,"Column3D"),
		Doughnut2D(8,"Doughnut2D"),
		Funnel(9,"Funnel"),
		Gantt(10,"Gantt"),
		Line(11,"Line");
		
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
	
}
