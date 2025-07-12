import React, { useState, useEffect } from 'react';
import { movieAPI } from '../services/api';

const Watched = ({ user }) => {
  const [watchedMovies, setWatchedMovies] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [message, setMessage] = useState('');

  useEffect(() => {
    loadWatchedMovies();
  }, []);

  const loadWatchedMovies = async () => {
    setLoading(true);
    setError('');
    try {
      const response = await movieAPI.getWatchedMovies(user.id);
      setWatchedMovies(response.data);
    } catch (err) {
      setError(err.response?.data?.message || 'Error loading watched movies');
    } finally {
      setLoading(false);
    }
  };

  const updateRating = async (movieId, newRating) => {
    try {
      await movieAPI.updateRating(user.id, movieId, { rating: newRating });
      setMessage('Rating updated!');
      loadWatchedMovies(); // Refresh the list
      setTimeout(() => setMessage(''), 3000);
    } catch (err) {
      setError(err.response?.data?.message || 'Error updating rating');
    }
  };

  const deleteMovie = async (movieId) => {
    try {
      await movieAPI.deleteMovie(user.id, movieId);
      setMessage('Movie removed from watched list!');
      loadWatchedMovies(); // Refresh the list
      setTimeout(() => setMessage(''), 3000);
    } catch (err) {
      setError(err.response?.data?.message || 'Error removing movie');
    }
  };

  const renderStars = (rating, movieId) => {
    const stars = [];
    for (let i = 1; i <= 5; i++) {
      stars.push(
        <span
          key={i}
          className={`star ${i <= rating ? 'text-warning' : 'text-muted'}`}
          style={{ cursor: 'pointer', fontSize: '1.2em' }}
          onClick={() => updateRating(movieId, i)}
        >
          ★
        </span>
      );
    }
    return stars;
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
      <h2>✅ Watched Movies</h2>
      
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

      {Array.isArray(watchedMovies) && watchedMovies.length > 0 ? (
        <div className="row">
          {watchedMovies.map((item) => (
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
                    Watched: {new Date(item.dateWatched).toLocaleDateString()}
                  </p>
                  <div className="mb-2">
                    <small className="text-muted">Your Rating:</small><br />
                    {renderStars(item.rating || 0, item.movieId)}
                  </div>
                  <div className="mt-auto">
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
            <p className="text-muted">Seems like you haven't watched any movies yet :(</p>
          </div>
        )
      )}
    </div>
  );
};

export default Watched;