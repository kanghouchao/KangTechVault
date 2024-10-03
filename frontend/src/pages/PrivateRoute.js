import React from 'react';
import { Navigate } from 'react-router-dom';

const PrivateRoute = ({ children }) => {
  const token = document.cookie.split(';').find(cookie => cookie.trim().startsWith('token='));

  // 如果没有 token，跳转到登录页面
  return token ? children : <Navigate to="/login" />;
};

export default PrivateRoute;
