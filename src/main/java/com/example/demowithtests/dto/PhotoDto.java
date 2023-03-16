package com.example.demowithtests.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Name of an employee.", example = "Billy", required = true)
    public String linkPhoto;
    @Schema(description = "Name of an employee.", example = "Billy", required = true)
    public Integer xHigh;
    @Schema(description = "Name of an employee.", example = "Billy", required = true)
    public Integer yWidth;
    @Schema(description = "Name of an employee.", example = "Billy", required = true)
    public Date createDate = Date.from(Instant.now());
    @Schema(description = "Name of an employee.", example = "Billy", required = true)
    public Boolean isVisible = Boolean.TRUE;
}


