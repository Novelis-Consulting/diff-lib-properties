package org.novelis.diff.lib.properties;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ConfControleHtml {

	private HtmlWriter htmlWriter = new HtmlWriter();

	public void process(List<String> headers, List<LineExcel> lineExcels, String fileOut) throws IOException {
		initWorkBookWithSheet();

		createSheetAndContent(headers, lineExcels);

		writeToFile(fileOut);
	}

	private void createSheetAndContent(List<String> headers, List<LineExcel> lineExcels) {
		htmlWriter.openTable();

		createHeader(headers);

		int i = 1;
		while (lineExcels.size() > 0) {
			processLine(lineExcels.get(0), i);
			lineExcels.remove(0);
			i++;
		}

		htmlWriter.closeTable();

	}

	private void processLine(LineExcel lineExcelToProcess, int i) {
		htmlWriter.openTr();
		for (int j = 0; j < lineExcelToProcess.getColumnsExcels().size(); j++) {

			htmlWriter.writeTdWithStyleClass(lineExcelToProcess.getColumnsExcels().get(j).getValue(), lineExcelToProcess.getColumnsExcels().get(j)
					.getStyleClass());

		}
		htmlWriter.closeTr();

	}

	private void createHeader(List<String> headers) {
		htmlWriter.openTr();
		for (String colonneName : headers) {
			htmlWriter.writeTh(colonneName);
		}
		htmlWriter.closeTr();
	}

	private void initWorkBookWithSheet()

	{
		htmlWriter.addFreeContent("<head><meta charset=\"UTF-8\"></head>");
		htmlWriter.openStyle();

		htmlWriter.writeStyle("table,table tr, table th,table td{border: 1px solid #555555;border-collapse:collapse}");
		htmlWriter
				.writeStyle("table th{  background-color: #555555;border: 1px solid #555555;color: #FFFFFF;padding: 3px;text-align: left;vertical-align: top;}");
		htmlWriter.writeStyle(".colorRed{background-color: red;}");
		htmlWriter.writeStyle(".colorDiff{background-color: #FF6600;}");

		htmlWriter.closeStyle();
	}

	private void writeToFile(String fileOut) throws FileNotFoundException, IOException {
		FileOutputStream os = null;
		try {
			final File file = new File(fileOut);
			file.createNewFile();

			htmlWriter.writeToFile(file);

		} finally {
			if (os != null)
				os.close();
		}
	}

}
