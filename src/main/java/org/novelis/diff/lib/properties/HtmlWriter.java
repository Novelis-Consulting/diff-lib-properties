package org.novelis.diff.lib.properties;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HtmlWriter {

	private StringBuffer html = new StringBuffer();

	public void openTable() {
		html.append("<table>\n");
	}

	public void openStyle() {
		html.append("<style>\n");
	}

	public void closeStyle() {
		html.append("</style>\n");
	}

	public void writeStyle(String styleContent) {
		html.append(styleContent + "\n");
	}

	public void closeTable() {
		html.append("</table>\n");

	}

	public void writeTd(String content) {
		html.append("<td> <div style=\"word-break: break-all;width: 215px !important;\">" + content + "</div> </td> \n");
	}

	public void writeTh(String content) {
		html.append("<th style=\"word-break: break-all;width: 215px !important;\"> " + content + " </th> \n");
	}

	public void writeTdWithStyle(String content, String style) {
		html.append("<td style=\"" + style + ";word-break: break-all;width: 215px !important;\"> " + content + " </td> \n");
	}

	public void writeTdWithStyleClass(String content, String styleClass) {
		html.append("<td class=\"" + styleClass + "\" style=\"word-break: break-all;width: 215px !important;\"> " + content + " </td> \n");
	}

	public void openTr() {
		html.append("<tr>\n");

	}

	public void closeTr() {
		html.append("</tr>\n");
	}

	public StringBuffer getHtml() {
		return html;
	}

	public static void main(String[] args) {
		HtmlWriter htmlWriter = new HtmlWriter();

		htmlWriter.openStyle();

		htmlWriter.writeStyle("table,table tr, table th,table td{border: 1px solid #555555;border-collapse:collapse}");
		htmlWriter
				.writeStyle("table th{  background-color: #555555;border: 1px solid #555555;color: #FFFFFF;padding: 3px;text-align: left;vertical-align: top;}");
		htmlWriter.writeStyle(".colorRed{background-color: red;}");
		htmlWriter.writeStyle(".colorDiff{background-color: #D1D1D1;}");

		htmlWriter.closeStyle();

		htmlWriter.openTable();

		htmlWriter.openTr();

		htmlWriter.writeTh("clé");

		htmlWriter.writeTh("integration");

		htmlWriter.writeTh("recette");

		htmlWriter.writeTh("preprod");

		htmlWriter.writeTh("prod");

		htmlWriter.closeTr();

		htmlWriter.closeTable();


	}

	public void writeToFile(File file) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(file);
			fileWriter.write(html.toString());

		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	public void addFreeContent(String content) {
		html.append(content);
		
	}

}
