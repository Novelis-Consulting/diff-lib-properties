package org.novelis.diff.lib.properties;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.novelis.diff.lib.properties.log4jxml.Log4jConfiguration;

public class FilePropertyHelper {

	public static Set<Object> getKeys(String... propertyFiles) throws Exception {

		return getKeys(Arrays.asList(propertyFiles));

	}

	public static Set getKeys(List<String> propertyFiles) throws Exception {
		Set<Object> keys = new HashSet<Object>();

		for (String propertieFile : propertyFiles) {
			Properties properties = new Properties();
			if (propertieFile.endsWith("xml")) {
				properties = Log4jConfiguration.getCleValueOfLog4Xml(propertieFile);
			} else {
				properties.load(new FileReader(propertieFile));
			}
			keys.addAll(properties.keySet());
		}

		return keys;
	}

	public static Properties getPropertyObject(String propertieFile) throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileReader(propertieFile));
		return properties;
	}

}
