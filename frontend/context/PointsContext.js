import React, { createContext, useEffect, useState } from 'react';
import { fetchUserSummary } from '../service/api';

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

  useEffect(() => {
    const load = async () => {
      try {
        const data = await fetchUserSummary(1);
        setPoints(data.totalPoints || 0);
      } catch (e) {
        console.error(e);
      }
    };
    load();
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
