package ru.otus.dsl.core;

import com.google.common.io.Resources;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

@Component
public class ScriptFileScanner {
    private final String scriptsDir;

    public ScriptFileScanner(@Value("${dsl.scripts.dir}")String scriptsDir) {
        this.scriptsDir = scriptsDir;
    }

    public List<File> getFilesFromResources() {
        try {
            URL dir = Resources.getResource(this.getClass(), scriptsDir);
            File folder = new File(dir.getFile());
            if(folder.isDirectory()) {
                File[] files = folder.listFiles();
                return Arrays.asList(files);
            } else {
                throw new RuntimeException("Неверно указана директория со скриптами");
            }
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при получении списка файлов", e);
        }
    }
}
