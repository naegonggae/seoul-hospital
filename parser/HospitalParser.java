package com.lion.domain.parser;

import com.lion.domain.Hospital;

public class HospitalParser implements Parser<Hospital> {
    private String getSubdivision(String name) {
        String[] subdivisions = {"소아", "피부", "성형외", "정형외과",
                "산부인과", "관절", "안과", "가정의학과", "비뇨기과", "치과","내과", "외과"};
        for (String subdivirion : subdivisions) {
            if (name.contains(subdivirion)) {
                return subdivirion;
            }
        }
        return "";
    }
    @Override
    public Hospital parse(String str) {
        str = str.replaceAll("\"", "");
        String[] splitted = str.split(",");

        String name = splitted[10];
        String subdivision = getSubdivision(name);

        return new Hospital(splitted[0], splitted[1], splitted[2], Integer.parseInt(splitted[6]),
                name, subdivision);
    }
}
