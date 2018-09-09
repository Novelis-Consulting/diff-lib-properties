package org.novelis.diff.lib.properties.log4jxml;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "configuration",namespace="http://jakarta.apache.org/log4j/")
public class Log4jConfiguration {

	private List<Appender> appenders = new ArrayList<Appender>();

	private List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

	@XmlElement(name = "appender")
	public List<Appender> getAppenders() {
		return appenders;
	}

	public void setAppenders(List<Appender> appenders) {
		this.appenders = appenders;
	}

	private List<Logger> loggers = new ArrayList<Logger>();

	public static void main(String[] args) throws Exception {
		Properties cleValue=getCleValueOfLog4Xml("D:/bundle_apicil/workspace1-7/ConfCosy/recette/web/log4j.xml");
		Properties cleValue1=getCleValueOfLog4Xml("D:/bundle_apicil/workspace1-7/ConfCosy/integration/web/log4j.xml");
		Properties cleValue2=getCleValueOfLog4Xml("D:/bundle_apicil/workspace1-7/ConfCosy/preprod/web/log4j.xml");
		Properties cleValue3=getCleValueOfLog4Xml("D:/bundle_apicil/workspace1-7/ConfCosy/prod/web/log4j.xml");
		
		Set<String> keys=new HashSet<String>();

	}

	public static Properties getCleValueOfLog4Xml(String fileName) throws JAXBException {
		File fXmlFile = new File(fileName);
		JAXBContext jaxbContext = JAXBContext.newInstance(Log4jConfiguration.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Log4jConfiguration log4jConfiguration = (Log4jConfiguration) jaxbUnmarshaller.unmarshal(fXmlFile);

		Properties hashMap = new Properties();
		for (Appender appender : log4jConfiguration.getAppenders()) {
			hashMap.put("appender name=\"" + appender.getName() + "\"", appender.getClasse());
			for (Param param : appender.getParams()) {
				if ("file".equals(param.getName())) {
					hashMap.put("appender name=\"" + appender.getName() + "\".param.file.value", param.getValue());
				}
			}
		}

		for (Logger logger : log4jConfiguration.getLoggers()) {
			hashMap.put("logger name=\"" + logger.getName() + "\"", logger.getLevel().getValue());

			for (AppenderRef appenderRef : logger.getAppenderRefs()) {
				hashMap.put("logger name=\"" + logger.getName() + "\".appenderRef[\"" + appenderRef.getRef() + "\"]", appenderRef.getRef());
			}
		}
		return hashMap;
	}

	public List<Logger> getLoggers() {
		return loggers;
	}

	@XmlElement(name = "logger")
	public void setLoggers(List<Logger> loggers) {
		this.loggers = loggers;
	}

}


class Level{
	private String value;

	public String getValue() {
		return value;
	}

	@XmlAttribute(name = "value")
	public void setValue(String value) {
		this.value = value;
	}
}

class Logger {
	private String name;

	private List<AppenderRef> appenderRefs = new ArrayList<AppenderRef>();
	
	private Level level;

	@XmlAttribute(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name = "appender-ref")
	public List<AppenderRef> getAppenderRefs() {
		return appenderRefs;
	}

	public void setAppenderRefs(List<AppenderRef> appenderRefs) {
		this.appenderRefs = appenderRefs;
	}

	@XmlElement(name = "level")
	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
}

class AppenderRef {
	private String ref;

	@XmlAttribute(name = "ref")
	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

}

class Appender {

	private String name;
	private String classe;
	private List<Param> params = new ArrayList<Param>();

	public String getClasse() {
		return classe;
	}

	@XmlAttribute(name = "class")
	public void setClasse(String classe) {
		this.classe = classe;
	}

	@XmlAttribute(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Param> getParams() {
		return params;
	}

	@XmlElement(name = "param")
	public void setParams(List<Param> params) {
		this.params = params;
	}

}

class Param {

	private String name;
	private String value;

	public String getValue() {
		return value;
	}

	@XmlAttribute(name = "value")
	public void setValue(String value) {
		this.value = value;
	}

	@XmlAttribute(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
