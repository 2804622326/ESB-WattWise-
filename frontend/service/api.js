import { BASE_URL } from '../config';

export async function fetchEnergyStats(mode, userId) {
  if (mode === 'home') {
    const res = await fetch(`${BASE_URL}/api/users/${userId}`);
    if (!res.ok) throw new Error('Failed to fetch');
    const data = await res.json();
    return { used: data.monthlyEnergy, earned: data.totalPoints };
  }

  const res = await fetch(`${BASE_URL}/api/users/all`);
  if (!res.ok) throw new Error('Failed to fetch');
  const list = await res.json();
  const used = list.reduce((sum, u) => sum + (u.monthlyEnergy || 0), 0);
  const earned = list.reduce((sum, u) => sum + (u.totalPoints || 0), 0);
  return { used, earned };
}

export async function fetchLeaderboard(type, userId) {
  const res = await fetch(`${BASE_URL}/api/users/${userId}/leaderboard/${type}`);
  if (!res.ok) throw new Error('Failed to fetch');
  return await res.json();
}

export async function fetchRewards() {
  const res = await fetch(`${BASE_URL}/api/rewards`);
  if (!res.ok) throw new Error('Failed to fetch');
  return await res.json();
}

export async function fetchTasks() {
  const res = await fetch(`${BASE_URL}/api/tasks`);
  if (!res.ok) throw new Error('Failed to fetch');
  return await res.json();
}

export async function fetchUser(userId) {
  const res = await fetch(`${BASE_URL}/api/users/${userId}`);
  if (!res.ok) throw new Error('Failed to fetch');
  return await res.json();
}