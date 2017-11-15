package crawler;

import javax.naming.NamingException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import data.Car;

/**
 * Created by tomas on 09/10/2017.
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;



public class Crawler {
    private List<String> pages;
    private List <String> adverts;
    private List <List<String>> advertDetails;


    public Crawler() {
        pages = new ArrayList<>();
        adverts = new ArrayList <>();
        advertDetails = new ArrayList <>();
    }

    // Gets all the 'destaques' web pages
    public void getWebPages(String URL) {
        pages.add(URL);
        try {
            Document document = Jsoup.connect(URL).get();
            Elements otherPages = document.select("a[href^=\"https://www.standvirtual.com/destaques/?page=\"]");

            for (Element page : otherPages) {
                String pageHref= page.attr("abs:href");
                if (!pages.contains(pageHref)) {
                    pages.add(pageHref);
                }
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        System.out.println(pages);

    }

    //Connect to each 'destaques' page get all car advert links
    public void getAdvertLink() {
        pages.forEach(page -> {
            Document document;
            try {
                document = Jsoup.connect(page).get();
                Elements advertBoxes = document.getElementsByClass("rel   img-cover");
                for (Element advert : advertBoxes) {
                    String advertHref = advert.attr("abs:href");
                    if (!adverts.contains(advertHref)) {
                        adverts.add(advertHref);
                    }
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        });
        System.out.println(adverts);
    }

    //Connect to each advert and scrape the details
    public void getAdvertDetails() {
        adverts.forEach(advert -> {
            Document document;
            try {
                document = Jsoup.connect(advert).get();
                Car car = new Car();

                Elements advertDetails = document.getElementsByClass("offer-params__item");
                Elements advertExtras = document.getElementsByClass("offer-features__item");
                String advertID = document.getElementsByClass("offer-meta__value").last().text();
                String advertPrice = document.getElementsByClass("offer-price__number").text();
                String imageUrl = document.getElementsByClass("offer-photos-thumbs__link").first().attr("abs:data-thumb");
                car.setPrice(Integer.parseInt(advertPrice.replaceAll("[^\\d]", "")));

                car.setImageUrl(imageUrl);

                for (Element detail : advertDetails) {
                    Elements children = detail.children();
                    String detailKey = children.first().text();
                    String detailValue = children.last().text();

                    if (detailKey.equals("Marca")) {
                        car.setBrand(detailValue);
                    } else if (detailKey.equals("Modelo")) {
                        car.setModel(detailValue);
                    } else if (detailKey.equals("Mês de Registo")) {
                        car.setMonth(detailValue);
                    } else if (detailKey.equals("Ano de Registo")) {
                        car.setYear(Integer.parseInt(detailValue));
                    } else if (detailKey.equals("Quilómetros")) {
                        car.setMileage(Integer.parseInt(detailValue.replaceAll("[^\\d]", "")));

                    }
                }

                sendCars(car);

            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        });
    }

    public static void main(String[] args) {

        Crawler crawler = new Crawler();
        crawler.getWebPages("https://www.standvirtual.com/destaques/");
        crawler.getAdvertLink();
        crawler.getAdvertDetails();

        System.out.println("Crawler Terminated");
    }

    public static void sendCars(Car car) {
        try {

            TopicSender sender = new TopicSender();
            sender.sendToTopic(car);
            System.out.println("Message sent");
        } catch(NamingException ne) {
            System.out.println("Error sending Car");
            System.err.println(ne);
        }
    }
}
