package PO51.Golubcov.wdad.learn.xml;


public class TestXmlTask {

    public static void main(String[] args) throws Exception {
        xmlTask xmlDoc=new xmlTask("src\\PO51\\Golubcov\\wdad\\learn\\xml\\goodExample.xml"); //src\PO51\Golubcov\wdad\learn\xml
        System.out.println(xmlDoc.salaryAverage());
        System.out.println(xmlDoc.salaryAverage("Worker"));
        xmlDoc.setSalary("Mikhail","Sadykov",5000);
        xmlDoc.setJobTitle("Oleg","Dalmatincev","head");
        xmlDoc.fireEmployee("Kirill","Armeitsev");
    }

}
