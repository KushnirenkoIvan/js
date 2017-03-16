package com.mc.kushnirenko;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLProcessor {

    public static final Logger log = Logger.getLogger(RunApp.class);

    public static final Pattern PROTOCOL = Pattern.compile("^http://.+");
    public static final Pattern CONTENT_TYPE = Pattern.compile("^text/html.*");

    private static final Pattern OPEN_TAG = Pattern.compile("<(script|style|object)");
    private static final Pattern CLOSED_TAG = Pattern.compile("</(script|style|object)");

    private String closedTag = null;

    private int totalCnt = 0;

    private static boolean isReportsDirCreated = false;
    private String urlExForm;

    public void processResource(String arg) {
        log.trace("Start processing '" + arg + "'");

        arg = arg.trim();
        URL url = null;
        try {
            validateUrl(arg);
            url = new URL(arg);
        } catch (MalformedURLException e) {
            log.error("Passed url: '" + arg + "' is invalid:\n", e);
            return;
        }

        urlExForm = url.toExternalForm();
        Appender reporter = initFileReporter(url.getHost());

        URLConnection connection;
        try {
            connection = url.openConnection();
            log.trace("Successfully connected to the '" + urlExForm + "'");
        } catch (IOException e) {
            log.error("Cannot connect to the '" + urlExForm + "'\n", e);
            return;
        }

        try {
            validateContentType(connection.getContentType());
        } catch (IllegalContentTypeException e) {
            log.error("Given url: '" + urlExForm + "' hasn't HTML content!\n", e);
            return;
        } catch (NullPointerException e) {
            log.error("Cannot connect to the '" + urlExForm + "'\n", e);
            return;
        }

        String content = null;
        try {
            content = getContent(connection);
        } catch (IOException e) {
            log.error("Cannot obtain content from url: '" + urlExForm + "'\n", e);
            return;
        }

        processText(content);

        totalCnt = 0;
        log.removeAppender(reporter);
        urlExForm = null;
        closedTag = null;
    }

    private Appender initFileReporter(String logFileName) {
        if (!isReportsDirCreated) {
            File dir = new File("reports");
            if (!dir.exists()) dir.mkdir();
            isReportsDirCreated = true;
        }

        FileAppender fileApp = null;
        try {
            fileApp = new FileAppender(
                    new PatternLayout("%m%n"),
                    "reports/[" + logFileName + "].log",
                    false
            );
        } catch (IOException e) {
            log.error("Cannot create appender to logger. Application won't write processing results for url: "
                    + urlExForm + ".\n", e);
        }
        log.addAppender(fileApp);
        return fileApp;
    }

    private void validateUrl(String arg) throws MalformedURLException {
        Matcher m = PROTOCOL.matcher(arg);
        if (!m.matches()) throw new MalformedURLException();
    }

    private void validateContentType(String connType) throws IllegalContentTypeException {
        Matcher m = CONTENT_TYPE.matcher(connType);
        if (!m.matches()) throw new IllegalContentTypeException(connType);
    }

    private String getContent(URLConnection conn) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            line = removeUnwantedTagsInfo(line);

            if (line != null){
                sb.append(line.replaceAll("(<(/?[^\\\\>]+)>)", " "));
            }
        }
        bufferedReader.close();
        log.trace("Obtained content: \n" + sb.toString());

        return sb.toString();
    }

    private String removeUnwantedTagsInfo(String content) {
        if (closedTag != null) {
            Matcher m = CLOSED_TAG.matcher(content);
            if (m.find()) {
                content = content.substring(m.start(), content.length());
                closedTag = null;
            } else {
                return null;
            }
        }

        Matcher m1 = OPEN_TAG.matcher(content);
        if (m1.find()) {
            closedTag = m1.group(1);

            Pattern p2 = Pattern.compile("</" + closedTag);
            Matcher m2 = p2.matcher(content);
            if (m2.find()){
                closedTag = null;
                return content.substring(0, m1.start()) + content.substring(m2.end()+1, content.length());
            }  else {
                return content.substring(0, m1.start());
            }
        }
        return content;
    }

    private void processText(String test) {
        String[] words =
                test.split("([^a-zA-Zа-яА-ЯёЁ]+|[a-zA-Zа-яА-ЯёЁ]+[^a-zA-Zа-яА-ЯёЁ\\s]+[a-zA-Zа-яА-ЯёЁ]+)");

        HashMap<String, Integer> wordMap = new HashMap<>();

        for (String w : words) {
            if (w.equals("")) continue;
            if (wordMap.containsKey(w)) {
                wordMap.replace(w, wordMap.get(w) + 1);
            } else {
                wordMap.put(w, 1);
            }
        }

        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(wordMap.entrySet());

        log.info("Word processing results: ");
        sortedEntries.stream().sorted((e1, e2) -> {
            return e2.getValue().compareTo(e1.getValue());
        }).forEach(entry -> {
            log.info(entry.getKey() + " - " + entry.getValue() + " ");
            totalCnt += entry.getValue();
        });
        log.info("Total: " + totalCnt);
    }
}
