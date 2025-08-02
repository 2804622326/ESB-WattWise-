import { BASE_URL } from '../config';

async function handleResponse(res) {
  if (!res.ok) {
    throw new Error('Failed to fetch');
  }
  return res.json();
}

export async function fetchUserSummary(userId) {
  const res = await fetch(`${BASE_URL}/api/users/${userId}/summary`);
  return handleResponse(res);
}

export async function fetchCommunitySummary() {
  const res = await fetch(`${BASE_URL}/api/users/community-summary`);
  return handleResponse(res);
}

export async function fetchEnergyStats(mode, userId) {
  const data =
    mode === 'home'
      ? await fetchUserSummary(userId)
      : await fetchCommunitySummary();

  return {
    used: data.monthlyEnergy,
    earned: data.totalPoints,
  };
}

export async function fetchTasks() {
  const res = await fetch(`${BASE_URL}/api/tasks`);
  return handleResponse(res);
}

export async function fetchRewards() {
  const res = await fetch(`${BASE_URL}/api/rewards`);
  return handleResponse(res);
}

export async function fetchLeaderboard(userId, range) {
  const res = await fetch(
    `${BASE_URL}/api/users/${userId}/leaderboard/${range}`
  );
  return handleResponse(res);
}

