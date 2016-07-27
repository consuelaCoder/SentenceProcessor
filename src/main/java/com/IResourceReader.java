package com;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Reads from resource files.
 */
public interface IResourceReader {
    /**
     * Provides a buffered reader from a file.
     *
     * @param fileName - A file name.
     * @return {BufferedReader} - A buffered reader.
     * @method
     */
    String getResourceContent(String fileName) throws IOException;
}
