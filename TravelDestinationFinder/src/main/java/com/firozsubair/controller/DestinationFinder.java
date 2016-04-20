/**
 * Represents DestinationFinder class for displaying city details.
 *
 * Bugs: none known
 *
 * @author       Firoz Subair
 * @version      1.0
 * @see also     
 */
package com.firozsubair.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.firozsubair.model.DestinationDTO;
import com.firozsubair.util.ErrorConstants;

@SpringBootApplication
public class DestinationFinder implements CommandLineRunner {
    // http://stackoverflow.com/jobs/107469/senior-software-engineer-lead-developer-java-goeuro-travel-gmbh?offset=8

    private static final Logger log = LoggerFactory.getLogger(DestinationFinder.class);
    private final static String url = "http://api.goeuro.com/api/v2/position/suggest/en/";
    private final static String fileName = "Destination.csv";
    private static String city = null;

    public static void main(String args[]) {
        log.info("DestinationFinder Application Start");
        if (args.length == 0) {
            System.out.println("Please Enter the City");
            System.exit(1);
        } else if (args.length > 1) {
            System.out.println("Wrong Input.Please Enter the City");
            System.exit(1);
        } else {
            city = args[0];
            System.out.println("Detailed log location : \"d:/traveldestinationfinder.log\"");
            log.info("Selected City: " + city);
        }
        SpringApplication.run(DestinationFinder.class);
        log.info("DestinationFinder Application End");
    }

    @Override
    public void run(String... args) throws Exception {

        writeCSVData(Arrays.asList(retrieveDestinationData(city)));
    }

    /**
     * Retrieve destination date.
     *
     * @param city the city
     * @return the destinationDTO[]
     * @throws Exception 
     */
    private static DestinationDTO[] retrieveDestinationData(String city) throws Exception {
        log.info("JSON Webservice Call Start");
        RestTemplate restTemplate1 = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        restTemplate1.setMessageConverters(messageConverters);
        DestinationDTO[] destinationArray = null;
        try {
            destinationArray = restTemplate1.getForObject(url + city, DestinationDTO[].class);
        } catch (Exception exp) {
            log.error(ErrorConstants.ERROR_JSON_CALL + exp.getMessage());
            throw new Exception(ErrorConstants.ERROR_JSON_CALL);
        }
        log.info("Data size on response :" + destinationArray.length);
        return destinationArray;
    }

    /**
     * Write csv data.
     *
     * @param destinationDTOs the destination dt os
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private static void writeCSVData(List<DestinationDTO> destinationDTOs) throws Exception {
        String workingDirectory = System.getProperty("user.dir");
        String fileFullName = workingDirectory + "\\" + fileName;
        deleteFile(fileFullName);
        if (!destinationDTOs.isEmpty()) {
            log.info("CSV writing started");

            try (ICsvBeanWriter beanWriter = new CsvBeanWriter(new FileWriter(fileName), CsvPreference.STANDARD_PREFERENCE)) {
                final String[] header = new String[] { "_id", "name", "type", "latitude", "longitude" };
                final CellProcessor[] processors = getProcessors();

                // write the header
                beanWriter.writeHeader(header);

                // write the content
                destinationDTOs.stream().forEach(dto -> {
                    try {
                        beanWriter.write(dto, header, processors);
                    } catch (Exception e) {
                        log.error(ErrorConstants.ERROR_CSV_WRITING + e.getLocalizedMessage());
                    }

                });
                log.info(ErrorConstants.SUCCESS_CSV_WRITING + "Please find the file in " + fileFullName + " location.");
            } catch (Exception ex) {
                log.error(ErrorConstants.ERROR_CSV_WRITING + ex.getLocalizedMessage());
                throw new Exception(ErrorConstants.ERROR_CSV_WRITING);
            }
            ;
        } else {
            log.info(ErrorConstants.INFO_NO_DATA);
        }
    }

    private static CellProcessor[] getProcessors() {

        final CellProcessor[] processors = new CellProcessor[] { new Optional(), new Optional(), new Optional(), new Optional(), new Optional() };
        return processors;
    }

    private static void deleteFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            Files.delete(path);
            log.info(ErrorConstants.FILE_DELETED);
        }

    }

}