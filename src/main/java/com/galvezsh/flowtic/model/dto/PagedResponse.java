package com.galvezsh.flowtic.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagedResponse<T> {

    private PageInfo info;
    private List<T> results;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PageInfo {
        private long count;   // total items
        private int pages;    // total pages
        private String next;  // URL of next page or null
        private String prev;  // URL of previous page or null
    }
}
