package org.novelis.diff.lib.properties;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
	
		List<String> properties=new ArrayList<String>();
		properties.add("/opt/hub/rec/conf/hub-common-configuration.properties");
		properties.add("/opt/hub/rec/conf/hub-configuration.properties");

		List<String> propertiesHeader=new ArrayList<String>();
		propertiesHeader.add("int");
		propertiesHeader.add("prod");

		String fileOut="/opt/result.html";
		ControleConf.comparePropertyFile(properties, propertiesHeader, fileOut);

	}

}
