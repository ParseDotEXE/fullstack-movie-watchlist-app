import React, { useState, useEffect } from 'react';
import { movieAPI } from '../services/api';

const Watchlist = ({ user }) => {
  const [watchlist, setWatchlist] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [message, setMessage] = useState('');

  useEffect(() => {
    loadWatchlist();
  }, []);

  const loadWatchlist = async () => {
    setLoading(true);
    setError('');
    try {
      const response = await movieAPI.getWatchlist(user.id);
      setWatchlist(response.data);
    } catch (err) {
      setError(err.response?.data?.message || 'Error loading watchlist');
    } finally {
      setLoading(false);
    }
  };

  const markAsWatched = async (movieId, rating = 5) => {
    try {
      await movieAPI.markAsWatched(user.id, movieId, { rating });
      setMessage('Movie marked as watched!');
      loadWatchlist(); // Refresh the list
      setTimeout(() => setMessage(''), 3000);
    } catch (err) {
      setError(err.response?.data?.message || 'Error marking movie as watched');
    }
  };

  const deleteMovie = async (movieId) => {
    try {
      await movieAPI.deleteMovie(user.id, movieId);
      setMessage('Movie removed from watchlist!');
      loadWatchlist(); // Refresh the list
      setTimeout(() => setMessage(''), 3000);
    } catch (err) {
      setError(err.response?.data?.message || 'Error removing movie');
    }
  };

  if (loading) {
    return (
      <div className="container mt-4 text-center">
        <div className="spinner-border" role="status">
          <span className="visually-hidden">Loading...</span>
        </div>
      </div>
    );
  }

  return (
    <div className="container mt-4">
      <h2>📝 My Watchlist</h2>
      
      {message && (
        <div className="alert alert-success">
          {message}
        </div>
      )}

      {error && (
        <div className="alert alert-info">
          {error}
        </div>
      )}

      {Array.isArray(watchlist) && watchlist.length > 0 ? (
        <div className="row">
          {watchlist.map((item) => (
            <div key={item.movieId} className="col-md-3 mb-4">
              <div className="card h-100">
                <img
                  src={item.movie.posterUrl || 'https://via.placeholder.com/300x450?text=No+Image'}
                  className="card-img-top"
                  alt={item.movie.title}
                  style={{ height: '300px', objectFit: 'cover' }}
                />
                <div className="card-body d-flex flex-column">
                  <h6 className="card-title">{item.movie.title}</h6>
                  <p className="card-text small text-muted">
                    Added: {new Date(item.dateAdded).toLocaleDateString()}
                  </p>
                  <div className="mt-auto">
                    <button
                      className="btn btn-success btn-sm me-2"
                      onClick={() => markAsWatched(item.movieId)}
                    >
                      Mark Watched
                    </button>
                    <button
                      className="btn btn-danger btn-sm"
                      onClick={() => deleteMovie(item.movieId)}
                    >
                      Remove
                    </button>
                  </div>
                </div>
              </div>
            </div>
          ))}
        </div>
      ) : (
        !error && (
          <div className="text-center mt-5">
            <p className="text-muted">There are no movies in your watchlist :(</p>
          </div>
        )
      )}
    </div>
  );
};

export default Watchlist;