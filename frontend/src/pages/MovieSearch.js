import React, { useState, useEffect } from 'react';
import { tmdbAPI, movieAPI } from '../services/api';

const MovieSearch = ({ user }) => {
  const [searchQuery, setSearchQuery] = useState('');
  const [movies, setMovies] = useState([]);
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState('');

  // Load popular movies on component mount
  useEffect(() => {
    loadPopularMovies();
  }, []);

  const loadPopularMovies = async () => {
    console.log('TMDB API Key:', process.env.REACT_APP_TMDB_API_KEY);
    setLoading(true);
    try {
      const response = await tmdbAPI.getPopularMovies();
      setMovies(response.data.results);
    } catch (error) {
      console.error('Error loading popular movies:', error);
    } finally {
      setLoading(false);
    }
  };

  const handleSearch = async (e) => {
    e.preventDefault();
    if (!searchQuery.trim()) return;

    setLoading(true);
    try {
      const response = await tmdbAPI.searchMovies(searchQuery);
      setMovies(response.data.results);
    } catch (error) {
      console.error('Error searching movies:', error);
    } finally {
      setLoading(false);
    }
  };

  const addToWatchlist = async (movie) => {
    console.log('=== DEBUG INFO ===');
    console.log('Full user object:', user);
    console.log('Movie object:', movie);
    console.log('Movie data being sent:', {
        tmdbId: movie.id,
        title: movie.title,
        posterUrl: movie.poster_path ? `https://image.tmdb.org/t/p/w500${movie.poster_path}` : null
    });
    try {
      const movieData = {
        tmdbId: movie.id,
        title: movie.title,
        posterUrl: movie.poster_path ? `https://image.tmdb.org/t/p/w500${movie.poster_path}` : null
      };

      await movieAPI.addToWatchlist(user.id, movieData);
      setMessage(`"${movie.title}" added to your watchlist!`);
      setTimeout(() => setMessage(''), 3000);
    } catch (error) {
      setMessage('Error adding movie to watchlist');
      console.error('Error:', error);
    }
  };

  return (
    <div className="container mt-4">
      <h2>🔍 Search Movies</h2>
      
      {/* Search Form */}
      <form onSubmit={handleSearch} className="mb-4">
        <div className="input-group">
          <input
            type="text"
            className="form-control"
            placeholder="Search for movies..."
            value={searchQuery}
            onChange={(e) => setSearchQuery(e.target.value)}
          />
          <button className="btn btn-primary" type="submit">
            Search
          </button>
        </div>
      </form>

      {/* Success Message */}
      {message && (
        <div className="alert alert-success">
          {message}
        </div>
      )}

      {/* Loading */}
      {loading && (
        <div className="text-center">
          <div className="spinner-border" role="status">
            <span className="visually-hidden">Loading...</span>
          </div>
        </div>
      )}

      {/* Movies Grid */}
      <div className="row">
        {movies.map((movie) => (
          <div key={movie.id} className="col-md-3 mb-4">
            <div className="card h-100">
              <img
                src={
                  movie.poster_path
                    ? `https://image.tmdb.org/t/p/w500${movie.poster_path}`
                    : 'https://via.placeholder.com/300x450?text=No+Image'
                }
                className="card-img-top"
                alt={movie.title}
                style={{ height: '300px', objectFit: 'cover' }}
              />
              <div className="card-body d-flex flex-column">
                <h6 className="card-title">{movie.title}</h6>
                <p className="card-text small text-muted">
                  {movie.release_date ? new Date(movie.release_date).getFullYear() : 'TBA'}
                </p>
                <p className="card-text small flex-grow-1">
                  {movie.overview?.substring(0, 100)}...
                </p>
                <button
                  className="btn btn-primary btn-sm mt-auto"
                  onClick={() => addToWatchlist(movie)}
                >
                  Add to Watchlist
                </button>
              </div>
            </div>
          </div>
        ))}
      </div>

      {movies.length === 0 && !loading && (
        <div className="text-center mt-5">
          <p className="text-muted">No movies found. Try searching for something else!</p>
        </div>
      )}
    </div>
  );
};

export default MovieSearch;