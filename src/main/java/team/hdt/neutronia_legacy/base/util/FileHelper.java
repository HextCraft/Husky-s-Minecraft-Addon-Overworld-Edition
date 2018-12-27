package team.hdt.neutronia_legacy.base.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.*;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FileHelper {

    public static void copyDirectoryToDirectory(URL sourceDirectory, File destinationDirectory) {
        String sourcePath = sourceDirectory.getPath();

        if (Reference.IS_DEV_ENV) {
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
        } else {
            if (sourceDirectory.getProtocol().equals("jar")) {
                sourcePath = sourcePath.substring(sourcePath.indexOf(".jar!/") + 6);

                try {
                    ZipFile zipFile = ((JarURLConnection) sourceDirectory.openConnection()).getJarFile();
                    Enumeration<? extends ZipEntry> zipEntries = zipFile.entries();

                    while (zipEntries.hasMoreElements()) {
                        ZipEntry zipEntry = zipEntries.nextElement();
                        String zipName = zipEntry.getName();

                        if (!zipName.contains(sourcePath)) {
                            continue;
                        }

                        File file = new File(destinationDirectory, zipName.substring(sourcePath.length()));

                        if (!file.exists()) {
                            if (zipEntry.isDirectory()) {
                                file.mkdirs();
                            } else {
                                copyFile(zipFile.getInputStream(zipEntry), new BufferedOutputStream(new FileOutputStream(file)));
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
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