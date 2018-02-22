
package com.tarkmhomas.prosper1099;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.pdfbox.io.RandomAccessBuffer;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Provides HTTP endpoints that can be called by external applications.
 */
@Controller
public class ConverterController {

    private final DocumentParser documentParser;
    private final Prosper1099BTransactionFinder recordFinder;


    @Autowired
    ConverterController(DocumentParser documentParser, Prosper1099BTransactionFinder recordFinder) {
        this.documentParser = documentParser;
        this.recordFinder = recordFinder;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/convertPdfToCsv", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "text/csv")
    public String convertPdfToCsv(@RequestParam("file") MultipartFile pdfFile,
                                  @RequestParam(name = "includeShortTerm", defaultValue = "true") boolean includeShortTerm,
                                  @RequestParam(name = "includeLongTerm", defaultValue = "true") boolean includeLongTerm)
            throws IOException {

        PDFParser pdfParser = new PDFParser(new RandomAccessBuffer(pdfFile.getInputStream()));

        pdfParser.parse();

        List<String> lines;
        try (PDDocument pdDocument = pdfParser.getPDDocument()) {
            lines = documentParser.parseDocument(pdDocument);
        }

        List<List<String>> transactions = recordFinder.find1099BTransactions(lines, includeShortTerm, includeLongTerm);

        return generateCsv(transactions);
    }

    private String generateCsv(List<List<String>> csvRecords) throws IOException {

        StringBuilder out = new StringBuilder();
        CSVPrinter printer = new CSVPrinter(out,
                CSVFormat.DEFAULT.withHeader("Date Sold", "Date Acquired", "Sales Proceeds", "Description", "Cost", "Reporting Category"));
        printer.printRecords(csvRecords);

        return out.toString();
    }
}
