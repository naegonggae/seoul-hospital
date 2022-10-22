package com.lion.domain;

import com.lion.domain.parser.Parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LineReader<T> { // parser인터페이스에 의존하게 됨 = str말고 다른거도 쓸수있게 해놓은거야
    Parser<T> parser;

    boolean isRemoveColumName = true;

    public LineReader(Parser<T> parser) {
        this.parser = parser;
    }

    public LineReader(Parser<T> parser, boolean isRemoveColumName) {
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
}
