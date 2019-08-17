package com.wakeparkby.HTTPController;

import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
public class WsEventDto {
    private ObjectType objectType;
    private EventType eventType;
    private Booking body;
}
