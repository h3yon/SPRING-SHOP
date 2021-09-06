package com.sparta.springcore.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@NoArgsConstructor
@Getter
public class ItemDto {
    private String title; // 관심상품명
    private String link; // 관심상품 링크
    private String image; // 관심상품 이미지
    private int lprice; // 관심상품 가격

    public ItemDto(JSONObject itemJson) {
        this.title = itemJson.getString("title");
        this.link = itemJson.getString("link");
        this.image = itemJson.getString("image");
        this.lprice = itemJson.getInt("lprice");
    }
}