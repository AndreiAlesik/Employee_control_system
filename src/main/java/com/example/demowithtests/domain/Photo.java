package com.example.demowithtests.domain;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@ToString
@Entity
@Table(name = "photos")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "link_photo")
    private String linkPhoto;
    @Column(name = "x_high")
    private Integer xHigh;
    @Column(name = "y_width")
    private Integer yWidth;
    @Column(name = "create_date")
    private Date createDate = Date.from(Instant.now());
    @Column(name = "is_visible")
    private Boolean isVisible = Boolean.TRUE;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLinkPhoto() {
        return linkPhoto;
    }

    public void setLinkPhoto(String linkPhoto) {
        this.linkPhoto = linkPhoto;
    }

    public Integer getXHigh() {
        return xHigh;
    }

    public void setXHigh(Integer xHigh) {
        this.xHigh = xHigh;
    }

    public Integer getYWidth() {
        return yWidth;
    }

    public void setyWidth(Integer yWidth) {
        this.yWidth = yWidth;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Boolean getIsVisible() {
        return isVisible;
    }

    public void setVisible(Boolean visible) {
        isVisible = visible;
    }
}

