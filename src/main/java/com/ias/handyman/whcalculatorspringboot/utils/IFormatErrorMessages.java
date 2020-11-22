package com.ias.handyman.whcalculatorspringboot.utils;

import org.springframework.validation.BindingResult;

public interface IFormatErrorMessages {

    String formatMessage(BindingResult result);

}
