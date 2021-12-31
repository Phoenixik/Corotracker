package services;


import model.location;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class coronavirusdataservice {

    private static String url = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    private List<location> allstats = new ArrayList<>();

    public List<location> getAllstats() {
        return allstats;
    }

    public void setAllstats(List<location> allstats) {
        this.allstats = allstats;
    }

    //execute method after creating this class
    @PostConstruct
    @Scheduled(cron = "* 1 * * * *")
    public void fetchdata() throws IOException, InterruptedException {




        List<location> newstats = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url)).build();
        System.out.println("done");
        HttpResponse<String> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        StringReader csvbodyreader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvbodyreader);
        for (CSVRecord record : records) {
            location ll = new location();
            ll.setState(record.get("Province/State"));
            ll.setCountry(record.get("Country/Region"));
            int latestcases = Integer.parseInt(record.get(record.size() - 1));
            int prevdaycases = Integer.parseInt(record.get(record.size() - 2));
            ll.setLatestconfirmedcases(latestcases);
            ll.setDiffdaycases(latestcases - prevdaycases);
//            System.out.println(ll);
            newstats.add(ll);
        }
        this.allstats = newstats;
    }
}
