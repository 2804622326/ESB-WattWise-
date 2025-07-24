package com.esb.esbapp.service.userService;

public interface IUserService {
    User getUserProfile(Long userId);
    List<DailyPointDTO> getUserPointTrend(Long userId);
    List<DailyEnergyDTO> getUserEnergyTrend(Long userId);
    List<RewardRecord> getRewardHistory(Long userId);
    UserRankingInfo getUserRankingInfo(Long userId);
}
