import React, { createContext, useState, useEffect } from 'react';
import { BASE_URL } from '../config';

export const PointsContext = createContext({
  points: 0,
  addPoints: () => {},
  deductPoints: () => {},
  taskProgress: 0,
  incrementProgress: () => {},
});

export const PointsProvider = ({ children }) => {
  const [points, setPoints] = useState(0);
  const [taskProgress, setTaskProgress] = useState(0);
  const userId = 1;

  useEffect(() => {
    async function loadPoints() {
      try {
        const res = await fetch(`${BASE_URL}/api/users/${userId}`);
        if (res.ok) {
          const data = await res.json();
          setPoints(data.dailyPoints || 0);
        }
      } catch (e) {
        console.error('Failed to fetch user info', e);
      }
    }

    loadPoints();
  }, []);

  const addPoints = (p = 0) => setPoints((v) => v + p);
  const deductPoints = (p = 0) => setPoints((v) => Math.max(0, v - p));
  const incrementProgress = () =>
    setTaskProgress((p) => Math.min(p + 1, 2));

  return (
    <PointsContext.Provider
      value={{ points, addPoints, deductPoints, taskProgress, incrementProgress }}
    >
      {children}
    </PointsContext.Provider>
  );
};
