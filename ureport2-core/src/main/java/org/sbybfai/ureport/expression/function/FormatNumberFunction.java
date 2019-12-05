/*******************************************************************************
 * Copyright 2017 Bstek
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package org.sbybfai.ureport.expression.function;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import org.sbybfai.ureport.Utils;
import org.sbybfai.ureport.build.BindData;
import org.sbybfai.ureport.build.Context;
import org.sbybfai.ureport.exception.ReportComputeException;
import org.sbybfai.ureport.expression.model.data.BindDataListExpressionData;
import org.sbybfai.ureport.expression.model.data.ExpressionData;
import org.sbybfai.ureport.expression.model.data.ObjectExpressionData;
import org.sbybfai.ureport.expression.model.data.ObjectListExpressionData;
import org.sbybfai.ureport.model.Cell;

/**
 * @author Jacky.gao
 * @since 2017年5月23日
 */
public class FormatNumberFunction implements Function {
	private final String defaultPattern="#";
	@Override
	public Object execute(List<ExpressionData<?>> dataList, Context context,Cell currentCell) {
		if(dataList==null){
			return "";
		}
		Object obj=null;
		String pattern=defaultPattern;
		if(dataList.size()==1){
			obj = buildExpressionData(dataList.get(0));
		}else if(dataList.size()>1){
			obj = buildExpressionData(dataList.get(0));
			Object patternData=buildExpressionData(dataList.get(1));
			if(patternData!=null){
				pattern=patternData.toString();				
			}
		}
		
		if(obj==null){
			throw new ReportComputeException("Function [formatnumber] need a number parameter at least");
		}else{
			BigDecimal bigData=Utils.toBigDecimal(obj);
			DecimalFormat df=new DecimalFormat(pattern);
			return df.format(bigData.doubleValue());
		}
	}

	private Object buildExpressionData(ExpressionData<?> data) {
		if(data instanceof ObjectListExpressionData){
			ObjectListExpressionData listExpressionData=(ObjectListExpressionData)data;
			List<?> list=listExpressionData.getData();
			if(list.size()>0){
				return list.get(0);
			}
		}else if(data instanceof ObjectExpressionData){
			return ((ObjectExpressionData)data).getData();
		}else if(data instanceof BindDataListExpressionData){
			BindDataListExpressionData bindDataList=(BindDataListExpressionData)data;
			List<BindData> list=bindDataList.getData();
			if(list.size()>0){
				return list.get(0).getValue();
			}
		}
		return null;
	}

	@Override
	public String name() {
		return "formatnumber";
	}
}
