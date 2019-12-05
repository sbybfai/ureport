package org.sbybfai.ureport.expression.function;

import java.util.List;

import org.sbybfai.ureport.build.Context;
import org.sbybfai.ureport.expression.model.data.ExpressionData;
import org.sbybfai.ureport.model.Cell;

/**
 * @author Jacky.gao
 * @since 2017年12月7日
 */
public class ParameterIsEmptyFunction extends ParameterFunction{
	@Override
	public Object execute(List<ExpressionData<?>> dataList, Context context,
			Cell currentCell) {
		Object obj = super.execute(dataList, context, currentCell);
		if(obj==null || obj.toString().trim().equals("")){
			return true;
		}
		return false;
	}
	@Override
	public String name() {
		return "emptyparam";
	}
}
