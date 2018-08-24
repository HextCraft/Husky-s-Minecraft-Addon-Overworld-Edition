package json_generator.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.*;
import java.net.URL;

public class FileHelper {

    public static void copyDirectoryToDirectory(URL sourceDirectory, File destinationDirectory) {
        String sourcePath = sourceDirectory.getPath();

        if (sourceDirectory.getProtocol().equals("file")) {
            sourcePath = sourcePath.substring(1);

            for (File file : FileUtils.listFilesAndDirs(new File(sourceDirectory.getFile()), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE)) {
                File destinationFile = new File(destinationDirectory, file.getPath().substring(sourcePath.length()));

                if (!destinationFile.exists()) {
                    if (getFileExtension(destinationFile).equals("")) {
                        destinationFile.mkdirs();
                    } else {
                        copyFile(file, destinationFile);
                    }
                }
            }
        }
    }

    public static String getFileExtension(File file) {
        int dotIndex = file.getName().lastIndexOf('.');
        return dotIndex == -1 ? "" : file.getName().substring(dotIndex + 1);
    }

    private static void copyFile(InputStream inputStream, OutputStream outputStream) {
        try {
            IOUtils.copy(inputStream, outputStream);
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(outputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copyFile(File sourceFile, File destinationFile) {
        try {
            copyFile(new FileInputStream(sourceFile), new BufferedOutputStream(new FileOutputStream(destinationFile)));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}