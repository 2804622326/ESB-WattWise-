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
  const url =
    mode === 'home'
      ? `${BASE_URL}/api/stats/home/${userId}`
      : `${BASE_URL}/api/stats/community`;
  const res = await fetch(url);
  if (!res.ok) throw new Error('Failed to fetch energy stats');
  return await res.json();
}