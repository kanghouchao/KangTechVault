import React, { useState } from 'react';
import '../css/LoginPage.css';

function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = (event) => {
    event.preventDefault();
    console.log('Username:', username);
    console.log('Password:', password);
  };

  return (
    <div className="container">
      <div className="header">
        <button className="register-button">Register</button>
      </div>
      <h2 className="page-title">Login Page</h2>
      <div className="form-container">
        <form onSubmit={handleSubmit} className="form">
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
          <button type="submit" className="button">Login</button>
          <a href="/" className="forgot-password">Forgot Password?</a>
        </form>
      </div>
    </div>
  );
}

export default Login;