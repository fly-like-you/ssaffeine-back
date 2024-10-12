package com.ssaffeine.ssaffeine.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Region {
    E001("서울"),
    E002("대전"),
    E003("광주"),
    E004("구미"),
    E005("부울경");

    private final String regionName;
}
