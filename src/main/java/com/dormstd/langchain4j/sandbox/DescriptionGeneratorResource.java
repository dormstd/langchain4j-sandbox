package com.dormstd.langchain4j.sandbox;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;

@Path("/")
public class DescriptionGeneratorResource {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(DescriptionGeneratorResource.class);

    private final DescriptionGeneratorService blogReaderService;

    private final WebCrawler webCrawler;

    private final RequestSplitter requestSplitter;

    @Inject
    public DescriptionGeneratorResource(DescriptionGeneratorService blogReaderService, WebCrawler webCrawler, RequestSplitter requestSplitter) {
        this.blogReaderService = blogReaderService;
        this.webCrawler = webCrawler;
        this.requestSplitter = requestSplitter;
    }

    @Path("/read")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String read(String url) {
        // Read the HTML from the specified URL
        //String content = webCrawler.crawl(url);

        LOGGER.info("\uD83D\uDD1C Preparing analysis of {}", url);

        // Prepare the model
        String columnList = blogReaderService.getColumns(url);

        String sumUp = blogReaderService.getDescription(url, columnList);

        // Split the HTML into small pieces
        /*List<String> split = requestSplitter.split(content);

        // Send each piece of HTML to the LLM
        for (int i = 0; i < split.size(); i++) {
            blogReaderService.sendBody(split.get(i));
            LOGGER.info("\uD83E\uDDD0 Analyzing article... Part {} out of {}.", (i + 1), split.size());
        }

        LOGGER.info("\uD83D\uDCDD Preparing response...");

        // Ask the model to sum up the article
        String sumUp = blogReaderService.sumUp();

        LOGGER.info("✅ Response for {} ready", url);

        // Return the result to the user

         */
        return sumUp;
    }
}