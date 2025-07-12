import React from 'react';
import { Link } from 'react-router-dom';

const Dashboard = ({ user }) => {
  return (
    <div className="container mt-4">
      <div className="row">
        <div className="col-12">
          <h1>🎬 Welcome to Your Movie Watchlist!</h1>
          <p className="lead">Hey {user.username}, ready to track some awesome movies?</p>
          
          <div className="row mt-4">
            <div className="col-md-4">
              <div className="card">
                <div className="card-body text-center">
                  <h5 className="card-title">📝 My Watchlist</h5>
                  <p className="card-text">Movies you want to watch</p>
                  <Link to="/watchlist" className="btn btn-primary">View Watchlist</Link>
                </div>
              </div>
            </div>
            
            <div className="col-md-4">
              <div className="card">
                <div className="card-body text-center">
                  <h5 className="card-title">✅ Watched Movies</h5>
                  <p className="card-text">Movies you've already seen</p>
                  <Link to="/watched" className="btn btn-success">View Watched</Link>
                </div>
              </div>
            </div>
            
            <div className="col-md-4">
              <div className="card">
                <div className="card-body text-center">
                  <h5 className="card-title">🔍 Search Movies</h5>
                  <p className="card-text">Find new movies to add</p>
                  <Link to="/search" className="btn btn-info">Search Movies</Link>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;