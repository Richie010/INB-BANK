import React, { useState } from 'react';
import './Dummy.css'; // Ensure you include this CSS in your project
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
// import { useAuth } from './AuthContext';

function App() {
  const [isActive, setIsActive] = useState(false);
  const navigate = useNavigate();
  // const { login } = useAuth(); // Call useAuth inside a functional component

  const handleToggle = () => {
    setIsActive(!isActive);
  };

  // Savings Login
  let [savingAccountId, setSavingAccountId] = useState("");
  let [savingPassword, setSavingPassword] = useState("");

  let SavinghandleSubmit = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.post('http://localhost:8082/api/savings/login', { "accountId": savingAccountId, "password": savingPassword });

      if (response.status === 201) {
        console.log(response.data);
        alert('Welcome user');
        localStorage.setItem('accountId', savingAccountId);
        // login('saving');
        navigate("/Dashboard");
      } else if (response.status === 404) {
        alert("Invalid login credentials, try again");
      } else if (response.status === 209) {
        alert(response.data);
      }
    } catch (e) {
      alert("Invalid Account Or password Please try again.");
    }
  };

  // Current Login
  let [accountId, setAccountId] = useState("");
  let [password, setPassword] = useState("");

  let handleSubmit = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.post('http://localhost:8082/currentaccount/login', { "accountId": accountId, "password": password });

      if (response.status === 201) {
        console.log(response);
        alert('Welcome user');
        localStorage.setItem('accountId', accountId);
        // login('current');
        navigate("/balance");
      } else if (response.status === 404) {
        alert("Invalid login credentials, try again");
      } else if (response.status === 209) {
        alert(response.data);
      }
    } catch (e) {
      alert("Invalid Account Or password Please try again.");
    }
  };

  return (
    <div className={`container mt-3 ${isActive ? 'active' : ''}`} id="container">
      <div className={`form-container ${isActive ? 'sign-up' : 'sign-in'}`}>
        {isActive && (
          <form onSubmit={handleSubmit} className="">
            <h1 className="text-2xl font-bold text-white">Current Login</h1>
            <div className='w-1/2'>
              <div>
                <label htmlFor="AccountID" className="text-sm font-medium text-white">
                  Enter Your Account ID
                </label>
                <div className="mt-2">
                  <input
                    id="accountId"
                    name="accountId"
                    type="number"
                    value={accountId}
                    onChange={(event) => setAccountId(event.target.value)}
                    required
                    autoComplete="AccountID"
                    className="block w-full text-black rounded-md border-gray-300 shadow-sm ring-1 ring-gray-300 placeholder-gray-400 focus:ring-2 focus:ring-indigo-600 sm:text-sm"
                  />
                </div>
              </div>
              <div>
                <label htmlFor="password" className="text-sm font-medium text-white">
                  Password
                </label>
                <div className="mt-2">
                  <input
                    id="password"
                    name="password"
                    type="password"
                    value={password}
                    onChange={(event) => setPassword(event.target.value)}
                    required
                    autoComplete="duration"
                    className="block w-full text-black rounded-md border-gray-300 shadow-sm ring-1 ring-gray-300 placeholder-gray-400 focus:ring-2 focus:ring-indigo-600 sm:text-sm"
                  />
                </div>
              </div>
              <div className='mt-3'>
                <button
                  type="submit"
                  className="flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
                >
                  Login
                </button>
              </div>
            </div>
          </form>
        )}
        {!isActive && (
          <form onSubmit={SavinghandleSubmit} className="">
            <h1 className="text-2xl font-bold text-white">Savings Login</h1>
            <div className='w-1/2'>
              <div>
                <label htmlFor="accountId" className="text-sm font-medium text-white">Enter Your Account ID</label>
                <div className="mt-2">
                  <input
                    id="accountId"
                    name="accountId"
                    type="text"
                    value={savingAccountId}
                    onChange={(event) => setSavingAccountId(event.target.value)}
                    required
                    autoComplete="username"
                    className="block w-full text-black rounded-md border-gray-300 shadow-sm ring-1 ring-gray-300 placeholder-gray-400 focus:ring-2 focus:ring-indigo-600 sm:text-sm"
                  />
                </div>
              </div>
              <div>
                <label htmlFor="password" className="text-sm font-medium text-white">Password</label>
                <div className="mt-2">
                  <input
                    id="password"
                    name="password"
                    type="password"
                    value={savingPassword}
                    onChange={(event) => setSavingPassword(event.target.value)}
                    required
                    autoComplete="current-password"
                    className="block w-full text-black rounded-md border-gray-300 shadow-sm ring-1 ring-gray-300 placeholder-gray-400 focus:ring-2 focus:ring-indigo-600 sm:text-sm"
                  />
                </div>
              </div>
              <div>
                <button
                  type="submit"
                  className="w-full rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold text-white shadow-sm hover:bg-indigo-500 focus:ring-2 focus:ring-indigo-600"
                >
                  Login
                </button>
              </div>
            </div>
          </form>
        )}
      </div>

      <div className="toggle-container">
        <div className="toggle">
          <div className="toggle-panel toggle-left">
            <h1>Welcome Back!</h1>
            <p>Login with your Account details to use all of site features</p>
            <button onClick={handleToggle}>Savings Account</button>
          </div>
          <div className="toggle-panel toggle-right">
            <h1>Hello, Friend!</h1>
            <p>Login with your Account details to use all of site features</p>
            <button onClick={handleToggle}>Current Account</button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default App;
