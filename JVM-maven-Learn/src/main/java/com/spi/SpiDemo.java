package com.spi;

import java.util.ServiceLoader;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2021-01-11 11:03
 * @Version: 1.0
 */
public class SpiDemo {
    public static void main(String[] args) {
        ServiceLoader<SpiService> services = ServiceLoader.load(SpiService.class);
        services.forEach(s -> System.out.println(s.spiMethod()));
    }
}
