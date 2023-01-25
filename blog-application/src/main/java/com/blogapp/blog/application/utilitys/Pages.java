package com.blogapp.blog.application.utilitys;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class Pages {
    private Pages() {
    }
    public static Pageable getPageable(Integer pageNumber, Integer pageSize,String sortBy) {
        return PageRequest.of(pageNumber, pageSize,Sort.by(sortBy).ascending());
    }
}
