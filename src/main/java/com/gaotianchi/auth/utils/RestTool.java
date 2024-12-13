package com.gaotianchi.auth.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaotianchi
 * @since 2024/12/13 9:00
 **/
public class RestTool {
    public static PageRequest getPageRequest(int page, int size, List<String> sorts) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String sort : sorts) {
            String[] sortParts = sort.split(":");
            if (sortParts.length == 2) {
                String field = sortParts[0].trim();
                String direction = sortParts[1].trim().toUpperCase();

                if ("DESC".equals(direction)) {
                    orders.add(Sort.Order.desc(field));
                } else {
                    orders.add(Sort.Order.asc(field));
                }
            }
        }
        Sort sort = Sort.by(orders);
        return PageRequest.of(page, size, sort);
    }
}
