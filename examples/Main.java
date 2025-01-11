package me.undeffineddev.libdataconfig.example;

import me.undeffineddev.libdataconfig.exceptions.InvalidFileException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        try {
            DConfParser config = new DConfParser("default.dconf", StandardCharsets.UTF_8);
            System.out.println(config.get("database", "url", "default"));
        } catch (InvalidFileException | RuntimeException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
