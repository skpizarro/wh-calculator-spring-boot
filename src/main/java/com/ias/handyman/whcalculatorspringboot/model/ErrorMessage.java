package com.ias.handyman.whcalculatorspringboot.model;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;

@Getter
@Setter

public class ErrorMessage {
    private String code;
    private List<Map<String, String>> messages;

    public ErrorMessage(String code, List<Map<String, String>> messages) {
        this.code = code;
        this.messages = messages;
    }
}
