const backendUrl = 'http://localhost:9192/api';

async function fetchJson(url, options) {
    const res = await fetch(url, options);
    if (!res.ok) {
        const txt = await res.text();
        throw new Error(txt || res.statusText);
    }
    return res.json();
}

async function loadUser() {
    const id = document.getElementById('userId').value;
    const data = await fetchJson(`${backendUrl}/users/${id}`);
    document.getElementById('user').innerHTML = `
        <p><strong>${data.username}</strong></p>
        <p>Daily Points: ${data.dailyPoints}</p>
        <p>Weekly Points: ${data.weeklyPoints}</p>
        <p>Total Points: ${data.totalPoints}</p>`;
}

async function loadTasks() {
    const tasks = await fetchJson(`${backendUrl}/tasks`);
    const tbody = tasks.map(t => `
        <tr><td>${t.title}</td><td>${t.description}</td><td>${t.rewardPoints}</td>
        <td><button onclick="completeTask(${t.id})">Complete</button></td></tr>`).join('');
    document.getElementById('tasks').innerHTML = `<tr><th>Title</th><th>Description</th><th>Reward</th><th></th></tr>${tbody}`;
}

async function loadRewards() {
    const rewards = await fetchJson(`${backendUrl}/rewards`);
    const tbody = rewards.map(r => `
        <tr><td>${r.name}</td><td>${r.costPoints}</td>
        <td><button onclick="redeemReward(${r.id})">Redeem</button></td></tr>`).join('');
    document.getElementById('rewards').innerHTML = `<tr><th>Name</th><th>Cost</th><th></th></tr>${tbody}`;
}

async function loadLeaderboard() {
    const id = document.getElementById('userId').value;
    const type = document.getElementById('lbType').value;
    const users = await fetchJson(`${backendUrl}/users/${id}/leaderboard/${type}`);
    const tbody = users.map(u => `
        <tr><td>${u.username}</td><td>${u.dailyPoints}</td><td>${u.weeklyPoints}</td><td>${u.totalPoints}</td></tr>`).join('');
    document.getElementById('leaderboard').innerHTML = `<tr><th>User</th><th>Daily</th><th>Weekly</th><th>Total</th></tr>${tbody}`;
}

async function completeTask(taskId) {
    const id = document.getElementById('userId').value;
    await fetchJson(`${backendUrl}/users/${id}/complete-task/${taskId}`, {method:'POST'});
    await loadUser();
    await loadLeaderboard();
}

async function redeemReward(rewardId) {
    const id = document.getElementById('userId').value;
    const res = await fetchJson(`${backendUrl}/users/${id}/redeem/${rewardId}`, {method:'POST'});
    alert(res);
    await loadUser();
}

async function loadAll() {
    await loadUser();
    await loadTasks();
    await loadRewards();
    await loadLeaderboard();
}

document.addEventListener('DOMContentLoaded', loadAll);
