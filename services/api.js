// services/api.js

const API_BASE_URL = process.env.API_URL || 'http://localhost:3000';

/**
 * 获取能耗统计数据
 * @param {'home'|'community'} mode
 */
export async function fetchEnergyStats(mode) {
  try {
    const res = await fetch(`${API_BASE_URL}/api/users/stats?mode=${mode}`);
    if (!res.ok) {
      throw new Error(`Request failed: ${res.status}`);
    }
    const data = await res.json();
    return data;
  } catch (err) {
    console.error('fetchEnergyStats error', err);
    throw err;
  }
}

/**
 * 模拟加入挑战
 */
export async function joinChallenge() {
  // 真实场景下改为 POST /api/challenge/join
  return { success: true };
}