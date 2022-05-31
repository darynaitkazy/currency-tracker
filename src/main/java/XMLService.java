import org.jvnet.hk2.annotations.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class XMLService {
    public String[] parseUSD() {
        String[] usd = new String[10];
        try {
            for (int days = 0; days < 11; days++) {
                LocalDate t = LocalDate.now();
                t = t.plusDays(-days);
                String today = t.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

                String url = "https://www.nationalbank.kz/rss/get_rates.cfm?fdate=" + today;
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(url);

                doc.getDocumentElement().normalize();

                NodeList nodelist = doc.getElementsByTagName("title");
                NodeList nodelist2 = doc.getElementsByTagName("description");
                NodeList nodelist3 = doc.getElementsByTagName("change");

                String firstParam = "";
                String secondParam = "";
                String thirdParam = "";
                firstParam = today;

                for (int id = 0; id < nodelist.getLength(); id++) {
                    Node node = nodelist.item(id);
                    Node node2 = nodelist2.item(id);
                    Node node3 = nodelist3.item(id);
                    if (node.getTextContent().equals("USD")) {
                        secondParam = node2.getTextContent();

                    }
                    if (node.getTextContent().equals("AED")) {
                        thirdParam = node3.getTextContent();
                    }

                }
                String res = firstParam + " " + secondParam + " " + thirdParam;
                usd[days] = res;


            }

        } catch (Exception ex) {

        }
        return usd;
    }

    public String[] parseEUR() {
        String[] eur = new String[10];
        try {
            for (int days = 0; days < 11; days++) {
                LocalDate t = LocalDate.now();
                t = t.plusDays(-days);
                String today = t.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

                String url = "https://www.nationalbank.kz/rss/get_rates.cfm?fdate=" + today;
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(url);

                doc.getDocumentElement().normalize();

                NodeList nodelist = doc.getElementsByTagName("title");
                NodeList nodelist2 = doc.getElementsByTagName("description");
                NodeList nodelist3 = doc.getElementsByTagName("change");

                String firstParam = "";
                String secondParam = "";
                String thirdParam = "";
                firstParam = today;

                for (int i = 0; i < nodelist.getLength(); i++) {
                    Node node = nodelist.item(i);
                    Node node2 = nodelist2.item(i);
                    Node node3 = nodelist3.item(i);
                    if (node.getTextContent().equals("EUR")) {
                        secondParam = node2.getTextContent();

                    }
                    if (node.getTextContent().equals("USD")) {
                        thirdParam = node3.getTextContent();
                    }
                }
                String res = firstParam + " " + secondParam + " " + thirdParam;
                eur[days] = res;


            }

        } catch (Exception ex) {

        }
        return eur;
    }

    public String[] parseRUB() {
        String[] rub = new String[10];
        try {
            for (int days = 0; days < 11; days++) {
                LocalDate t = LocalDate.now();
                t = t.plusDays(-days);
                String today = t.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

                String url = "https://www.nationalbank.kz/rss/get_rates.cfm?fdate=" + today;
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(url);

                doc.getDocumentElement().normalize();

                NodeList nodelist = doc.getElementsByTagName("title");
                NodeList nodelist2 = doc.getElementsByTagName("description");
                NodeList nodelist3 = doc.getElementsByTagName("change");

                String firstParam = "";
                String secondParam = "";
                String thirdParam = "";
                firstParam = today;

                for (int i = 0; i < nodelist.getLength(); i++) {
                    Node node = nodelist.item(i);
                    Node node2 = nodelist2.item(i);
                    Node node3 = nodelist3.item(i);
                    if (node.getTextContent().equals("RUB")) {
                        secondParam = node2.getTextContent();

                    }
                    if (node.getTextContent().equals("SAR")) {
                        thirdParam = node3.getTextContent();
                    }
                }
                String res = firstParam + " " + secondParam + " " + thirdParam;
                rub[days] = res;


            }

        } catch (Exception ex) {

        }
        return rub;
    }
}
