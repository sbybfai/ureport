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
package org.sbybfai.ureport.expression.function.math;

import java.math.BigDecimal;
import java.util.List;

import org.sbybfai.ureport.build.Context;
import org.sbybfai.ureport.expression.model.data.ExpressionData;
import org.sbybfai.ureport.expression.model.data.ObjectExpressionData;
import org.sbybfai.ureport.model.Cell;

/**
 * @author Jacky.gao
 * @since 2017年1月23日
 */
public class ChnMoneyFunction extends MathFunction {
	private static final String[] CN_UPPER_NUMBER = { "零", "壹", "贰", "叁", "肆","伍", "陆", "柒", "捌", "玖" };
	private static final String[] CN_UPPER_UNIT = { "分", "角", "元","拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "兆", "拾","佰", "仟" };
	private static final String CN_NEGATIVE = "负";
	private static final int NUMBER_PRECISION = 2;
	private static final String CN_ZEOR = "零";
	private static final String CN_FULL = "整";
	@Override
	public Object execute(List<ExpressionData<?>> dataList, Context context,Cell currentCell) {
		BigDecimal data = buildBigDecimal(dataList);
		Boolean is6MustZero = false;
		Boolean is2MustZero = false;
		if (dataList.size() == 3) {
			ObjectExpressionData args1 = (ObjectExpressionData) dataList.get(1);
			ObjectExpressionData args2 = (ObjectExpressionData) dataList.get(2);
			is6MustZero = (Boolean) args1.getData();
			is2MustZero = (Boolean) args2.getData();
		}
		return numberToRMB(data, is6MustZero, is2MustZero);
	}

	@Override
	public String name() {
		return "rmb";
	}
	
	public static void main(String[] args) {
		BigDecimal numberData=new BigDecimal(2031002120.25);
		String chn=numberToRMB(numberData, false, false);
		System.out.println(chn);
	}

	/**
	 *
	 * @param numberData 需要转化的数字
	 * @param is6MustZero 万位为0时，是否需要添加零
	 * @param is2MustZero 元位为0时，是否需要添加零
	 * @return
	 */
	private static String numberToRMB(BigDecimal numberData, Boolean is6MustZero, Boolean is2MustZero){
		StringBuilder sb = new StringBuilder();
		int signum = numberData.signum();
		if (signum == 0) {
			return CN_ZEOR;
		}
		long number = numberData.movePointRight(NUMBER_PRECISION).setScale(0, BigDecimal.ROUND_HALF_UP).abs().longValue();
		long scale = number % 100;
		int numUnit = 0;
		int numIndex = 0;
		boolean getZero = false;
		if (!(scale > 0)) {
			 numIndex = 2;
			 number = number / 100;
			 getZero = true;
		}
		if ((scale > 0) && (!(scale % 10 > 0))) {
			numIndex = 1;
			number = number / 10;
			getZero = true;
		}
		int zeroSize = 0;
		while (true) {
		    if (number <= 0) {
		        break;
		    }
		    numUnit = (int) (number % 10);
		    if (numUnit > 0) {
		        if ((numIndex == 9) && (zeroSize >= 3)) {	//前面遇0太多，补万
		            sb.insert(0, CN_UPPER_UNIT[6]);
		        }
		        if ((numIndex == 13) && (zeroSize >= 3)) {	 //前面遇0太多，补亿
		            sb.insert(0, CN_UPPER_UNIT[10]);
		        }
		        sb.insert(0, CN_UPPER_UNIT[numIndex]);
		        sb.insert(0, CN_UPPER_NUMBER[numUnit]);
		        getZero = false;
		        zeroSize = 0;
		    } else {
		        ++zeroSize;
		        if (!(getZero) && needPutZero(numIndex, is2MustZero, is6MustZero)) {
		        	sb.insert(0, CN_UPPER_NUMBER[numUnit]); //填零
				}

		        if (numIndex == 2) {
		            if (number > 0) {
		                sb.insert(0, CN_UPPER_UNIT[numIndex]); //补元
		            }
		        } else if (((numIndex - 2) % 4 == 0) && (number % 1000 > 0)) {
		            sb.insert(0, CN_UPPER_UNIT[numIndex]);
		        }
		        getZero = true;
		    }
		    number = number / 10;
		    ++numIndex;
		}
		if (signum == -1) {
		    sb.insert(0, CN_NEGATIVE);
		}
		if (!(scale > 0)) {
			sb.append(CN_FULL);
		}
		return sb.toString();
	}

	private static boolean needPutZero(int numIndex, boolean is2MustZero, boolean is6MustZero){
		if(numIndex == 2 && !is2MustZero){	//如果是元位，且不要求为零
			return false;
		}
		if(numIndex == 6 && !is6MustZero){ //如果是万位，且不要求为零
			return false;
		}
		return true;

	}
}


