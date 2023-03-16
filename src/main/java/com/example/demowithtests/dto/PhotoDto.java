package com.example.demowithtests.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class PhotoDto {
    public String linkPhoto;
    public Integer xHigh;
    public Integer yWidth;
    public Date createDate = Date.from(Instant.now());

    public Boolean isVisible = Boolean.TRUE;
}


