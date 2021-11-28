package com.hammersoft.formal_languages.file_handler;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileHandler implements FileManager {
    private String filePath;
    private boolean isValid;
    private List<String> lines;
    private Iterator<String> lineNumber;
    public boolean isValid() {
        return isValid;
    }

    public FileHandler(String filePath) {
        setFilePath(filePath);
        openFile();
        if(isValid)
            lineNumber = lines.listIterator();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        if (!filePath.isEmpty())
            this.filePath = filePath;
    }

    public void openFile() {
        lines = readFullFile();
    }

    public boolean hasNextLine() {
        if(!isValid)
            return false;
        return lineNumber.hasNext();
    }

    public String getNextLine() {
        if (lineNumber.hasNext())
            return lineNumber.next();
        return null;
    }

    public List<String> readFullFile() {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
            if(lines.size() > 0)
                isValid = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }
}
