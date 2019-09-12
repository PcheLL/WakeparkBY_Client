package com.wakeparkby.HTTPController;

import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
public class WsEventDto {
    private String objectType;
    private String eventType;
    private Booking body;
}
