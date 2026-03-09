package ru.ivk.utils.file;

import java.io.File;

public class FileCommonUtilities {
    protected static String getFilePath(String directory, String filename, FileExtensions extension) {
        return directory + File.separator + filename + "." + extension.getFormat();
    }
}
