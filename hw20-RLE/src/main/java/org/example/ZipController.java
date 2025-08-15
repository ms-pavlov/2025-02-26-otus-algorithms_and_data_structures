package org.example;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.IOException;

@ShellComponent
@RequiredArgsConstructor
public class ZipController {
    private final ZipService zipService;

    @ShellMethod(value = "Zip file", key = {"z", "zip"})
    public void zip(@ShellOption(value={"s", "source"}) String source, @ShellOption String to) throws IOException {
        zipService.zip(source, to);
    }

    @ShellMethod(value = "UnZip file", key = {"u", "unzip"})
    public void unZip(@ShellOption(value={"s", "source"}) String source, @ShellOption(value={"d", "dir"}) String dir) throws IOException {
        zipService.unZip(source, dir);
    }
}
