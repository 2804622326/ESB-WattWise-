import { BASE_URL } from '../config';

export async function fetchCurrentUser(userId) {
  const res = await fetch(`${BASE_URL}/api/users/${userId}`);
  if (!res.ok) throw new Error('Failed to fetch user');
  return await res.json();
}

export async function fetchLeaderboard(userId, period) {
  const res = await fetch(`${BASE_URL}/api/users/${userId}/leaderboard/${period}`);
  if (!res.ok) throw new Error('Failed to fetch leaderboard');
  return await res.json();
}

export async function fetchTasks() {
  const res = await fetch(`${BASE_URL}/api/tasks`);
  if (!res.ok) throw new Error('Failed to fetch tasks');
  return await res.json();
}

export async function fetchRewards() {
  const res = await fetch(`${BASE_URL}/api/rewards`);
  if (!res.ok) throw new Error('Failed to fetch rewards');
  return await res.json();
}

export async function fetchEnergyStats(mode, userId) {
  if (mode === 'home') {
    const user = await fetchCurrentUser(userId);
    return { used: user.monthlyEnergy, earned: user.totalPoints };
  }
  const res = await fetch(`${BASE_URL}/api/users/all`);
  if (!res.ok) throw new Error('Failed to fetch community stats');
  const allUsers = await res.json();
  return {
    used: allUsers.reduce((sum, u) => sum + (u.monthlyEnergy || 0), 0),
    earned: allUsers.reduce((sum, u) => sum + (u.totalPoints || 0), 0),
  };
}