package ru.freedomfinance.service;

import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;

@Component
public interface XMLServiceInterface {
    void parseUSD();
}
