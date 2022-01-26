package com.pressnews.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;

@Service
public class FileSystemStorageService {
    @Value("${files.directory}") // use only on production
    private String rootDirectory;
    private Path root;

    public Boolean store(MultipartFile file) {
        String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        if (filename.contains("..")) return false;

        try (InputStream is = file.getInputStream()) {
            Files.copy(is, this.root.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void deleteDirectory(String materialDirectory) {
        try {
            Path directory = Paths.get(rootDirectory + "/" + materialDirectory);
            if (Files.exists(directory)) {
                Files.walk(directory)
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Integer countKeyword(String modelDirectory, String filename, String keyword) {
        Integer count = 0;
        try {
            File file = Paths.get(rootDirectory + "/" + modelDirectory + "/" + filename).toFile();
            Scanner in = new Scanner(file);
            String line;
            while (in.hasNextLine()) {
                if (in.nextLine().contains(keyword)) {
                    count++;
                }
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void initialize(String materialDirectory) {
        try {
            if (!Files.exists(Paths.get(rootDirectory)))
                Files.createDirectory(Paths.get(rootDirectory));

            root = Paths.get(rootDirectory + "/" + materialDirectory);

            if (!Files.exists(root))
                Files.createDirectory(root);

        } catch (IOException e) {
            System.out.println("Couldn't initialize directory");
            e.printStackTrace();
        }
    }

}
