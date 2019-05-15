package com.atf.common;

import javax.validation.MessageInterpolator;
import java.util.Locale;

public class LocalizedMessageInterpolator implements MessageInterpolator {
    private MessageInterpolator defaultInterpolator;
    private Locale defaultLocale;

    public LocalizedMessageInterpolator(MessageInterpolator interpolator, Locale locale) {
        this.defaultInterpolator = interpolator;
        this.defaultLocale = locale;
    }

    @Override
    public String interpolate(String messageTemplate, Context context) {
        return defaultInterpolator.interpolate(messageTemplate, context, this.defaultLocale);
    }

    @Override
    public String interpolate(String messageTemplate, Context context, Locale locale) {
        return defaultInterpolator.interpolate(messageTemplate, context, locale);
    }
}
