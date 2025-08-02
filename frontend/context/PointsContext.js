import React, { createContext, useState, useEffect } from 'react';
import { fetchCurrentUser } from '../service/api';

const DEFAULT_USER_ID = 1;

export const PointsContext = createContext({
  user: null,
  points: 0,
  addPoints: () => {},
  deductPoints: () => {},
  taskProgress: 0,
  incrementProgress: () => {},
});

export const PointsProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [points, setPoints] = useState(0);
  const [taskProgress, setTaskProgress] = useState(0);

  useEffect(() => {
    fetchCurrentUser(DEFAULT_USER_ID)
      .then((data) => {
        setUser(data);
        setPoints(data.dailyPoints || 0);
      })
      .catch(() => {});
  }, []);

  const addPoints = (p = 0) => setPoints((v) => v + p);
  const deductPoints = (p = 0) => setPoints((v) => Math.max(0, v - p));
  const incrementProgress = () =>
    setTaskProgress((p) => Math.min(p + 1, 2));

  return (
    <PointsContext.Provider
      value={{ user, points, addPoints, deductPoints, taskProgress, incrementProgress }}
    >
      {children}
    </PointsContext.Provider>
  );
};
