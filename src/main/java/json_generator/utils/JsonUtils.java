package json_generator.utils;

import net.hdt.neutronia.base.util.FileHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

public class JsonUtils {

    private static Logger LOGGER = LogManager.getLogger();

    public static void parseBiomeConfigs(File directory) {
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            Iterator<Path> pathIter = Files.walk(directory.toPath()).iterator();

            while (pathIter.hasNext()) {
                Path configPath = pathIter.next();
                File configFile = configPath.toFile();

                if (!FileHelper.getFileExtension(configFile).equals("json")) {
                    if (!configFile.isDirectory()) {
                        LOGGER.warn("Skipping file located at, {}, as it is not a json file.", configPath.toString());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
