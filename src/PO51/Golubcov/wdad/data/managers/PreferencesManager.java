package PO51.Golubcov.wdad.data.managers;

import PO51.Golubcov.wdad.utils.PreferencesConstantManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Enumeration;
import java.util.Properties;


public class PreferencesManager {

    private static volatile PreferencesManager instance;

    private final Document doc;

    public static PreferencesManager getInstance() throws Exception {
        if (instance == null)
            synchronized (PreferencesManager.class){
                if (instance == null)
                    instance = new PreferencesManager();
            }
        return instance;
    }

    private PreferencesManager() throws Exception {
        DocumentBuilderFactory docBFac = DocumentBuilderFactory.newInstance();
        DocumentBuilder docB = docBFac.newDocumentBuilder();
        String pathConfig = "src\\PO51\\Golubcov\\wdad\\resources\\configuration\\appconfig.xml";
        File file = new File(pathConfig);
        doc = docB.parse(file);
    }

    public void setProperty(String key, String value){
        doc.getElementsByTagName(splitString(key)).item(0).setTextContent(value);
    }

    public String getProperty(String key){
        return doc.getElementsByTagName(splitString(key)).item(0).getTextContent();
    }

    public void setProperties(Properties prop){
        Enumeration enumProp = prop.elements();
        Enumeration enumKey = prop.keys();
        while (enumProp.hasMoreElements()){
            doc.getElementsByTagName(splitString((String) enumKey.nextElement())).item(0).setTextContent((String) enumProp.nextElement());
        }
    }

    public Properties getProperties(){
        Properties properties = new Properties();
        String[] tags = new String[]{
                PreferencesConstantManager.CREATEREGISTRY,
                PreferencesConstantManager.REGISTRYADDRESS,
                PreferencesConstantManager.REGISTRYPORT,
                PreferencesConstantManager.POLICYPATH,
                PreferencesConstantManager.USECODEBASEONLY,
                PreferencesConstantManager.CLASSPROVIDER
        };
        for (String tag : tags) {
            properties.put(tag, doc.getElementsByTagName(splitString(tag)).item(0).getTextContent());
        }

        return properties;
    }

    public void addBindedObject(String name, String className){
        Element el = (Element) doc.createElement("bindedobject");
        el.setAttribute("name", name);
        el.setAttribute("class", className);
        doc.getElementsByTagName("server").item(0).appendChild(el);
    }

    public void removeBindedObject(String name){
        NodeList nodeList = doc.getElementsByTagName("bindedobject");
        Element elNodeList;
        for(int i=0; i<nodeList.getLength(); i++){
            elNodeList = (Element) nodeList.item(i);
            if (elNodeList.getAttribute("name").equals(name)){
                doc.getElementsByTagName("server").item(0).removeChild(elNodeList);
            }
        }
    }


    @Deprecated
    public String getCreaterRegistry() {
        return  doc.getElementsByTagName("createregistry").item(0).getTextContent();
    }

    @Deprecated
    public String getRegistryAdress() {
        return doc.getElementsByTagName("registryaddress").item(0).getTextContent();
    }

    @Deprecated
    public String getRegistryPort() {
        return doc.getElementsByTagName("registryport").item(0).getTextContent();
    }

    @Deprecated
    public String getPolicyPath() {
        return doc.getElementsByTagName("policypath").item(0).getTextContent();
    }

    @Deprecated
    public String getUseCodeBaseOnly() {
        return doc.getElementsByTagName("usecodebaseonly").item(0).getTextContent();
    }

    @Deprecated
    public String getClassProvider() {
        return doc.getElementsByTagName("classprovider").item(0).getTextContent();
    }

    @Deprecated
    public void setCreaterRegistry(String newRegistry) {
        doc.getElementsByTagName("createregistry").item(0).setTextContent(newRegistry);
    }

    @Deprecated
    public void getRegistryAdress(String newRegAd) {
        doc.getElementsByTagName("registryaddress").item(0).setTextContent(newRegAd);
    }

    @Deprecated
    public void getRegistryPort(String newRegPort) {
        doc.getElementsByTagName("registryport").item(0).setTextContent(newRegPort);
    }

    @Deprecated
    public void setPolicyPath(String newPolPath) {
        doc.getElementsByTagName("policypath").item(0).setTextContent(newPolPath);
    }

    @Deprecated
    public void setUseCodeBaseOnly(String newCodeBase ) {
        doc.getElementsByTagName("usecodebaseonly").item(0).setTextContent(newCodeBase);
    }

    @Deprecated
    public void setClassProvider(String newCP) {
        doc.getElementsByTagName("classprovider").item(0).setTextContent(newCP);
    }

    private String splitString(String str){
        String[] strArray = str.split("\\.");
        return strArray[strArray.length-1];
    }

}
