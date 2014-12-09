package io.ahaitech.harmoney.dto;

import java.util.Date;

import lombok.Data;

@Data
public class RiskAnalysisResult extends RiskAnalysisToken {

	int status;

	Date updateTime;

}
