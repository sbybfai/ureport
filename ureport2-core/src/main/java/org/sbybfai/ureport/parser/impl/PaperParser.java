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
package org.sbybfai.ureport.parser.impl;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;

import org.sbybfai.ureport.definition.HtmlReportAlign;
import org.sbybfai.ureport.definition.Orientation;
import org.sbybfai.ureport.definition.PagingMode;
import org.sbybfai.ureport.definition.Paper;
import org.sbybfai.ureport.definition.PaperSize;
import org.sbybfai.ureport.definition.PaperType;
import org.sbybfai.ureport.parser.Parser;

/**
 * @author Jacky.gao
 * @since 2017年1月19日
 */
public class PaperParser implements Parser<Paper> {
	@Override
	public Paper parse(Element element) {
		Paper paper=new Paper();
		String orientation=element.attributeValue("orientation");
		paper.setOrientation(Orientation.valueOf(orientation));
		paper.setPaperType(PaperType.valueOf(element.attributeValue("type")));
		if(paper.getPaperType().equals(PaperType.CUSTOM)){
			paper.setWidth(Integer.valueOf(element.attributeValue("width")));
			paper.setHeight(Integer.valueOf(element.attributeValue("height")));
		}else{
			PaperSize size=paper.getPaperType().getPaperSize();
			paper.setWidth(size.getWidth());
			paper.setHeight(size.getHeight());
		}
		String leftMargin=element.attributeValue("left-margin");
		if(StringUtils.isNotBlank(leftMargin)){
			paper.setLeftMargin(Integer.valueOf(leftMargin));			
		}
		String rightMargin=element.attributeValue("right-margin");
		if(StringUtils.isNotBlank(rightMargin)){
			paper.setRightMargin(Integer.valueOf(rightMargin));			
		}
		String topMargin=element.attributeValue("top-margin");
		if(StringUtils.isNotBlank(topMargin)){
			paper.setTopMargin(Integer.valueOf(topMargin));			
		}
		String bottomMargin=element.attributeValue("bottom-margin");
		if(StringUtils.isNotBlank(bottomMargin)){
			paper.setBottomMargin(Integer.valueOf(bottomMargin));			
		}
		paper.setPagingMode(PagingMode.valueOf(element.attributeValue("paging-mode")));
		if(paper.getPagingMode().equals(PagingMode.fixrows)){
			paper.setFixRows(Integer.valueOf(element.attributeValue("fixrows")));
		}
		String columnEnabled=element.attributeValue("column-enabled");
		if(StringUtils.isNotBlank(columnEnabled)){
			paper.setColumnEnabled(Boolean.valueOf(columnEnabled));
		}
		if(paper.isColumnEnabled()){
			paper.setColumnCount(Integer.valueOf(element.attributeValue("column-count")));
			paper.setColumnMargin(Integer.valueOf(element.attributeValue("column-margin")));
		}
		String htmlReportAlign=element.attributeValue("html-report-align");
		if(StringUtils.isNotBlank(htmlReportAlign)){
			paper.setHtmlReportAlign(HtmlReportAlign.valueOf(htmlReportAlign));
		}
		String htmlIntervalRefreshValue=element.attributeValue("html-interval-refresh-value");
		if(StringUtils.isNotBlank(htmlIntervalRefreshValue)){
			paper.setHtmlIntervalRefreshValue(Integer.valueOf(htmlIntervalRefreshValue));
		}
		String htmlPaddingValue=element.attributeValue("html-padding-value");
		if(StringUtils.isNotBlank(htmlPaddingValue)){
			paper.setHtmlPaddingValue(htmlPaddingValue);
		}
		paper.setBgImage(element.attributeValue("bg-image"));
		return paper;
	}
}
