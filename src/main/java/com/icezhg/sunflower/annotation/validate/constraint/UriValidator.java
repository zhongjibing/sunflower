package com.icezhg.sunflower.annotation.validate.constraint;

import com.icezhg.sunflower.annotation.validate.Uri;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by zhongjibing on 2022/12/25.
 */
public class UriValidator implements ConstraintValidator<Uri, String> {
    private String delimiter;

    @Override
    public void initialize(Uri uri) {
        this.delimiter = uri.delimiter();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        List<String> checkingValues = delimiter.isEmpty() ? List.of(value) : List.of(value.split(delimiter));
        return checkingValues.stream().allMatch(this::validateRedirectUri);
    }

    private boolean validateRedirectUri(String redirectUri) {
        try {
            URI validRedirectUri = new URI(redirectUri);
            return validRedirectUri.getFragment() == null;
        } catch (URISyntaxException ex) {
            return false;
        }
    }
}
