import React, { createContext, useState, useEffect } from 'react';
import { fetchCurrentUser } from '../service/api';

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
    fetchCurrentUser(1)
      .then((u) => setPoints(u.dailyPoints || 0))
      .catch((err) => console.error(err));
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
