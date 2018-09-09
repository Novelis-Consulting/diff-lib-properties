package org.novelis.diff.lib.properties;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
	
		List<String> properties=new ArrayList<String>();
		properties.add("/opt/work/bundle_apicil/git/git/confmep/int-apicil_105/web/cosyConfiguration.properties");
		properties.add("/opt/work/bundle_apicil/git/git/confmep/prod/web/cosyConfiguration.properties");

		List<String> propertiesHeader=new ArrayList<String>();
		propertiesHeader.add("int");
		propertiesHeader.add("prod");

		String fileOut="/opt/result.html";
		ControleConf.comparePropertyFile(properties, propertiesHeader, fileOut);

	}

}
