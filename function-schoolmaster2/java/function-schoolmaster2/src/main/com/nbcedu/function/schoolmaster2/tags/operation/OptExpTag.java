package com.nbcedu.function.schoolmaster2.tags.operation;

import javax.servlet.jsp.el.ELException;

@SuppressWarnings("serial")
public class OptExpTag extends AbstractOperationTag {

	private String exp="";
	
	@Override
	boolean show(OperationContext opCtx) {
		
		Boolean result = Boolean.FALSE;
		String fmtedExp = formatExp(exp);
		try {
			result = (Boolean) this.pageContext.getExpressionEvaluator()
				.evaluate(fmtedExp, Boolean.TYPE, pageContext.getVariableResolver(),null);
		} catch (ELException e) {
			e.printStackTrace();
		}
		return result;
	}

	private String formatExp(String exp2) {
		return "${" + exp.replace("ctx.", AbstractOperationTag.CTX_KEY+".") + "}";
	}
	public void setExp(String exp) {
		this.exp = exp;
	}
	
}
