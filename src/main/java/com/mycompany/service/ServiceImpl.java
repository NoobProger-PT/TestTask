package com.mycompany.service;

import com.mycompany.exceptions.inputData.InputDataException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ServiceImpl implements ServiceApp {
    @Override
    public String count(String str) {
        StringBuilder sb = new StringBuilder();
        Map<Character, Integer> map = new HashMap<>();

        if (str.equals("0")) {
            throw new InputDataException("Переданная строка пуста");
        }

        for (char c : str.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        map.entrySet()
                .stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .forEach(entry ->
                        sb.append("\"").append(entry.getKey()).append("\"").append(": ")
                                .append(entry.getValue()).append(", "));
        sb.setLength(sb.length() - 2);

        return sb.toString();
    }
}
