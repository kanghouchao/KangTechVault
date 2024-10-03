import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../css/LoginPage.css';

function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    setError('');

    try {
      const response = await fetch('/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username, password })
      });

      if (response.ok) {
        navigate('/home');
      } else {
        setError('Incorrect username or password.');
      }
    } catch (error) {
      setError('I\'m sorry for this, Plese try again');
    }
  };

  return (
    <div className="container">
      <div className="header">
        <button className="register-button">Register</button>
      </div>
      <h2 className="page-title">Login Page</h2>
      <div className="form-container">
        <form onSubmit={handleLogin} className="form">
          <div className="input-group">
            <label htmlFor="username">Username:</label>
            <input
              type="text"
              id="username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              className="input"
              required
            />
          </div>
          <div className="input-group">
            <label htmlFor="password">Password:</label>
            <input
              type="password"
              id="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className="input"
              required
            />
          </div>
          {error && <p style={{ color: 'red' }}>{error}</p>}
          <button type="submit" className="button">Login</button>
          <a href="/" className="forgot-password">Forgot Password?</a>
        </form>
      </div>
    </div>
  );
}

export default Login;