package com.esb.esbapp.service;

import com.esb.esbapp.dto.CommunitySummaryDTO;
import com.esb.esbapp.dto.UserSummaryDTO;

public interface HomeService {
    UserSummaryDTO getUserSummary(Long userId);
    CommunitySummaryDTO getCommunitySummary();
}
