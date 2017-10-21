package PO51.Golubcov.wdad.learn.xml;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class xmlTask {

    private final File file;
    private Document doc;
    private String path;


    public xmlTask(String path) throws Exception {
        this.path = path;
        file = new File(this.path);
        DocumentBuilderFactory docBFac = DocumentBuilderFactory.newInstance();
        DocumentBuilder docB = docBFac.newDocumentBuilder();
        doc = docB.parse(file);
    }

    public int salaryAverage() {
        // Средняя зарплата 
        Element elNodeList;
        int salary =0;
        NodeList nodeList = doc.getElementsByTagName("employee");
        for (int i = 0; i< nodeList.getLength(); i++){
            elNodeList = (Element) nodeList.item(i);
            salary += Integer.parseInt(elNodeList.getElementsByTagName("salary").item(0).getTextContent());
        }
        return salary/nodeList.getLength();
    }

    public int salaryAverage(String departmentName){
        // Средняя зарплата отдела "departmentName"
        int salary=0;
        NodeList nodeList = doc.getElementsByTagName("department");
        NodeList listEmployees;
        Element elNodeList;
        for (int i = 0; i < nodeList.getLength() ; i++) {
            elNodeList = (Element) nodeList.item(i);
            if(elNodeList.getAttribute("name").equals(departmentName) ){
                listEmployees = elNodeList.getElementsByTagName("employee");
                for (int j = 0; j < listEmployees.getLength(); j++) {
                    elNodeList = (Element) listEmployees.item(i);
                    salary += Integer.parseInt(elNodeList.getElementsByTagName("salary").item(0).getTextContent());
                }
                return salary/listEmployees.getLength();
            }
        }
        return salary;
    }

    public void setJobTitle(String firstName, String secondName, String newJobTitle) throws TransformerException {
        // изменение должности сотрудника
        NodeList nodeList = doc.getElementsByTagName("employee");
        Element elNodeList;
        for (int i = 0; i < nodeList.getLength() ; i++) {
            elNodeList = (Element) nodeList.item(i);
            if(elNodeList.getAttribute("firstname").equals(firstName)&&
                    elNodeList.getAttribute("secondname").equals(secondName)){
                elNodeList.getElementsByTagName("jobtitle").item(0).setTextContent(newJobTitle);
                saveXML();
                break;
            }
        }
    }

    public void setSalary(String firstName, String secondName, int newSalary) throws TransformerException {
        // установление зарплаты
        NodeList nodeList = doc.getElementsByTagName("employee");
        Element elNodeList;
        for (int i = 0; i < nodeList.getLength() ; i++) {
            elNodeList = (Element) nodeList.item(i);
            if(elNodeList.getAttribute("firstname").equals(firstName)&&
                    elNodeList.getAttribute("secondname").equals(secondName)){
                elNodeList.getElementsByTagName("salary").item(0).setTextContent(Integer.toString(newSalary));
                saveXML();
                break;
            }
        }

    }

    public void fireEmployee(String firstName, String secondName) throws TransformerException {
        // уволнение работника
        NodeList nodeList = doc.getElementsByTagName("department");
        NodeList listEmployees;
        Element elNodeList;
        for (int i = 0; i < nodeList.getLength(); i++) {
            elNodeList = (Element) nodeList.item(i);
            listEmployees = elNodeList.getElementsByTagName("employee");
            for(int j = 0; j<listEmployees.getLength(); j++) {
                elNodeList = (Element) listEmployees.item(j);
                if ((elNodeList.getAttribute("firstname").equals(firstName) &
                        elNodeList.getAttribute("secondname").equals(secondName))) {
                    nodeList.item(i).removeChild(elNodeList);
                    saveXML();
                    break;
                }
            }
        }
    }


    private void saveXML() throws TransformerException {
        Transformer trans = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(path));
        trans.transform(source, result);
    }

}
