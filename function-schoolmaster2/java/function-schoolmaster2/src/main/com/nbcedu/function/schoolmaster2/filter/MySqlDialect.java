package com.nbcedu.function.schoolmaster2.filter;


import java.sql.Types;

import org.hibernate.dialect.MySQL5Dialect;

public class MySqlDialect  extends MySQL5Dialect {
	public MySqlDialect() {
		super();
		registerColumnType(Types.LONGVARCHAR, "text");
	}
}
