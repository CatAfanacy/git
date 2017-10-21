package PO51.Golubcov.wdad.data.managers;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class PreferencesManager {

    private static volatile PreferencesManager instance;

    private final Document doc;
    private final String pathConfig = "src\\PO51\\Golubcov\\wdad\\resources\\configuration\\appconfig.xml";


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
        doc = docB.parse(new File(pathConfig));
    }

    public String getCreaterRegistry() {
        String registry = doc.getElementsByTagName("createregistry").item(0).getTextContent();
        return registry;
    }

    public String getRegistryAdress() {
        String regAd = doc.getElementsByTagName("registryaddress").item(0).getTextContent();
        return regAd;
    }

    public String getRegistryPort() {
        String regPort = doc.getElementsByTagName("registryport").item(0).getTextContent();
        return regPort;
    }
    public String getPolicyPath() {
        String polPath = doc.getElementsByTagName("policypath").item(0).getTextContent();
        return polPath;
    }
    public String getUseCodeBaseOnly() {
        String codeBase = doc.getElementsByTagName("usecodebaseonly").item(0).getTextContent();
        return codeBase;
    }
    public String getClassProvider() {
        String cP = doc.getElementsByTagName("classprovider").item(0).getTextContent();
        return cP;
    }

    public void setCreaterRegistry(String newRegistry) {
        doc.getElementsByTagName("createregistry").item(0).setTextContent(newRegistry);
    }

    public void getRegistryAdress(String newRegAd) {
        doc.getElementsByTagName("registryaddress").item(0).setTextContent(newRegAd);
    }

    public void getRegistryPort(String newRegPort) {
        doc.getElementsByTagName("registryport").item(0).setTextContent(newRegPort);
    }
    public void setPolicyPath(String newPolPath) {
        doc.getElementsByTagName("policypath").item(0).setTextContent(newPolPath);
    }
    public void setUseCodeBaseOnly(String newCodeBase ) {
        doc.getElementsByTagName("usecodebaseonly").item(0).setTextContent(newCodeBase);
    }
    public void setClassProvider(String newCP) {
        doc.getElementsByTagName("classprovider").item(0).setTextContent(newCP);
    }

}
