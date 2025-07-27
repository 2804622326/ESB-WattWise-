// services/api.js

/**
 * 模拟获取能耗统计（快速预览用）
 * @param {'home'|'community'} mode 
 */
export async function fetchEnergyStats(mode) {
  // 这里返回假数据，接真实接口时改成 fetch 调用即可
  return {
    used: 123.4,
    earned: 56,
    weekly: [
      { day: 'M', used: 10, earned: 30 },
      { day: 'T', used: 12, earned: 25 },
      { day: 'W', used:  8, earned: 20 },
      { day: 'T', used: 15, earned: 40 },
      { day: 'F', used: 20, earned: 50 },
      { day: 'S', used: 18, earned: 45 },
      { day: 'S', used: 22, earned: 55 },
    ],
  };
}

/**
 * 模拟加入挑战
 */
export async function joinChallenge() {
  // 真实场景下改为 POST /api/challenge/join
  return { success: true };
}