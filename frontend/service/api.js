import { BASE_URL } from '../config';

export async function fetchEnergyStats(mode, userId) {
  const url =
    mode === 'home'
      ? `${BASE_URL}/api/users/${userId}/summary`
      : `${BASE_URL}/api/users/community-summary`;
  const res = await fetch(url);
  if (!res.ok) throw new Error('Failed to fetch');
  const data = await res.json();
  return {
    used: data.monthlyEnergy,
    earned: data.totalPoints,
  };
}

export async function fetchCurrentUser(userId) {
  const res = await fetch(`${BASE_URL}/api/users/${userId}`);
  if (!res.ok) throw new Error('Failed to fetch user');
  return res.json();
}

export async function fetchLeaderboard(userId, period = 'daily') {
  const res = await fetch(`${BASE_URL}/api/users/${userId}/leaderboard/${period}`);
  if (!res.ok) throw new Error('Failed to fetch leaderboard');
  return res.json();
}

export async function fetchTasks() {
  const res = await fetch(`${BASE_URL}/api/tasks`);
  if (!res.ok) throw new Error('Failed to fetch tasks');
  return res.json();
}

export async function fetchRewards() {
  const res = await fetch(`${BASE_URL}/api/rewards`);
  if (!res.ok) throw new Error('Failed to fetch rewards');
  return res.json();
}

export async function completeTask(userId, taskId) {
  const res = await fetch(
    `${BASE_URL}/api/users/${userId}/complete-task/${taskId}`,
    { method: 'POST' }
  );
  if (!res.ok) throw new Error('Failed to complete task');
  return res.json();
}

export async function redeemReward(userId, rewardId) {
  const res = await fetch(
    `${BASE_URL}/api/users/${userId}/redeem/${rewardId}`,
    { method: 'POST' }
  );
  if (!res.ok) throw new Error('Failed to redeem reward');
  return res.text();
}
