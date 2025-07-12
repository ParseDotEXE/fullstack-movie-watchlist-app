import React from 'react';
import { Link } from 'react-router-dom';

const Navbar = ({ user, onLogout }) => {
  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
      <div className="container">
        <Link className="navbar-brand" to="/">
          🎬 Movie Watchlist
        </Link>
        
        <div className="navbar-nav ms-auto">
          {user ? (
            // Logged in user navigation
            <>
              <span className="navbar-text me-3">
                Welcome, {user.username}!
              </span>
              <button 
                className="btn btn-outline-light" 
                onClick={onLogout}
              >
                Logout
              </button>
            </>
          ) : (
            // Guest navigation
            <>
              <Link className="nav-link btn btn-outline-light me-2" to="/login">
                Login
              </Link>
              <Link className="nav-link btn btn-primary" to="/signup">
                Sign Up
              </Link>
            </>
          )}
        </div>
      </div>
    </nav>
  );
};

export default Navbar;