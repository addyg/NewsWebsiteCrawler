import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class Controller {

    private static final String website = "usatoday https://www.usatoday.com";

    private static final int numberOfCrawlers   = 7;
    private static final int maxDepthOfCrawling = 16;
    private static final int maxPagesToFetch    = 20000;
    private static final int politenessDelay    = 2;
    private static final String userAgentString = "2";

    public static void main(String[] args) throws Exception {

        String crawlStorageFolder = "/Users/adityagupta/Documents/00 USC Courses/09 CSCI 572 - IR/02 HW - 2";

        CrawlConfig crawlConfig = new CrawlConfig();
        crawlConfig.setCrawlStorageFolder(crawlStorageFolder);

//        crawlConfig.setMaxDepthOfCrawling(maxDepthOfCrawling);
//        crawlConfig.setMaxPagesToFetch(maxPagesToFetch);
//        crawlConfig.setPolitenessDelay(politenessDelay);
//        crawlConfig.setUserAgentString(userAgentString);

        /*
         * Instantiate the controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher(crawlConfig);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(crawlConfig, pageFetcher, robotstxtServer);

        /*
         * For each crawl, you need to add some seed urls. These are the first
         * URLs that are fetched and then the crawler starts following links
         * which are found in these pages
         */
        controller.addSeed(website);

        /*
         * Start the crawl. This is a blocking operation, meaning that your code
         * will reach the line after this only when crawling is finished.
         */
        controller.start(Crawler.class, numberOfCrawlers);
    }
}