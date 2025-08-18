package ru.otus.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum KeyWords {

    ACTION("действе"),
    SCOPE("область"),
    EXECUTE("выполнить"),
    OCB("{"),
    CCB("}"),
    OB("("),
    CB(")"),
    SEMI_COLON(";"),
    COMMA(","),
    QUOTE("\"");

    private String word;

    public final static String ALPHABET = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя ().,:;-0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";


    public static Set<String> getKeyWords() {
        Set<String> keyWord = Arrays.stream(values()).map(KeyWords::getWord).collect(Collectors.toCollection(TreeSet::new));
        for (char ch : ALPHABET.toCharArray()) {
            keyWord.add(String.valueOf(ch));
        }
        return keyWord;
    }
}
