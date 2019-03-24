package com.wakeparkby.HTTPController;

import com.google.gson.annotations.Expose;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
public class TimeSpace {
    @Expose
    private int start;
    @Expose
    private int end;
}
