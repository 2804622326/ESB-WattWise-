export async function fetchFromApi(endpoint, options = {}) {
  const response = await fetch(endpoint, options);
  if (!response.ok) {
    throw new Error('API request failed');
  }
  return response.json();
}
