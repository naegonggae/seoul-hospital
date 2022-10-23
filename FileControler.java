package com.lion.domain;

import com.lion.domain.parser.Parser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileControler<T> { // parser인터페이스에 의존하게 됨 = str말고 다른거도 쓸수있게 해놓은거야
    Parser<T> parser;

    boolean isRemoveColumName = true;

    public FileControler(Parser<T> parser) {
        this.parser = parser;
    }

    public FileControler(Parser<T> parser, boolean isRemoveColumName) {
        this.parser = parser;
        this.isRemoveColumName = isRemoveColumName;
    }

    List<T> readLines(String filename) throws IOException {
        List<T> result = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
        //인코딩 이슈 해결방법인데 안됨
        //bufferedReader = Files.newBufferedReader(Paths.get(filename), StandardCharsets.UTF_8);
        String str;

        if (isRemoveColumName) {
            bufferedReader.readLine();
        }
        while ((str = bufferedReader.readLine()) != null) {
            //System.out.println(str);
            result.add(parser.parse(str));
        }
        return result;
    }
    public void createANewFile(String filename) throws IOException {
        File file = new File(filename);
        file.createNewFile();
        System.out.println("Have a file generated?:" + file.exists());
    }

    public void writeLines(List<String> lines, String filename) {
        File file = new File(filename);

        try {
            BufferedWriter writer
                    = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
            // 한줄씩 쓰는 부분
            for (String str : lines) {
                writer.write(str);
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("success");
    }
}
