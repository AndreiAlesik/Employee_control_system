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
    @Schema(description = "Link of photo.", example = "http//...", required = true)
    public String linkPhoto;
    @Schema(description = "Photo high.", example = "640", required = true)
    public Integer xHigh;
    @Schema(description = "Photo width.", example = "700", required = true)
    public Integer yWidth;
    @Schema(description = "Date of uploading.", example = "2020-01-01", required = true)
    public Date createDate ;
    @Schema(description = "Status of visibility.", example = "True|False", required = true)
    public Boolean isVisible;
}


