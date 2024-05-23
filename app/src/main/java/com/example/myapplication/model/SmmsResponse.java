package com.example.myapplication.model;
import java.util.List;

import lombok.Data;
/*
* {
    "success": true,
    "code": "success",
    "message": "Get list success.",
    "data": [],
    "RequestId": "8A84DDCA-96B3-4363-B5DF-524E95A5201A"
}
* */
@Data
public class SmmsResponse {
    public boolean success;
    public String code;
    public String message;
    public List<SmmsData> data;
    public String requestId;

}
