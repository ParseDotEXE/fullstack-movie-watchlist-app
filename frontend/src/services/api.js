import axios from 'axios';

// Base URL for your Spring Boot backend
const API_BASE_URL = 'http://localhost:8080/api';

// Create axios instance with base configuration
const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Authentication API calls
export const authAPI = {
  // Sign up a new user
  signup: (userData) => api.post('/auth/signup', userData),
  
  // Login user
  login: (credentials) => api.post('/auth/login', credentials),
};

// Movie/Watchlist API calls
export const movieAPI = {
  // Add movie to watchlist
  addToWatchlist: (userId, movieData) => 
    api.post(`/users/${userId}/watchlist`, movieData),
  
  // Get user's watchlist
  getWatchlist: (userId) => 
    api.get(`/users/${userId}/watchlist`),
  
  // Get user's watched movies
  getWatchedMovies: (userId) => 
    api.get(`/users/${userId}/watched`),
  
  // Mark movie as watched with rating
  markAsWatched: (userId, movieId, ratingData) => 
    api.put(`/users/${userId}/movies/${movieId}/watched`, ratingData),
  
  // Update movie rating
  updateRating: (userId, movieId, ratingData) => 
    api.put(`/users/${userId}/movies/${movieId}/rating`, ratingData),
  
  // Delete movie from lists
  deleteMovie: (userId, movieId) => 
    api.delete(`/users/${userId}/movies/${movieId}`),
};

// TMDB API configuration
const TMDB_BASE_URL = 'https://api.themoviedb.org/3';
const TMDB_API_KEY = ProcessingInstruction.env.REACT_APP_TMDB_API_KEY; 

// TMDB API calls
export const tmdbAPI = {
  // Search for movies
  searchMovies: (query) => 
    axios.get(`${TMDB_BASE_URL}/search/movie`, {
      params: {
        api_key: TMDB_API_KEY,
        query: query,
        language: 'en-US',
        page: 1
      }
    }),
  
  // Get popular movies
  getPopularMovies: () =>
    axios.get(`${TMDB_BASE_URL}/movie/popular`, {
      params: {
        api_key: TMDB_API_KEY,
        language: 'en-US',
        page: 1
      }
    })
};

export default api;