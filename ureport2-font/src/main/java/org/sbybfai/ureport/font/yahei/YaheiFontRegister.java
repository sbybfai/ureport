package org.sbybfai.ureport.font.yahei;

import org.sbybfai.ureport.export.pdf.font.FontRegister;

/**
 * @author Jacky.gao
 * @since 2014年5月7日
 */
public class YaheiFontRegister implements FontRegister {

	public String getFontName() {
		return "微软雅黑";
	}

	public String getFontPath() {
		return "org/sbybfai/ureport/font/yahei/msyh.ttc";
	}
}
