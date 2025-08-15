package org.example;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@Service
public class ZipService {

    private final UnZip unZip = new RLEOptimization();

    public void zip(String source, String to) throws IOException {
        File sourceFile = new File(source);
        File toFile = new File(to);
        if (!toFile.exists()) {
            toFile.createNewFile();
        }

        String fileName = sourceFile.getName();

        String text = FileUtils.readFileToString(sourceFile, "UTF-8");

        String zip = unZip.zip(text);

        FileUtils.writeStringToFile(toFile, ((char) fileName.length()) + fileName + zip, "UTF-8");
    }

    public void unZip(String source, String dir) throws IOException {
        File sourceFile = new File(source);
        String text = FileUtils.readFileToString(sourceFile, "UTF-8");

        int nameLen = text.charAt(0);

        String name = text.substring(1, nameLen + 1);

        File file = Path.of(dir, name).toFile();

        String unzip = unZip.unzip(text.substring(nameLen +1));

        FileUtils.writeStringToFile(file, unzip, "UTF-8");
    }
}
