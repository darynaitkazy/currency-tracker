package ru.freedomfinance.service;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.freedomfinance.model.Eur;
import ru.freedomfinance.model.Rub;
import ru.freedomfinance.model.Usd;
import ru.freedomfinance.repos.EurRepository;
import ru.freedomfinance.repos.RubRepository;
import ru.freedomfinance.repos.UsdRepository;

import javax.transaction.Transactional;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Repository
public class XMLService implements XMLServiceInterface{

    @Autowired
    private UsdRepository usdRepository;

    @Autowired
    private EurRepository eurRepository;

    @Autowired
    private RubRepository rubRepository;

    @Override
    public void parseUSD() {
        try {
            //for (int days = 9; days >= 0; days--) {
                LocalDate t = LocalDate.now();
                //t = t.plusDays(-days);
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
                int cnt = usdRepository.countByDate(firstParam);
                if (cnt == 0) {
                    Usd usd1 = new Usd(firstParam, secondParam, thirdParam);
                    usdRepository.save(usd1);
                }
            //}

        } catch (Exception ex) {

        }
    }

    public void parseEUR() {
        try {
            //for (int days = 9; days >= 0; days--) {
                LocalDate t = LocalDate.now();
                //t = t.plusDays(-days);
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
                int cnt = eurRepository.countByDate(firstParam);
                if (cnt == 0) {
                    Eur eur1 = new Eur(firstParam, secondParam, thirdParam);
                    eurRepository.save(eur1);
                }

            //}

        } catch (Exception ex) {

        }
    }

    public void parseRUB() {
        try {
            //for (int days = 9; days >= 0; days--) {
                LocalDate t = LocalDate.now();
                //t = t.plusDays(-days);
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
                int cnt = rubRepository.countByDate(firstParam);
                if (cnt == 0) {
                    Rub rub1 = new Rub(firstParam, secondParam, thirdParam);
                    rubRepository.save(rub1);
                }
            //}

        } catch (Exception ex) {

        }
    }
}
