import axios from 'axios';


const baseAxios = axios.create({
  timeout: 40000,
});

baseAxios.showNotifications = true;

baseAxios.interceptors.request.use(
  config => {
    return config;
  },
  error => Promise.reject(error)
);

baseAxios.interceptors.response.use(
  response => response,
  error => {
    return Promise.reject(error);
  }
);

export default baseAxios;
