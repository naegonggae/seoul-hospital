package com.lion.domain;

import com.lion.domain.parser.HospitalParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        FileControler<Hospital> hospitalFileControler = new FileControler<>(new HospitalParser());
        String filename = "./hospital.csv";
        List<Hospital> hospitals = hospitalFileControler.readLines(filename);

        System.out.println(hospitals.size());
        List<String> lines = new ArrayList<>();
        for (Hospital hospital : hospitals) {
            lines.add(hospital.getSqlInsertQuery());
        }
        String sqlFilename = "insertSeoulHospital.sql";
        hospitalFileControler.createANewFile(sqlFilename);
        hospitalFileControler.writeLines(lines, sqlFilename);
    }
}
