package com.ias.handyman.whcalculatorspringboot.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ias.handyman.whcalculatorspringboot.model.ErrorMessage;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class FormatErrorMessages implements IFormatErrorMessages{


    @Override
    public String formatMessage(BindingResult result) {

        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(err -> { // mapeamos la lista de errores
                    Map<String, String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage()); // obtenemos el tipo de error y el mensaje
                    return error;
                }).collect(Collectors.toList()); // como retorna un string toca convertirlo a una lista

        ErrorMessage errorMessage = new ErrorMessage("01",errors);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        try {
            json = objectMapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }

}
