import React from 'react';
import { Navigate, useLocation } from 'react-router-dom';
import { useAuth } from './AuthContext';
import DarkHome from './DarkPage';

const PrivateRoute = ({ element }) => {
  const { isAuthenticated } = useAuth();
  const location = useLocation(); // Use this to keep track of where the user was trying to go

  if (!isAuthenticated) {
    // Redirect to the DarkHome or any other page
    return <Navigate to="/" state={{ from: location }} replace />;
  }

  return element;
};

export default PrivateRoute;
