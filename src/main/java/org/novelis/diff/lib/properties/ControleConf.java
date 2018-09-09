package org.novelis.diff.lib.properties;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.novelis.diff.lib.properties.log4jxml.Log4jConfiguration;


public class ControleConf {
	
	
	final static String CLE_PROPERTY = "#CLE_PROPERTY#";

	
	public static void comparePropertyFile(List<String> properties, List<String> propertiesHeader, String fileOut) throws Exception {
		if (properties.size() != propertiesHeader.size()) {
			throw new RuntimeException("taille de la liste des titres de colonnes est différentes du nombre de fichiers à comparer.");
		}
		ArrayList<LineExcel> lineExcels = new ArrayList<LineExcel>();

		Set keys = FilePropertyHelper.getKeys(properties);

		Map<String, Properties> propertiesByFile = new HashMap<String, Properties>();
		for (String propertieFile : properties) {
			if (propertieFile.endsWith("xml")) {
				propertiesByFile.put(propertieFile, Log4jConfiguration.getCleValueOfLog4Xml(propertieFile));
			} else {
				propertiesByFile.put(propertieFile, FilePropertyHelper.getPropertyObject(propertieFile));
			}
		}
		for (Object key : keys) {
			processKey(lineExcels, properties, propertiesByFile, key);
		}
		propertiesHeader.add(0, "clé");
		new ConfControleHtml().process(propertiesHeader, lineExcels, fileOut);
	}

	private static void processKey(ArrayList<LineExcel> lineExcels, List<String> properties, Map<String, Properties> propertiesByFile, Object key) {
		LineExcel lineExcel = new LineExcel();
		final ColumnExcel column = new ColumnExcel();
		column.setValue(key + "");
		column.setName(CLE_PROPERTY);
		lineExcel.getColumnsExcels().add(column);
		for (Object propertyFile : properties) {
			processKeyForPropertyFile(propertiesByFile, key, lineExcel, propertyFile);
		}
		setColorForDifferentsProperties(lineExcel);
		lineExcels.add(lineExcel);
	}

	private static void setColorForDifferentsProperties(LineExcel lineExcel) {
		boolean areDifferents = false;
		Object object = lineExcel.getColumnsExcels().get(1).getValue();
		for (ColumnExcel columnExcel : lineExcel.getColumnsExcels()) {
			if ((object == null || !object.equals(columnExcel.getValue())) && !CLE_PROPERTY.equals(columnExcel.getName())) {
				areDifferents = true;
				break;
			}
		}
		if (areDifferents) {
			for (ColumnExcel columnExcel : lineExcel.getColumnsExcels()) {
				if (columnExcel.getStyleClass() == null || columnExcel.getStyleClass().trim().isEmpty()) {
					columnExcel.setStyleClass("colorDiff");
				}
			}
		}
	}

	private static void processKeyForPropertyFile(Map<String, Properties> propertiesByFile, Object key, LineExcel lineExcel, Object propertyFile) {
		ColumnExcel columnExcel = new ColumnExcel();
		final Object object = propertiesByFile.get(propertyFile).get(key);
		if (object == null) {
			columnExcel.setStyleClass("colorRed");
			columnExcel.setValue("#NOT_FOUND#");
		} else {
			columnExcel.setValue(object + "");
		}
		lineExcel.getColumnsExcels().add(columnExcel);
	}
}
