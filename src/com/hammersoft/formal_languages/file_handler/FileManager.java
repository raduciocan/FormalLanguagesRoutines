package com.hammersoft.formal_languages.file_handler;

import java.util.List;

public interface FileManager {
    boolean hasNextLine();
    String getNextLine();
    List<String> readFullFile();
}
