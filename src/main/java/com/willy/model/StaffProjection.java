package com.willy.model;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "mobileAndName", types = {Staff.class})
public interface StaffProjection {
    String getposition();
}
