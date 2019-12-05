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

import java.util.List;

import org.sbybfai.ureport.Utils;
import org.sbybfai.ureport.build.Context;
import org.sbybfai.ureport.expression.model.data.ExpressionData;
import org.sbybfai.ureport.expression.model.data.ObjectExpressionData;
import org.sbybfai.ureport.model.Cell;
import org.sbybfai.ureport.model.Row;

/**
 * @author Jacky.gao
 * @since 2017年4月25日
 */
public class RowFunction implements Function{
	@Override
	public Object execute(List<ExpressionData<?>> dataList, Context context,Cell currentCell) {
		if (dataList.size() == 1) {
			ObjectExpressionData exprData = (ObjectExpressionData) dataList.get(0);
			String cellName = (String) exprData.getData();
			List<Cell> targetCells = Utils.fetchTargetCells(currentCell, context, cellName);
			Row row = targetCells.get(0).getRow();
			return row.getRowNumber();
		}
		Row row=currentCell.getRow();
		return row.getRowNumber();
	}
	@Override
	public String name() {
		return "row";
	}
}
