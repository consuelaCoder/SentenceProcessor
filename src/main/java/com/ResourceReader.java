package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * Reads from resource files.
 */
public class ResourceReader implements IResourceReader {
    /**
     * Provides a buffered reader from a file.
     *
     * @param fileName - A file name.
     * @return {BufferedReader} - A buffered reader.
     * @method
     */
    public String getResourceContent(String fileName) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        return bufferedReader.lines().collect(Collectors.joining("\n"));
    }
}

